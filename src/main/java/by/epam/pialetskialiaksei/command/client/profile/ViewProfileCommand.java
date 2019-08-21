package by.epam.pialetskialiaksei.command.client.profile;

import by.epam.pialetskialiaksei.Path;
import by.epam.pialetskialiaksei.command.api.Command;
import by.epam.pialetskialiaksei.entity.*;
import by.epam.pialetskialiaksei.exception.CommandException;
import by.epam.pialetskialiaksei.exception.DAOException;
import by.epam.pialetskialiaksei.sql.DAO.ClientDao;
import by.epam.pialetskialiaksei.sql.DAO.FriendDAO;
import by.epam.pialetskialiaksei.sql.DAO.UserDao;
import by.epam.pialetskialiaksei.sql.сriteria.client.FindAllClientsCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.client.FindClientByUserEmailCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.friend.FindFriendsByEmailCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.friend.api.FriendCriteria;
import by.epam.pialetskialiaksei.sql.сriteria.user.FindUserByEmailCriteria;
import by.epam.pialetskialiaksei.util.ActionType;
import by.epam.pialetskialiaksei.util.DTO;
import by.epam.pialetskialiaksei.util.Dummy;
import by.epam.pialetskialiaksei.util.Response;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewProfileCommand implements Command {

    private static final long VersionUID = -3071536593627692473L;

    private static final Logger LOG = LogManager.getLogger(ViewProfileCommand.class);

    @Override
    public Response execute(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException, ServletException, CommandException {
        LOG.debug("Command execution starts");
        try {
            Response result = null;

            HttpSession session = request.getSession(false);
            String userEmail = String.valueOf(session.getAttribute("email"));
            UserDao userDao = new UserDao();
            User user = (User) userDao.find(new FindUserByEmailCriteria(userEmail)).getData();

            Client client = (Client) new ClientDao().find(new FindClientByUserEmailCriteria(user.getEmail())).getData();

            DTO dto =  new FriendDAO().find(new FindFriendsByEmailCriteria(userEmail));
            List<Client> friends = new ArrayList<>();
            if(!(dto.getData() instanceof Dummy)){
                friends = (List<Client>) dto.getData();
            }

            request.setAttribute("first_name", client.getFirstName());
            LOG.trace("Set the request attribute: 'first_name' = "
                    + client.getFirstName());
            request.setAttribute("last_name", client.getLastName());
            LOG.trace("Set the request attribute: 'last_name' = "
                    + client.getLastName());
            request.setAttribute("friends", friends);
            LOG.trace("Set the request attribute: 'friends' = "
                    + friends);
            request.setAttribute("role", user.getRole());
            LOG.trace("Set the request attribute: 'role' = " + user.getRole());

            String role = user.getRole();

            if ("client".equals(role)) {
//                EntrantDAO entrantDAO = new EntrantDAO();
//                Entrant entrant = entrantDAO.find(user);

                request.setAttribute("about", client.getAbout());
                LOG.trace("Set the request attribute: 'city' = "
                        + client.getAbout());
//                request.setAttribute("district", entrant.getDistrict());
//                LOG.trace("Set the request attribute: 'district' = "
//                        + entrant.getDistrict());
//                request.setAttribute("school", entrant.getSchool());
//                LOG.trace("Set the request attribute: 'school' = "
//                        + entrant.getSchool());
//                request.setAttribute("diploma", entrant.getDiplomaMark());
//                LOG.trace("Set the request attribute: 'diploma' = "
//                        + entrant.getDiplomaMark());

//                MarkDAO markDAO = new MarkDAO();
//                List<ClientSubject> marks = markDAO.findMarks(entrant);
//                Gson gson = new Gson();
//                ClientSubject[] marksArray = marks.toArray(new ClientSubject[marks.size()]);
//                request.setAttribute("jsonMarks", gson.toJson(marksArray));
//                request.setAttribute("marks", marks);
//                LOG.trace("Set the request attribute: 'marks' = "
//                        + marks);
//
//                if (results) {
//                    FormOfEducation formOfEducation = reportSheetDAO.getFormOfEducation(user);
//                    if (formOfEducation != null) {
//                        request.setAttribute("formOfEducation", formOfEducation);
//                    }
//                }
//
//                FacultyEntrantDAO facultyEntrantDAO = new FacultyEntrantDAO();
//                Faculty faculty = facultyEntrantDAO.find(entrant.getId());
//                if (faculty != null) {
//                    request.setAttribute("faculty", faculty);
//                }

                result = new Response(Path.FORWARD_CLIENT_PROFILE, ActionType.GET);
            } else if ("admin".equals(role)) {
                result = new Response(Path.FORWARD_ADMIN_PROFILE, ActionType.GET);
            }
            return result;
        } catch (DAOException e) {
            throw new CommandException(e);
        }
    }
}