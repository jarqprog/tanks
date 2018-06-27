package com.tanksDevs.system.bulletPool;

import com.tanksDevs.system.entity.bullet.Bullet;
import com.tanksDevs.system.entity.bullet.SimpleBullet;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

public class BulletBankTest {

    private BulletPool pool;

    @Before
    public void setup() {

        Collection<Bullet> bullets = Arrays.asList(new Bullet[] {new SimpleBullet(), new SimpleBullet(),
                new SimpleBullet()});

        pool = new BulletBank(bullets);
    }



    @Test
    public void borrow() {

        Bullet bullet = pool.borrow();
        int expectedCapacity = 2;

        assertNotNull(bullet);
        assertEquals(expectedCapacity, pool.available());
        assertTrue(pool.isAvailable());

    }

    @Test(expected = IllegalStateException.class)
    public void borrow_too_much() throws IllegalStateException {

        pool.borrow();
        pool.borrow();

        assertEquals(1, pool.available());
        assertTrue(pool.isAvailable());

        pool.borrow();
        pool.borrow();

    }

    @Test
    public void restore() {


        Bullet first = pool.borrow();
        Bullet second = pool.borrow();
        Bullet third = pool.borrow();

        assertEquals(0, pool.available());
        assertFalse(pool.isAvailable());

        pool.restore(first);

        assertEquals(1, pool.available());

        pool.restore(second);

        assertEquals(2, pool.available());


        pool.restore(third);

        assertEquals(3, pool.available());
    }

    @Test
    public void isAvailable_if_full() {

        assertTrue(pool.isAvailable());
    }

    @Test
    public void available_if_full() {

        int expected = 3;

        assertEquals(expected, pool.available());

    }


}