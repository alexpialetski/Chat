package by.epam.pialetskialiaksei.command.client.client;

import by.epam.pialetskialiaksei.Path;
import by.epam.pialetskialiaksei.command.api.Command;
import by.epam.pialetskialiaksei.entity.Client;
import by.epam.pialetskialiaksei.entity.User;
import by.epam.pialetskialiaksei.exception.CommandException;
import by.epam.pialetskialiaksei.exception.DAOException;
import by.epam.pialetskialiaksei.sql.DAO.ClientDao;
import by.epam.pialetskialiaksei.sql.DAO.FriendDAO;
import by.epam.pialetskialiaksei.sql.DAO.UserDao;
import by.epam.pialetskialiaksei.sql.сriteria.client.FindClientById;
import by.epam.pialetskialiaksei.sql.сriteria.friend.FindFriendshipByEmailCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.friend.FindFriendsByUserIdCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.user.FindUserByEmailCriteria;
import by.epam.pialetskialiaksei.util.ActionType;
import by.epam.pialetskialiaksei.util.DTO;
import by.epam.pialetskialiaksei.util.Dummy;
import by.epam.pialetskialiaksei.util.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewClientCommand implements Command {

    private static final long VersionUID = -3071536593627692473L;

    private static final Logger LOG = LogManager.getLogger(ViewAllClientsCommand.class);

    @Override
    public Response execute(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException, ServletException, CommandException {

        LOG.debug("Start executing Command");
        try {
            String email = (String) request.getSession().getAttribute("email");

            int id = Integer.parseInt(request.getParameter("id"));

            User user = (User) new UserDao().find(new FindUserByEmailCriteria(email)).getData();

            if(user.getId() == id){
                return new Response(Path.REDIRECT_TO_PROFILE, ActionType.POST);
            }

            ClientDao clientDao = new ClientDao();
            Client client = (Client) clientDao.find(new FindClientById(id)).getData();

            request.setAttribute("client", client);
            LOG.trace("Set the request attribute: 'client' = "
                    + client);

            FriendDAO friendDAO = new FriendDAO();

            DTO dto = friendDAO.find(new FindFriendsByUserIdCriteria(id));
            List<Client> friends = new ArrayList<>();
            if (!(dto.getData() instanceof Dummy)) {
                friends = (List<Client>) dto.getData();
            }
            boolean friend = true;
            if (friendDAO.find(new FindFriendshipByEmailCriteria(email, id)).getData() instanceof Dummy) {
                friend = false;
            }

            request.setAttribute("friends", friends);
            LOG.trace("Set the request attribute: 'friends' = "
                    + friends);
            request.setAttribute("friend", friend);
            LOG.trace("Set the request attribute: 'friend' = "
                    + friend);
            request.getSession().setAttribute("prevCommand", "viewClient&id="+ Integer.toString(id));
            return new Response(Path.FORWARD_CLIENT_PAGE, ActionType.GET);
        } catch (DAOException e) {
            throw new CommandException(e);
        }
    }
}