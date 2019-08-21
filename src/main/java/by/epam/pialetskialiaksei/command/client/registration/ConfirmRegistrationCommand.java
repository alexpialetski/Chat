package by.epam.pialetskialiaksei.command.client.registration;

import by.epam.pialetskialiaksei.Fields;
import by.epam.pialetskialiaksei.Path;
import by.epam.pialetskialiaksei.command.api.Command;
import by.epam.pialetskialiaksei.entity.Mail;
import by.epam.pialetskialiaksei.exception.CommandException;
import by.epam.pialetskialiaksei.sql.DAO.MailDao;
import by.epam.pialetskialiaksei.sql.сriteria.mail.DeleteMailByMailId;
import by.epam.pialetskialiaksei.sql.сriteria.mail.FindMailByMailId;
import by.epam.pialetskialiaksei.util.ActionType;
import by.epam.pialetskialiaksei.util.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfirmRegistrationCommand implements Command {

    private static final long VersionUID = -3071536593627692473L;

    private static final Logger LOG = LogManager.getLogger(ConfirmRegistrationCommand.class);


    @Override
    public Response execute(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException, ServletException, CommandException {
        LOG.debug("Start executing Command");
        try {
            String email = request.getParameter(Fields.USER_EMAIL);
            String password = request.getParameter(Fields.USER_PASSWORD);
            String userKey = request.getParameter("key");

            MailDao mailDAO = new MailDao();
            Mail mail = new Mail();
            mail.setMailId(email + password);
            Mail dbMail = (Mail) mailDAO.find(new FindMailByMailId(mail)).getData();

            if (dbMail.getKey() != null && userKey.equals(dbMail.getKey())) {
                mailDAO.delete(new DeleteMailByMailId(mail));
                return new Response("{\"errorEng\":\"none\"}", ActionType.AJAX);
            } else {
                mailDAO.delete(new DeleteMailByMailId(mail));
                return new Response("{\"errorEng\":\"Key is not valid\"," +
                        "\"errorRu\":\"Ключ не верен\"}", ActionType.AJAX);
            }
        } catch (Exception e) {
            throw new CommandException(e);
        }
    }
}
