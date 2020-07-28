package com.anton.day8.model.validator;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookValidator {
    private static final int MAX_STRING_LENGTH = 50;
    private static final String ILLEGAL_CHARACTERS = "[<>#=&%]";
    private static final String COMMA = ", ";

    public boolean validatePublishYear(String publishYear) {
        boolean flag = false;
        if (publishYear != null) {
            try {
                int year = Integer.parseInt(publishYear);
                flag = year >= 0 && year <= Calendar.getInstance().get(Calendar.YEAR);
            } catch (NumberFormatException ex) {
                flag = false;
            }
        }
        return flag;
    }

    public boolean validateStringData(String data) {
        boolean flag = false;
        if (data != null && !data.isEmpty()) {
            Pattern pattern = Pattern.compile(ILLEGAL_CHARACTERS);
            Matcher matcher = pattern.matcher(data);
            if (!matcher.find() && data.length() < MAX_STRING_LENGTH) {
                flag = true;
            }
        }
        return flag;
    }

    public boolean validateAuthors(String authorsString) {
        boolean flag = false;
        if (authorsString != null && !authorsString.isEmpty()) {
            List<String> authors = Arrays.asList(authorsString.split(COMMA));
            if (authors.size() <= 10) {
                flag = true;
            }
        }
        return flag;
    }
}
