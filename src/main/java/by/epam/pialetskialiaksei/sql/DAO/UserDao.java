package by.epam.pialetskialiaksei.sql.DAO;

import by.epam.pialetskialiaksei.exception.DAOException;
import by.epam.pialetskialiaksei.sql.DAO.api.SqlDAO;
import by.epam.pialetskialiaksei.sql.сriteria.api.*;
import by.epam.pialetskialiaksei.sql.сriteria.user.api.UserCriteria;
import by.epam.pialetskialiaksei.util.DTO;

public class UserDao extends SqlDAO {
    public <T extends UserCriteria & FindCriteria> DTO find(T criteria) throws DAOException {
        return super.find(criteria);
    }
    public <T extends UserCriteria & DeleteCriteria> void delete(T criteria) throws DAOException {
        super.delete(criteria);
    }
    public <T extends UserCriteria & UpdateCriteria> void update(T criteria) throws DAOException {
        super.update(criteria);
    }
    public <T extends UserCriteria & InsertCriteria> void insert(T criteria) throws DAOException {
        super.insert(criteria);
    }
}
