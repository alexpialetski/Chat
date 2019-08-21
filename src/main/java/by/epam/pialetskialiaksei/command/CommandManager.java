package by.epam.pialetskialiaksei.command;

import by.epam.pialetskialiaksei.command.api.Command;
import by.epam.pialetskialiaksei.command.client.ViewWelcomeCommand;
import by.epam.pialetskialiaksei.command.client.client.ViewAllClientsCommand;
import by.epam.pialetskialiaksei.command.client.client.ViewClientCommand;
import by.epam.pialetskialiaksei.command.client.friend.BreakFriendsCommand;
import by.epam.pialetskialiaksei.command.client.friend.MakeFriendsCommand;
import by.epam.pialetskialiaksei.command.client.login.LoginCommand;
import by.epam.pialetskialiaksei.command.client.login.LogoutCommand;
import by.epam.pialetskialiaksei.command.client.login.ViewLoginCommand;
import by.epam.pialetskialiaksei.command.client.profile.ViewProfileCommand;
import by.epam.pialetskialiaksei.command.client.registration.ClientRegistrationCommand;
import by.epam.pialetskialiaksei.command.client.registration.ConfirmRegistrationCommand;
import by.epam.pialetskialiaksei.command.client.registration.SendConfirmationRegistrationCommand;
import by.epam.pialetskialiaksei.command.client.registration.ViewRegistrationCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private static final Logger LOG = LogManager.getLogger(CommandManager.class);

    private static Map<String, Command> commands = new HashMap<String, Command>();
    static {
        commands.put("login", new LoginCommand());
        commands.put("viewLogin", new ViewLoginCommand());
        commands.put("viewWelcome", new ViewWelcomeCommand());
        commands.put("viewProfile", new ViewProfileCommand());
        commands.put("client_registration", new ClientRegistrationCommand());
        commands.put("view_registration", new ViewRegistrationCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("sendConfirmation", new SendConfirmationRegistrationCommand());
        commands.put("confirm", new ConfirmRegistrationCommand());
        commands.put("viewClients", new ViewAllClientsCommand());
        commands.put("viewClient", new ViewClientCommand());
        commands.put("makeFriends", new MakeFriendsCommand());
        commands.put("breakFriends", new BreakFriendsCommand());
    }
    public static Command get(String commandName) {
        return commands.get(commandName);
    }
}
