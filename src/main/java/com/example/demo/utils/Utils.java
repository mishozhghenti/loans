package com.example.demo.utils;

import com.example.demo.models.Loan;
import com.example.demo.models.Status;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Utils {


    public static Status verifyLoanRequest(Loan loan) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(loan.getBirthday());

        int score = stringSum(loan.getFirstName()) +
                loan.getSalary().multiply(new BigDecimal("1.5")).intValue() -
                loan.getLiability().multiply(new BigDecimal(3)).intValue() +
                loan.getBirthday().getYear() -
                loan.getBirthday().getMonth() -
                calendar.get(Calendar.DAY_OF_YEAR);

        Status result = Status.MANUAL;

        if (score < 2500) {
            result = Status.REJECTED;
        } else if (score > 3500) {
            result = Status.APPROVED;
        }
        return result;
    }


    private static int stringSum(String name) {
        int sum = 0;
        for (char c : name.toLowerCase().toCharArray()) {
            sum += c - 'a' + 1;
        }
        return sum;
    }
}
