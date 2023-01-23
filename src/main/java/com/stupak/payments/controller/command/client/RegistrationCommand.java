package com.stupak.payments.controller.command.client;

import com.stupak.payments.appcontext.AppContext;
import com.stupak.payments.controller.Path;
import com.stupak.payments.controller.command.ICommand;
import com.stupak.payments.model.entity.Account;
import com.stupak.payments.model.entity.ContactDetails;
import com.stupak.payments.model.entity.Role;
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
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

        if(session.getAttribute("firstName") == null){
            String forward = Path.PAGE_REGISTRATION;
            return forward;
        }

        String firstName = session.getAttribute("firstName").toString().trim();
        String lastName = session.getAttribute("middleName").toString().trim();
        String surname = session.getAttribute("lastName").toString().trim();
        String login = session.getAttribute("login").toString().trim();
        String password = session.getAttribute("password").toString().trim();

        String city = session.getAttribute("city").toString().trim();
        String street = session.getAttribute("street").toString().trim();
        String home = session.getAttribute("house").toString().trim();
        String apartment = session.getAttribute("apartment").toString().trim();
        String email = session.getAttribute("email").toString().trim();
        String phone = session.getAttribute("phone").toString().trim();

        session.removeAttribute("firstName");
        session.removeAttribute("middleName");
        session.removeAttribute("surname");
        session.removeAttribute("login");
        session.removeAttribute("password");
        session.removeAttribute("city");
        session.removeAttribute("street");
        session.removeAttribute("house");
        session.removeAttribute("apartment");
        session.removeAttribute("email");
        session.removeAttribute("phone");

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

        newUser = userService.findByLogin(login);

        int name_number = 1;
        createAccount(accountService, newUser.getId(), name_number);
        name_number++;
        createAccount(accountService, newUser.getId(), name_number);

        session.setAttribute("user", newUser);
        session.setAttribute("userRole", Role.getRole(newUser));

        String forward = Path.COMMAND_ACCOUNT;
        try {
            resp.sendRedirect(forward);
            forward = Path.COMMAND_REDIRECT;
        } catch (
                IOException e) {
            forward = Path.PAGE_ERROR_PAGE;
        }
        return forward;
    }

    private void createAccount(IAccountService accountService, long id, Integer name_number) {
        Account account = new Account();
        account.setId(accountService.getNextIdValue());
        account.setNumber(accountService.getNumberContract());
        account.setAccountName("Account " + name_number);
        account.setBalance(BigDecimal.ZERO);
        account.setUserId(id);
        account.setBlocked(false);
        account.setUnblockReq(false);
        accountService.save(account);
    }
}
