package by.epam.pialetskialiaksei.command.client.friend;

import by.epam.pialetskialiaksei.Path;
import by.epam.pialetskialiaksei.command.api.Command;
import by.epam.pialetskialiaksei.command.client.login.LoginCommand;
import by.epam.pialetskialiaksei.entity.User;
import by.epam.pialetskialiaksei.exception.CommandException;
import by.epam.pialetskialiaksei.exception.DAOException;
import by.epam.pialetskialiaksei.sql.DAO.FriendDAO;
import by.epam.pialetskialiaksei.sql.DAO.UserDao;
import by.epam.pialetskialiaksei.sql.сriteria.friend.DeleteFriendsCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.friend.InsertFriendsCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.user.FindUserByEmailCriteria;
import by.epam.pialetskialiaksei.util.ActionType;
import by.epam.pialetskialiaksei.util.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BreakFriendsCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(LoginCommand.class);

    @Override
    public Response execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, CommandException {
        LOG.debug("Start executing Command");
        try {
            HttpSession session = request.getSession(false);
            String userEmail = String.valueOf(session.getAttribute("email"));
            UserDao userDao = new UserDao();
            User user = (User) userDao.find(new FindUserByEmailCriteria(userEmail)).getData();

            Integer friendId = Integer.parseInt(request.getParameter("id"));
            FriendDAO friendDAO = new FriendDAO();
            friendDAO.delete(new DeleteFriendsCriteria(user.getId(), friendId));
            friendDAO.delete(new DeleteFriendsCriteria(friendId, user.getId()));

            return new Response(Path.REDIRECT_TO_VIEW_CLIENT.replace("*", friendId.toString()), ActionType.POST);
        } catch (DAOException e) {
            throw new CommandException(e);
        }
    }
}
