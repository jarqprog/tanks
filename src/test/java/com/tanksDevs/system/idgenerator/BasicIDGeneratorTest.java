package com.tanksDevs.system.idgenerator;

import org.junit.Test;

import static org.junit.Assert.*;

public class BasicIDGeneratorTest {

    @Test
    public void checkGettingID(){
        assertEquals(0, BasicIDGenerator.getNewID());
        BasicIDGenerator.getNewID();
        BasicIDGenerator.getNewID();
        assertEquals(3, BasicIDGenerator.getNewID());
    }

}