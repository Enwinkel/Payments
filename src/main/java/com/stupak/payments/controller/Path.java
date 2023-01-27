package com.stupak.payments.controller;

public final class Path {
    // pages
    public static final String PAGE_LOGIN = "/login.jsp";
    public static final String PAGE_REGISTRATION = "/WEB-INF/jsp/client/registration.jsp";
    public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
    public static final String PAGE_PROFILE = "/WEB-INF/jsp/admin/profile.jsp";
    public static final String PAGE_ACCOUNT = "/WEB-INF/jsp/client/account.jsp";
    public static final String PAGE_USER_LIST = "/WEB-INF/jsp/admin/users.jsp";
    public static final String PAGE_SERVICES = "/WEB-INF/jsp/client/services.jsp";
    public static final String PAGE_TRANSACTIONS = "/WEB-INF/jsp/client/transactions.jsp";
    public static final String PAGE_PAYMENT = "/WEB-INF/jsp/client/payment.jsp";
    public static final String PAGE_INSUFFICIENT = "/WEB-INF/jsp/client/payment_insufficient.jsp";
    public static final String PAGE_REQUESTS = "/WEB-INF/jsp/admin/requests.jsp";

    // common commands

    public static final String COMMAND_REDIRECT = "redirect";

    // admin commands
    public static final String COMMAND_SHOW_USERS = "controller?action=users";
    public static final String COMMAND_REQUESTS = "controller?action=requests";
    public static final String COMMAND_PROFILE = "controller?action=profile";

    // client commands
    public static final String COMMAND_ACCOUNT = "controller?action=account";
    public static final String COMMAND_SERVICES = "controller?action=services";
    public static final String COMMAND_TRANSACTIONS = "controller?action=transactions";

    // i18n
    public static final String LOCALE_NAME_UA = "ua";
    public static final String LOCALE_NAME_EN = "en";

}
