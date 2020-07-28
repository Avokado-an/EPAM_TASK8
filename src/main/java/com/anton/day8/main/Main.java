package com.anton.day8.main;

import com.anton.day8.controller.invoker.Invoker;
import com.anton.day8.controller.responce.ResponseParameters;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> params = new HashMap<>();
        String publishYear = "2000";
        String name = "new";
        String publisher = "new";
        String authors = "newAuthor1";
        params.put(ResponseParameters.PUBLISH_YEAR, publishYear);
        params.put(ResponseParameters.NAME, name);
        params.put(ResponseParameters.PUBLISHER, publisher);
        params.put(ResponseParameters.AUTHORS, authors);
        System.out.println(Invoker.getInstance().doRequest(
                ResponseParameters.FIND + ResponseParameters.PUBLISHER, params));
    }
}
