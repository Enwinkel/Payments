package com.stupak.payments.controller.command.main;

import com.stupak.payments.controller.Path;
import com.stupak.payments.appcontext.AppContext;
import com.stupak.payments.controller.command.ICommand;
import com.stupak.payments.model.entity.Role;
import com.stupak.payments.model.entity.User;
import com.stupak.payments.model.service.IUserService;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ICommand {

    IUserService service = AppContext.getInstance().getUserService();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        // error handler
        String errorMessage;
        String forward = Path.PAGE_LOGIN;

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            errorMessage = "Login or password can't be empty";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }

        User user = service.findByLogin(login);
        if (user.getLogin() == null || !BCrypt.checkpw(password, user.getPassword())) {
            errorMessage = "Cannot find user with such login or password";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        } else {
            Role userRole = Role.getRole(user);

            if (userRole == Role.ADMIN) {
                forward = Path.COMMAND_SHOW_USERS;
            }

            if (userRole == Role.CLIENT) {
                forward = Path.COMMAND_ACCOUNT;
            }

            session.setAttribute("user", user);
            session.setAttribute("userRole", userRole);
        }

        return forward;
    }
}
