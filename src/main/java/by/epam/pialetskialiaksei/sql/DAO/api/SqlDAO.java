package by.epam.pialetskialiaksei.sql.DAO.api;

import by.epam.pialetskialiaksei.exception.DAOException;
import by.epam.pialetskialiaksei.sql.connection.ConnectionPoolManager;
import by.epam.pialetskialiaksei.sql.connection.ProxyConnection;
import by.epam.pialetskialiaksei.sql.сriteria.api.*;
import by.epam.pialetskialiaksei.util.DTO;
import by.epam.pialetskialiaksei.util.Dummy;

import java.sql.*;
import java.util.Optional;

public abstract class SqlDAO {
    protected ProxyConnection getConnection() {
        return ConnectionPoolManager.getConnection();
    }

    protected DTO find(Criteria criteria) throws DAOException{
        try (ProxyConnection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(criteria.getQuery());
            criteria.prepareStatement(statement);
            ResultSet resultSet = statement.executeQuery();
//            resultSet.next();
//            Optional<DTO> dto = Optional.of(criteria.process(resultSet));
//            dto.ifPresent();
            DTO dto = null;
            if(resultSet.next()) {
                dto = criteria.process(resultSet);
            }else{
                dto = new DTO(new Dummy());
            }
            resultSet.close();
            statement.close();
            return dto;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    protected void delete(Criteria criteria) throws DAOException {
        try (ProxyConnection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(criteria.getQuery());
            criteria.prepareStatement(statement);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    protected void update(Criteria criteria) throws DAOException {
        try (ProxyConnection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(criteria.getQuery());
            criteria.prepareStatement(statement);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    protected void insert(Criteria criteria) throws DAOException {
        try (ProxyConnection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(criteria.getQuery());
            criteria.prepareStatement(statement);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
