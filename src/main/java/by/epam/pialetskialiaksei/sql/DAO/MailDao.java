package by.epam.pialetskialiaksei.sql.DAO;

import by.epam.pialetskialiaksei.entity.Mail;
import by.epam.pialetskialiaksei.exception.DAOException;
import by.epam.pialetskialiaksei.sql.DAO.api.SqlDAO;
import by.epam.pialetskialiaksei.sql.сriteria.api.*;
import by.epam.pialetskialiaksei.sql.сriteria.conversationgroup.api.ConversationGroupCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.mail.api.MailCriteria;
import by.epam.pialetskialiaksei.util.DTO;

public class MailDao extends SqlDAO{
    public <T extends MailCriteria & FindCriteria> DTO find(T criteria) throws DAOException {
        return super.find(criteria);
    }
    public <T extends MailCriteria & DeleteCriteria> void delete(T criteria) throws DAOException {
        super.delete(criteria);
    }
    public <T extends MailCriteria & UpdateCriteria> void update(T criteria) throws DAOException {
        super.update(criteria);
    }
    public <T extends MailCriteria & InsertCriteria> void insert(T criteria) throws DAOException {
        super.insert(criteria);
    }
}
