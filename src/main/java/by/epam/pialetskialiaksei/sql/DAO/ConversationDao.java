package by.epam.pialetskialiaksei.sql.DAO;

import by.epam.pialetskialiaksei.exception.DAOException;
import by.epam.pialetskialiaksei.sql.DAO.api.SqlDAO;
import by.epam.pialetskialiaksei.sql.сriteria.api.*;
import by.epam.pialetskialiaksei.sql.сriteria.client.api.ClientCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.conversation.api.ConversationCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.user.api.UserCriteria;
import by.epam.pialetskialiaksei.util.DTO;

public class ConversationDao extends SqlDAO {
    public <T extends ConversationCriteria & FindCriteria> DTO find(T criteria) throws DAOException {
        return super.find(criteria);
    }
    public <T extends ConversationCriteria & DeleteCriteria> void delete(T criteria) throws DAOException {
        super.delete(criteria);
    }
    public <T extends ConversationCriteria & UpdateCriteria> void update(T criteria) throws DAOException {
        super.update(criteria);
    }
    public <T extends ConversationCriteria & InsertCriteria> void insert(T criteria) throws DAOException {
        super.insert(criteria);
    }
}
