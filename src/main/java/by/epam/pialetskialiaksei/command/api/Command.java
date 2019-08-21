package by.epam.pialetskialiaksei.command.api;

import by.epam.pialetskialiaksei.exception.CommandException;
import by.epam.pialetskialiaksei.util.Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command{
    Response execute(HttpServletRequest request,
                     HttpServletResponse response)
            throws IOException, ServletException, CommandException;

}
