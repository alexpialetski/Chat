package by.epam.pialetskialiaksei.command.client.registration;

import by.epam.pialetskialiaksei.Fields;
import by.epam.pialetskialiaksei.Path;
import by.epam.pialetskialiaksei.command.api.Command;
import by.epam.pialetskialiaksei.entity.Client;
import by.epam.pialetskialiaksei.entity.Role;
import by.epam.pialetskialiaksei.entity.User;
import by.epam.pialetskialiaksei.exception.CommandException;
import by.epam.pialetskialiaksei.exception.DAOException;
import by.epam.pialetskialiaksei.sql.DAO.ClientDao;
import by.epam.pialetskialiaksei.sql.DAO.UserDao;
import by.epam.pialetskialiaksei.sql.builder.ClientBuilder;
import by.epam.pialetskialiaksei.sql.сriteria.client.InsertClientCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.user.FindUserByEmailAndPasswordCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.user.InsertUserCriteria;
import by.epam.pialetskialiaksei.util.ActionType;
import by.epam.pialetskialiaksei.util.Response;
import by.epam.pialetskialiaksei.util.validation.ProfileInputValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientRegistrationCommand implements Command {

    private static final long VersionUID = -3071536593627692473L;

    private static final Logger LOG = LogManager.getLogger(ClientRegistrationCommand.class);


    @Override
    public Response execute(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException, CommandException {
        LOG.debug("Start executing Command");
        try {
            String email = request.getParameter(Fields.USER_EMAIL);
            String password = request.getParameter(Fields.USER_PASSWORD);
            String firstName = request.getParameter(Fields.CLIENT_FIRST_NAME);
            String lastName = request.getParameter(Fields.CLIENT_LAST_NAME);

            boolean validUser = ProfileInputValidator.validateUserParameters(firstName,
                    lastName, email, password);

//            boolean validEntrant = ProfileInputValidator.validateEntrantParameters(town, district,
//                    school);
//            if (!validUser||!validEntrant) {
            if (!validUser) {
                request.getSession().setAttribute("errorEng",
                        "Please fill all fields properly!");
                request.getSession().setAttribute("errorRu",
                        "Пожалуйста, заполните все поля правильно!");
                LOG.error("errorMessage: Not all fields are filled");
                return new Response(Path.REDIRECT_CLIENT_REGISTRATION_PAGE, ActionType.POST);
            } else {
                User user = new User();
                user.setPassword(password);
                user.setEmail(email);
                user.setRole(Role.CLIENT.toString());
//                User user = new User(email, password, firstName, lastName,
//                        Role.CLIENT);
                UserDao userDao = new UserDao();
                userDao.insert(new InsertUserCriteria(user));
//                UserDAO userDAO = new UserDAO();
//                userDAO.create(user);
                LOG.trace("User record created: " + user);

                Client client = new Client(firstName, lastName, user.getId());
                ClientDao clientDao = new ClientDao();
                clientDao.insert(new InsertClientCriteria(client));

//                Entrant entrant = new Entrant(town, district, school, user);
//                EntrantDAO entrantDAO = new EntrantDAO();
//                entrantDAO.create(entrant);
                LOG.trace("Entrant record created: " + client);
                return new Response(Path.LOGIN_PAGE, ActionType.GET);
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }
    }
}
