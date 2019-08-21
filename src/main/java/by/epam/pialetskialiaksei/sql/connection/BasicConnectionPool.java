package by.epam.pialetskialiaksei.sql.connection;

import by.epam.pialetskialiaksei.sql.connection.api.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BasicConnectionPool implements ConnectionPool {

    private String url;
    private String user;
    private String password;
    private List<ProxyConnection> connectionPool;
    private List<ProxyConnection> usedConnections = new ArrayList<>();
    private int maxPoolSize;
    private Semaphore SEMAPHORE;
    private static final Lock LOCK = new ReentrantLock();
    private static final Logger LOGGER = LogManager.getLogger(BasicConnectionPool.class);

    private BasicConnectionPool(String url, String user, String password, List<ProxyConnection> connectionPool, int maxPoolSize) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.maxPoolSize = maxPoolSize;
        this.connectionPool = connectionPool;
        SEMAPHORE = new Semaphore(maxPoolSize);
    }

    static BasicConnectionPool create(String url, String user, String password, int initialPoolSize, int maxPoolSize) throws SQLException {
        List<ProxyConnection> pool = new ArrayList<>(initialPoolSize);
        for (int i = 0; i < initialPoolSize; i++) {
            pool.add(new ProxyConnection(createConnection(url, user, password)));
        }
        return new BasicConnectionPool(url, user, password, pool, maxPoolSize);
    }

    @Override
    public ProxyConnection getConnection() {

        try {
            SEMAPHORE.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOCK.lock();

        if (connectionPool.isEmpty() && usedConnections.size() < maxPoolSize) {
            try {
                connectionPool.add(new ProxyConnection(createConnection(url, user, password)));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ProxyConnection connection = connectionPool
                .remove(connectionPool.size() - 1);
        usedConnections.add(connection);

        LOCK.unlock();

        return connection;
    }

    @Override
    public boolean releaseConnection(ProxyConnection connection) {
        LOCK.lock();
        connectionPool.add(connection);
        SEMAPHORE.release();
        LOCK.unlock();
        return usedConnections.remove(connection);
    }

    private static Connection createConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

    @Override
    public void shutdown() throws SQLException {
        for (ProxyConnection c : usedConnections) {
            c.realClose();
            LOGGER.info("closed connection");
        }
        usedConnections.clear();
        for (ProxyConnection c : connectionPool) {
            c.realClose();
            LOGGER.info("closed connection");
        }
        connectionPool.clear();
    }
}
