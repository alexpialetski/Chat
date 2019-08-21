package by.epam.pialetskialiaksei.command.client.registration;

import by.epam.pialetskialiaksei.Fields;
import by.epam.pialetskialiaksei.command.api.Command;
import by.epam.pialetskialiaksei.entity.Client;
import by.epam.pialetskialiaksei.entity.Mail;
import by.epam.pialetskialiaksei.entity.Role;
import by.epam.pialetskialiaksei.entity.User;
import by.epam.pialetskialiaksei.exception.CommandException;
import by.epam.pialetskialiaksei.exception.DAOException;
import by.epam.pialetskialiaksei.sql.DAO.ClientDao;
import by.epam.pialetskialiaksei.sql.DAO.MailDao;
import by.epam.pialetskialiaksei.sql.DAO.UserDao;
import by.epam.pialetskialiaksei.sql.сriteria.client.FindClientByUserEmailCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.mail.InsertMail;
import by.epam.pialetskialiaksei.sql.сriteria.user.FindUserByEmailCriteria;
import by.epam.pialetskialiaksei.util.ActionType;
import by.epam.pialetskialiaksei.util.MailUtils;
import by.epam.pialetskialiaksei.util.Response;
import by.epam.pialetskialiaksei.util.validation.ProfileInputValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SendConfirmationRegistrationCommand implements Command {

    private static final long VersionUID = -3071536593627692473L;

    private static final Logger LOG = LogManager.getLogger(SendConfirmationRegistrationCommand.class);

    @Override
    public Response execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, CommandException {
        LOG.debug("Start executing Command");
        try {
            String email = request.getParameter(Fields.USER_EMAIL);
            String password = request.getParameter(Fields.USER_PASSWORD);
            String firstName = request.getParameter(Fields.CLIENT_FIRST_NAME);
            String lastName = request.getParameter(Fields.CLIENT_LAST_NAME);

            boolean valid = ProfileInputValidator.validateUserParameters(firstName,
                    lastName, email, password);

            LOG.trace(valid);

            if (!valid) {
                LOG.error("errorMessage: Not all fields are filled");
                return new Response("{\"errorEng\":\"Please fill all fields properly.\"," +
                        "\"errorRu\":\"Пожалуйста, заполните все поля правильно.\"}", ActionType.AJAX);
            } else {
                User user = new User();
                user.setPassword(password);
                user.setEmail(email);
                user.setRole(Role.CLIENT.toString());

                ClientDao clientDao = new ClientDao();
                Client client = (Client) clientDao.find(new FindClientByUserEmailCriteria(user.getEmail())).getData();
//                User user = new User(email, password, firstName, lastName,
//                        Role.CLIENT);
                UserDao userDAO = new UserDao();
                User user1 = (User) userDAO.find(new FindUserByEmailCriteria(user.getEmail())).getData();
                if(user1!=null){
                    return new Response("{\"errorEng\":\" There is already user with such email.\"," +
                            "\"errorRu\":\" Уже есть пользователь с таким эл. адресом.\"}", ActionType.AJAX);
                }
                MailDao mailDAO = new MailDao();
                String key = MailUtils.sendConfirmationEmail(client, user);
                Mail mail = new Mail();
                mail.setMailId(email + password);
                mail.setKey(key);
                mailDAO.insert(new InsertMail(mail));
            }
            return new Response("{\"errorEng\":\"none\"}", ActionType.AJAX);
        } catch (MessagingException | DAOException e) {
            throw new CommandException(e);
        }
    }
}
