package com.anton.day6.model.validator;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class BookValidator {
    public boolean validatePublishYear(String publishYear) {
        boolean flag = true;
        try {
            int year = Integer.parseInt(publishYear);
            if(year < 0 || year > Calendar.getInstance().get(Calendar.YEAR)) {
                flag = false;
            }
        } catch (NumberFormatException ex) {
            flag = false;
        }
        return flag;
    }

    public boolean validateStringData(String data) {
        String illegalCharacters = "(.*):;%&<>\\{}\\[]#*+=(.*)";
        return !data.matches(illegalCharacters);
    }

    public boolean validateAuthors(String authorsString) {
        boolean flag = true;
        String comma = ", ";
        List<String> authors = Arrays.asList(authorsString.split(comma));
        if(authors.size() > 10) {
            flag = false;
        }
        return flag;
    }
}
