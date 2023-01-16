package com.stupak.payments.controller.command;

import com.stupak.payments.controller.command.admin.ProfileCommand;
import com.stupak.payments.controller.command.admin.ShowUserListCommand;
import com.stupak.payments.controller.command.client.*;
import com.stupak.payments.controller.command.common.LogoutCommand;
import com.stupak.payments.controller.command.main.LoginCommand;

import javax.servlet.http.HttpServletRequest;
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

        //common
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());

        //admin
        commands.put("users", new ShowUserListCommand());
        commands.put("profile", new ProfileCommand());

        // client commands
        commands.put("account", new PersonalAccountCommand());
        commands.put("transactions", new TransactionCommand());
        commands.put("services", new ServicesCommand());
        commands.put("payment", new PaymentCommand());
        commands.put("block_user", new BlockAccountCommand());
    }

    public ICommand getCommand(HttpServletRequest request) {
        String action = request.getParameter("action");
        return commands.get(action);
    }
}
