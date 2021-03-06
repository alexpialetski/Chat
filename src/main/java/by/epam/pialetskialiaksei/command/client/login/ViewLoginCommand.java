package by.epam.pialetskialiaksei.command.client.login;

import by.epam.pialetskialiaksei.Path;
import by.epam.pialetskialiaksei.command.api.Command;
import by.epam.pialetskialiaksei.util.ActionType;
import by.epam.pialetskialiaksei.util.Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewLoginCommand implements Command {
    @Override
    public Response execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return new Response(Path.LOGIN_PAGE, ActionType.GET);
    }
}
