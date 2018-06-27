package com.tanksDevs.system.idgenerator;

public class BasicIDGenerator{

    private static int ID = 0;

    public static int getNewID(){
        return ID++;
    }
}
