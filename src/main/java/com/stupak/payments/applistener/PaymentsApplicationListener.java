package com.stupak.payments.applistener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class PaymentsApplicationListener implements ServletContextListener {

    private final Logger LOGGER = LogManager.getLogger(PaymentsApplicationListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("Webapp 'Payments' was started.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info("Webapp 'Payments' was closed");
    }
}
