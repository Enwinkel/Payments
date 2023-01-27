package com.stupak.payments.controller.filter;

import com.stupak.payments.controller.Path;
import com.stupak.payments.model.entity.Role;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class SecurityFilter implements Filter {
  private static final Logger log = Logger.getLogger(SecurityFilter.class);

  // commands access
  private static Map<Role, List<String>> accessMap = new HashMap<>();
  private static List<String> commons = new ArrayList<>();
  private static List<String> outOfControl = new ArrayList<>();

  @Override
  public void init(FilterConfig config) {
    log.debug("Filter initialization starts");

    // roles
    accessMap.put(Role.ADMIN, asList(config.getInitParameter("admin")));
    accessMap.put(Role.CLIENT, asList(config.getInitParameter("client")));
    log.trace("Access map --> " + accessMap);

    // commons
    commons = asList(config.getInitParameter("common"));
    log.trace("Common commands --> " + commons);

    // out of control
    outOfControl = asList(config.getInitParameter("out-of-control"));
    log.trace("Out of control commands --> " + outOfControl);

    log.debug("Filter initialization finished");
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp,
                       FilterChain chain) throws IOException, ServletException {
    log.debug("Filter starts");
    if (accessAllowed(req)) {
      log.debug("Filter finished");
      chain.doFilter(req, resp);
    } else {
      String errorMessages = "You do not have permission to access the requested resource";
      req.setAttribute("errorMessage", errorMessages);

      log.trace("Set the request attribute: errorMessage --> " + errorMessages);

      req.getRequestDispatcher(Path.PAGE_ERROR_PAGE).forward(req, resp);
    }
  }

  private boolean accessAllowed(ServletRequest req) {
    HttpServletRequest httpRequest = (HttpServletRequest) req;

    String commandName = req.getParameter("action");
    if (commandName == null || commandName.isEmpty()) {
      return false;
    }

    if (outOfControl.contains(commandName)) {
      return true;
    }

    HttpSession session = httpRequest.getSession(false);
    if (session == null) {
      return false;
    }

    Role userRole = (Role) session.getAttribute("userRole");
    if (userRole == null) {
      return false;
    }

    return accessMap.get(userRole).contains(commandName) || commons.contains(commandName);
  }

  @Override
  public void destroy() {
    log.debug("Filter destruction starts");
    // do nothing
    log.debug("Filter destruction finished");
  }

  private List<String> asList(String param) {
    List<String> list = new ArrayList<>();
    StringTokenizer st = new StringTokenizer(param);
    while (st.hasMoreTokens()) {
      list.add(st.nextToken());
    }
    return list;
  }
}
