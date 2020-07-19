package com.anton.day6.model.validator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class BookValidatorTest {
    BookValidator validator;

    @BeforeClass
    public void setup() {
        validator = new BookValidator();
    }

    @Test
    public void validatePublishYearValidTest() {
        String year = "1400";
        boolean isValidYear = validator.validatePublishYear(year);
        assertTrue(isValidYear);
    }

    @DataProvider(name = "invalidYear")
    public Object[][] provideInvalidYear() {
        return new Object[][]{
                {null}, {"-1234"}, {"2050"}, {""}
        };
    }

    @Test(dataProvider = "invalidYear")
    public void validatePublishYearInvalidTest(String year) {
        boolean isValidYear = validator.validatePublishYear(year);
        assertFalse(isValidYear);
    }

    @Test
    public void validateStringDataValidTest() {
        String str = "hfuhsief3243kfos";
        boolean isValidString = validator.validateStringData(str);
        assertTrue(isValidString);
    }

    @DataProvider(name = "invalidStringData")
    public Object[][] provideInvalidStringData() {
        return new Object[][]{
                {null}, {"asdf<a"}, {"asdf>a"}, {"asdf%a"}, {"asdf&a"}, {"asdf#a"}, {"asdf=a"}, {""}
        };
    }

    @Test(dataProvider = "invalidStringData")
    public void validateStringDataInvalidTest(String data) {
        boolean isValidString = validator.validateStringData(data);
        assertFalse(isValidString);
    }

    @Test
    public void validateAuthorsValidTest() {
        String authors = "A, A, A";
        boolean areValidAuthors = validator.validateAuthors(authors);
        assertTrue(areValidAuthors);
    }

    @DataProvider(name = "invalidAuthorsData")
    public Object[][] provideInvalidAuthorsData() {
        return new Object[][]{
                {null}, {"a, a, a, a, a, a, a, a, a, a, a, a, a, a"}, {""}
        };
    }

    @Test(dataProvider = "invalidAuthorsData")
    public void validateAuthorsInvalidTest(String authors) {
        boolean areValidAuthors = validator.validateAuthors(authors);
        assertFalse(areValidAuthors);
    }
}
