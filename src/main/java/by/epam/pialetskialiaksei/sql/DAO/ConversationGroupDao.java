package by.epam.pialetskialiaksei.sql.DAO;

import by.epam.pialetskialiaksei.exception.DAOException;
import by.epam.pialetskialiaksei.sql.DAO.api.SqlDAO;
import by.epam.pialetskialiaksei.sql.сriteria.api.*;
import by.epam.pialetskialiaksei.sql.сriteria.conversation.api.ConversationCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.conversationgroup.api.ConversationGroupCriteria;
import by.epam.pialetskialiaksei.util.DTO;

public class ConversationGroupDao extends SqlDAO {
    public <T extends ConversationGroupCriteria & FindCriteria> DTO find(T criteria) throws DAOException {
        return super.find(criteria);
    }
    public <T extends ConversationGroupCriteria & DeleteCriteria> void delete(T criteria) throws DAOException {
        super.delete(criteria);
    }
    public <T extends ConversationGroupCriteria & UpdateCriteria> void update(T criteria) throws DAOException {
        super.update(criteria);
    }
    public <T extends ConversationGroupCriteria & InsertCriteria> void insert(T criteria) throws DAOException {
        super.insert(criteria);
    }
}
