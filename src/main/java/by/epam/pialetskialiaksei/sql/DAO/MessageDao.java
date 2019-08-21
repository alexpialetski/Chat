package by.epam.pialetskialiaksei.sql.DAO;

import by.epam.pialetskialiaksei.exception.DAOException;
import by.epam.pialetskialiaksei.sql.DAO.api.SqlDAO;
import by.epam.pialetskialiaksei.sql.сriteria.api.*;
import by.epam.pialetskialiaksei.sql.сriteria.message.api.MessageCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.user.api.UserCriteria;
import by.epam.pialetskialiaksei.util.DTO;

public class MessageDao extends SqlDAO {
    public <T extends MessageCriteria & FindCriteria> DTO find(T criteria) throws DAOException {
        return super.find(criteria);
    }
    public <T extends MessageCriteria & DeleteCriteria> void delete(T criteria) throws DAOException {
        super.delete(criteria);
    }
    public <T extends MessageCriteria & UpdateCriteria> void update(T criteria) throws DAOException {
        super.update(criteria);
    }
    public <T extends MessageCriteria & InsertCriteria> void insert(T criteria) throws DAOException {
        super.insert(criteria);
    }
}
