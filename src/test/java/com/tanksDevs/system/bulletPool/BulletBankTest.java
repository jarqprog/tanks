package com.tanksDevs.system.bulletPool;

import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.Destructible;
import com.tanksDevs.system.entity.Direction;
import com.tanksDevs.system.entity.Genre;
import com.tanksDevs.system.entity.bullet.Bullet;
import com.tanksDevs.system.entity.hitBox.HitBox;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

public class BulletBankTest {

    private BulletPool pool;

    @Before
    public void setup() {
        Bullet[] fakes = getFakeBullets();

        Collection<Bullet> bullets = Arrays.asList(fakes);

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


    private Bullet[] getFakeBullets() {
        Bullet fake = new Bullet() {
            @Override
            public void destroy(Destructible target) {

            }

            @Override
            public int getHp() {
                return 0;
            }

            @Override
            public void decrementHp(int hitPoints) {

            }

            @Override
            public boolean isDestroyed() {
                return false;
            }

            @Override
            public int getSpeed() {
                return 0;
            }

            @Override
            public void move(Direction direction) {

            }

            @Override
            public void stop() {

            }

            @Override
            public boolean isCollision(Colliding other) {
                return false;
            }

            @Override
            public HitBox getHitBox() {
                return null;
            }

            @Override
            public int getId() {
                return 0;
            }

            @Override
            public double getX() {
                return 0;
            }

            @Override
            public double getY() {
                return 0;
            }

            @Override
            public double getSize() {
                return 0;
            }

            @Override
            public Genre getGenre() {
                return null;
            }
        };

        return new Bullet[] {fake, fake, fake};
    }


}