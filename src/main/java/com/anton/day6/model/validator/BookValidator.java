package com.anton.day6.model.validator;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookValidator {
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
            String illegalCharacters = "[<>#=&%]";
            Pattern pattern = Pattern.compile(illegalCharacters);
            Matcher matcher = pattern.matcher(data);
            if (!matcher.find()) {
                flag = true;
            }
        }
        return flag;
    }

    public boolean validateAuthors(String authorsString) {
        boolean flag = false;
        if (authorsString != null && !authorsString.isEmpty()) {
            String comma = ", ";
            List<String> authors = Arrays.asList(authorsString.split(comma));
            if (authors.size() <= 10) {
                flag = true;
            }
        }
        return flag;
    }
}
