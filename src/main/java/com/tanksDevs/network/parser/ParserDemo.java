package com.tanksDevs.network.parser;

import com.tanksDevs.system.entity.Genre;
import com.tanksDevs.system.entity.forest.Forest;
import com.tanksDevs.system.entity.forest.ForestPojo;
import com.tanksDevs.system.entity.forest.SimpleForestPojo;

public class ParserDemo {


    public static void main(String[] args) {


        PojoParser parser = new NaivePojoParser();

        ForestPojo forestPojo = new SimpleForestPojo();
        forestPojo.setId(1);
        forestPojo.setX(10.1);
        forestPojo.setY(10.7);
        forestPojo.setSize(100);
        forestPojo.setGenre(Genre.FOREST);



        Forest forest = parser.parse(forestPojo);

        System.out.println(forest);


    }
}
