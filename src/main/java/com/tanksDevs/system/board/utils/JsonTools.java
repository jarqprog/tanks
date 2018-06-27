package com.tanksDevs.system.board.utils;

import java.util.List;

public interface JsonTools {
    <T> List<T> getJsonElementsAsArray(String jsonString, Class<T> objectType, int listSize);
}
