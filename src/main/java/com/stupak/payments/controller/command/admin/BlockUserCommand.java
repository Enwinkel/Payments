package com.stupak.payments.controller.command.admin;

import com.stupak.payments.appcontext.AppContext;
import com.stupak.payments.controller.Path;
import com.stupak.payments.controller.command.ICommand;
import com.stupak.payments.model.entity.User;
import com.stupak.payments.model.service.IContactDetailsService;
import com.stupak.payments.model.service.IUserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BlockUserCommand implements ICommand {
    IUserService userService = AppContext.getInstance().getUserService();
    IContactDetailsService detailsService = AppContext.getInstance().getDetailsService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        long id;
        String user_id = req.getParameter("user_id");
        if(user_id != null) {
            id = Long.parseLong(user_id);
        } else{
            id = Long.parseLong((String)session.getAttribute("user_id"));
        }
        String forward;

        User user;
        if (id != 0) {
            user = userService.find(id);
            user.setRoleId(user.getRoleId());
            user.setDetails(detailsService.find(user.getDetails().getId()));
            session.setAttribute("user_id", user.getId());
        } else {
            user = (User) session.getAttribute("newUser");
        }

        forward = blockUser(resp, userService, user);

        return forward;
    }

    private String blockUser(HttpServletResponse response, IUserService userService, User user) {
        String resp = Path.COMMAND_TRANSACTIONS;
        if (user.isBlocked()) {
            user.setBlocked(false);
            userService.update(user);
        } else {
            user.setBlocked(true);
            userService.update(user);
        }
        try {
            response.sendRedirect(resp);
            resp = Path.COMMAND_REDIRECT;
        } catch (IOException e) {
            resp = Path.PAGE_ERROR_PAGE;
        }
        return resp;
    }

}