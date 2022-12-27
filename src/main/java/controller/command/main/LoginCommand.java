package controller.command.main;

import controller.Path;
import controller.command.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.Role;
import model.entity.User;

public class LoginCommand implements ICommand {
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

//        User user = service.findByLogin(login);
//        if (user.getLogin() == null || !BCrypt.checkpw(password, user.getPassword())) {
//            errorMessage = "Cannot find user with such login or password";
//            request.setAttribute("errorMessage", errorMessage);
//            return forward;
//        } else {
//            Role userRole = Role.getRole(user);
//
//            if (userRole == Role.ADMIN) {
//                forward = Path.COMMAND_SHOW_USERS;
//            }
//
//            if (userRole == Role.CLIENT) {
//                forward = Path.COMMAND_ACCOUNT;
//            }
//
//            session.setAttribute("user", user);
//            session.setAttribute("userRole", userRole);
//        }

        return forward;
    }
}
