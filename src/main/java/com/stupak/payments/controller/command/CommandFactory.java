package com.stupak.payments.controller.command;

import com.stupak.payments.controller.command.admin.BlockUserCommand;
import com.stupak.payments.controller.command.admin.ProfileCommand;
import com.stupak.payments.controller.command.admin.RequestCommand;
import com.stupak.payments.controller.command.admin.ShowUserListCommand;
import com.stupak.payments.controller.command.client.*;
import com.stupak.payments.controller.command.common.LocalizationCommand;
import com.stupak.payments.controller.command.common.LogoutCommand;
import com.stupak.payments.controller.command.common.LoginCommand;

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
        commands.put("localization", new LocalizationCommand());
        commands.put("registration", new RegistrationCommand());

        //admin
        commands.put("users", new ShowUserListCommand());
        commands.put("profile", new ProfileCommand());
        commands.put("requests", new RequestCommand());
        commands.put("block_user", new BlockUserCommand());

        // client commands
        commands.put("account", new AccountCommand());
        commands.put("transactions", new TransactionCommand());
        commands.put("services", new ServicesCommand());
        commands.put("payment", new PaymentCommand());
        commands.put("top_up", new TopUpCommand());
        commands.put("block_account", new BlockAccountCommand());
    }

    public ICommand getCommand(HttpServletRequest request) {
        String action = request.getParameter("action");
        return commands.get(action);
    }
}
