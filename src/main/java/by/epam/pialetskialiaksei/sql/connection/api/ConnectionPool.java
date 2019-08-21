package by.epam.pialetskialiaksei.sql.connection.api;

import by.epam.pialetskialiaksei.sql.connection.BasicConnectionPool;
import by.epam.pialetskialiaksei.sql.connection.ProxyConnection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {
    ProxyConnection getConnection();
    boolean releaseConnection(ProxyConnection connection);
    void shutdown() throws SQLException;
}
