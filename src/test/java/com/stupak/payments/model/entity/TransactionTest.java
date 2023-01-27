package com.stupak.payments.model.entity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {

    @InjectMocks
    Transaction transaction;
    static LocalDateTime dateTime;


    @Before
    public void setUp() {
        dateTime = LocalDateTime.parse("2023-02-16T10:22:15");
        MockitoAnnotations.openMocks(this);
        transaction.setTimestamp(dateTime);
    }

    @Test
    public void getDate() {
        assertEquals(transaction.getDate(), "16.02.2023 10:22");
    }
}