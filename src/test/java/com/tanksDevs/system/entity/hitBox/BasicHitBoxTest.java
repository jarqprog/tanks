package com.tanksDevs.system.entity.hitBox;

import org.junit.Test;

import static org.junit.Assert.*;

public class BasicHitBoxTest {

    @Test
    public void checkIfNotOverlapping(){
        HitBox hitBox = new BasicHitBox(0, 0, 4);
        HitBox hitBox1 = new BasicHitBox(10, 10, 4);
        assertFalse(hitBox.checkCollision(hitBox1));
    }

    @Test
    public void checkIfOverlapping(){
        HitBox hitBox = new BasicHitBox(0, 0, 4);
        HitBox hitBox1 = new BasicHitBox(2, 2, 4);
        assertTrue(hitBox.checkCollision(hitBox1));
    }

    @Test
    public void checkIfOverlappingInPoint(){
        HitBox hitBox = new BasicHitBox(0, 0, 4);
        HitBox hitBox1 = new BasicHitBox(4, 4, 4);
        assertTrue(hitBox.checkCollision(hitBox1));
    }
}