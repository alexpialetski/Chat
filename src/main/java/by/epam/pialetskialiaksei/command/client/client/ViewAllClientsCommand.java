package by.epam.pialetskialiaksei.command.client.client;

import by.epam.pialetskialiaksei.Path;
import by.epam.pialetskialiaksei.command.api.Command;
import by.epam.pialetskialiaksei.entity.Client;
import by.epam.pialetskialiaksei.entity.User;
import by.epam.pialetskialiaksei.exception.CommandException;
import by.epam.pialetskialiaksei.exception.DAOException;
import by.epam.pialetskialiaksei.sql.DAO.ClientDao;
import by.epam.pialetskialiaksei.sql.DAO.UserDao;
import by.epam.pialetskialiaksei.sql.сriteria.client.FindAllClientsCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.client.FindClientByUserEmailCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.user.FindAllUsersCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.user.FindUserByEmailAndPasswordCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.user.FindUserByEmailCriteria;
import by.epam.pialetskialiaksei.util.ActionType;
import by.epam.pialetskialiaksei.util.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Invoked when user logins in the system.
 *
 */
public class ViewAllClientsCommand implements Command {

    private static final long VersionUID = -3071536593627692473L;

    private static final Logger LOG = LogManager.getLogger(ViewAllClientsCommand.class);

    @Override
    public Response execute(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException, ServletException, CommandException {

        LOG.debug("Start executing Command");
        try {
            String email = (String) request.getSession().getAttribute("email");

            ClientDao clientDao = new ClientDao();
            Client client = (Client) clientDao.find(new FindClientByUserEmailCriteria(email)).getData();

            List<Client> clients = (List<Client>) new ClientDao().find(new FindAllClientsCriteria()).getData();
            clients.remove(client);

            request.setAttribute("clients", clients);
            LOG.trace("Set the request attribute: 'clients' = "
                    + clients);
            return new Response(Path.FORWARD_ALL_CLIENTS, ActionType.GET);
        } catch (DAOException e) {
            throw new CommandException(e);
        }
    }
}