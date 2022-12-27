package controller.command;

import controller.command.main.LoginCommand;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static CommandFactory factory;
    private static Map<String, ICommand> commands = new HashMap<>();

    private CommandFactory() {}

    public static CommandFactory commandFactory() {
        if (factory == null) {
            factory = new CommandFactory();
        }
        return factory;
    }

    static {
        commands.put("login", new LoginCommand());
    }

    public ICommand getCommand(HttpServletRequest request) {
        String action = request.getParameter("action");
        return commands.get(action);
    }
}
