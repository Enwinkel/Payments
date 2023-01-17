package com.stupak.payments.controller.command.client;

import com.stupak.payments.appcontext.AppContext;
import com.stupak.payments.controller.Path;
import com.stupak.payments.controller.command.ICommand;
import com.stupak.payments.model.entity.Account;
import com.stupak.payments.model.entity.ContactDetails;
import com.stupak.payments.model.entity.User;
import com.stupak.payments.model.service.IAccountService;
import com.stupak.payments.model.service.IContactDetailsService;
import com.stupak.payments.model.service.IUserService;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

public class RegistrationCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        if(request.getParameter("firstName") == null){
            String forward = Path.PAGE_REGISTRATION;
            return forward;
        }

        String firstName = request.getParameter("firstName").trim();
        String lastName = request.getParameter("lastName").trim();
        String surname = request.getParameter("surname").trim();
        String login = request.getParameter("login").trim();
        String password = request.getParameter("password").trim();

        String city = request.getParameter("city").trim();
        String street = request.getParameter("street").trim();
        String home = request.getParameter("home").trim();
        String apartment = request.getParameter("apartment").trim();
        String email = request.getParameter("email").trim();
        String phone = request.getParameter("phone").trim();

        IUserService userService = AppContext.getInstance().getUserService();
        IContactDetailsService detailsService = AppContext.getInstance().getDetailsService();
        IAccountService accountService = AppContext.getInstance().getAccountService();

        ContactDetails details = new ContactDetails();
        details.setId(detailsService.getNextIdValue());
        details.setCity(city);
        details.setStreet(street);
        details.setHome(home);
        details.setApartment(apartment);
        details.setEmail(email);
        details.setPhone(phone);
        detailsService.save(details);

        User newUser = new User();
        newUser.setLogin(login);
        newUser.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        newUser.setSurname(surname);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setRoleId(2);
        newUser.setBlocked(false);
        newUser.setDetails(details);
        userService.save(newUser);

        long id = userService.findByLogin(login).getId();

        createAccount(accountService, id);
        createAccount(accountService, id);


        HttpSession session = request.getSession();
        session.setAttribute("user", newUser);

        String forward = Path.COMMAND_ACCOUNT;
        try {
            response.sendRedirect(forward);
            forward = Path.COMMAND_REDIRECT;
        } catch (
                IOException e) {
            forward = Path.PAGE_ERROR_PAGE;
        }
        return forward;
    }

    private void createAccount(IAccountService accountService, long id) {
        Account account = new Account();
        account.setId(accountService.getNextIdValue());
        account.setNumber(accountService.getNumberContract());
        account.setBalance(BigDecimal.ZERO);
        account.setUserId(id);
        account.setBlocked(false);
        account.setUnblockReq(false);
        accountService.save(account);
    }
}
