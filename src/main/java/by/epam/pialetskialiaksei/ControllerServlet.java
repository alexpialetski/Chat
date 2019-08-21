package by.epam.pialetskialiaksei;

import by.epam.pialetskialiaksei.command.CommandManager;
import by.epam.pialetskialiaksei.command.api.Command;
import by.epam.pialetskialiaksei.exception.CommandException;
import by.epam.pialetskialiaksei.util.ActionType;
import by.epam.pialetskialiaksei.util.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
    private static final long VersionUID = 1L;

    private static final Logger LOGGER = LogManager.getLogger();


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
//        String type = request.getParameter("type");
//        LOGGER.trace("Type of command: " + type);
//        if (type == null) {
//            process(request, response, ActionType.GET);
//        } else {
//            process(request, response, ActionType.valueOf(type));
//        }
        process(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
//        process(request, response, ActionType.POST);
        process(request, response);
    }

    private void process(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {

        LOGGER.debug("Start processing in Controller");

        String commandName = request.getParameter("command");
        LOGGER.trace("Request parameter: 'command' = " + commandName);

        Command command = CommandManager.get(commandName);
        LOGGER.trace("Obtained 'command' = " + command);

        Response path;
        try {
            if (determineActionType(request) != ActionType.AJAX) {
//                request.getSession().setAttribute("prevCommand", commandName);
                request.getSession().setAttribute("prevCommand", commandName);
            }
            path = command.execute(request, response);
            switch (path.getActionType()) {
                case AJAX:
                    LOGGER.trace("AJAX with json= " + path);
                    LOGGER.debug("Controller proccessing finished");
                    PrintWriter writer = response.getWriter();
                    writer.print(path.getSource());
                    break;
                case GET:
                    LOGGER.trace("Forward to address = " + path);
                    LOGGER.debug("Controller proccessing finished");
                    RequestDispatcher disp = request.getRequestDispatcher(path.getSource());
                    disp.forward(request, response);
                    break;
                case POST:
                    LOGGER.trace("Redirect to address = " + path);
                    LOGGER.debug("Controller proccessing finished");
                    response.sendRedirect(path.getSource());
                    break;
                default:
                    LOGGER.trace("Path is null");
                    LOGGER.debug("Controller proccessing finished");
                    RequestDispatcher dispatcher = request.getRequestDispatcher(Path.WELCOME_PAGE);
                    dispatcher.forward(request, response);
                    break;
            }
//            if (path == null) {
//                LOGGER.trace("Path is null");
//                LOGGER.debug("Controller proccessing finished");
//                RequestDispatcher disp = request.getRequestDispatcher(Path.WELCOME_PAGE);
//                disp.forward(request, response);
//            } else {
//                if (actionType == ActionType.GET) {
//                    LOGGER.trace("Forward to address = " + path);
//                    LOGGER.debug("Controller proccessing finished");
//                    RequestDispatcher disp = request.getRequestDispatcher(path);
//                    disp.forward(request, response);
//                } else if (actionType == ActionType.AJAX) {
//                    LOGGER.trace("AJAX with json= " + path);
//                    LOGGER.debug("Controller proccessing finished");
//                    PrintWriter writer = response.getWriter();
//                    writer.print(path);
//                } else if (actionType == ActionType.POST) {
//                    LOGGER.trace("Redirect to address = " + path);
//                    LOGGER.debug("Controller proccessing finished");
//                    response.sendRedirect(path);
//                }
//            }
        } catch (CommandException | Exception e) {
            LOGGER.error("Exception is:", e);
            request.setAttribute("errorMessage", "Something going wrong, go to start - page");
            request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        LOGGER.debug(this.getServletInfo() + "is destroyed");

    }

    @Override
    public void init() throws ServletException {
        super.init();
        LOGGER.debug(this.getServletInfo() + "is initialized");
    }

    private ActionType determineActionType(HttpServletRequest request){
        String type = request.getParameter("type");
        LOGGER.trace("Type of command: " + type);
        if (type == null) {
            String method = request.getMethod();
            if(method.equals("POST")){
                return ActionType.POST;
            }else{
                return ActionType.GET;
            }
        } else {
            return ActionType.valueOf(type);
        }
    }
}
