package by.epam.pialetskialiaksei.sql.DAO;

import by.epam.pialetskialiaksei.exception.DAOException;
import by.epam.pialetskialiaksei.sql.DAO.api.SqlDAO;
import by.epam.pialetskialiaksei.sql.сriteria.api.DeleteCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.api.FindCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.api.InsertCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.api.UpdateCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.client.api.ClientCriteria;
import by.epam.pialetskialiaksei.util.DTO;

public class ClientDao extends SqlDAO {
    public <T extends ClientCriteria & FindCriteria> DTO find(T criteria) throws DAOException {
        return super.find(criteria);
    }
    public <T extends ClientCriteria & DeleteCriteria> void delete(T criteria) throws DAOException {
        super.delete(criteria);
    }
    public <T extends ClientCriteria & UpdateCriteria> void update(T criteria) throws DAOException {
        super.update(criteria);
    }
    public <T extends ClientCriteria & InsertCriteria> void insert(T criteria) throws DAOException {
        super.insert(criteria);
    }
}
