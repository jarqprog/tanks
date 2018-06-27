package com.tanksDevs.system.board.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class JsonReader implements JsonTools{

    public <T> List<T> getJsonElementsAsArray(String jsonString, Class<T> objectType, int listSize) {
        List<T> list = new ArrayList<>(listSize);
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonElements = jsonParser.parse(jsonString).getAsJsonArray();

        for (int i = 0; i < jsonElements.size(); i++) {
            list.add(gson.fromJson(jsonElements.get(i), objectType));
        }
        return list;
    }

}
