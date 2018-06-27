package com.tanksDevs.system.bulletPool;

import com.tanksDevs.system.entity.bullet.Bullet;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class BulletBank implements BulletPool {

    private final Queue<Bullet> available;
    private final Queue<Bullet> borrowed;

    public BulletBank(Collection<Bullet> bullets) {
        int len = bullets.size();

        this.available = new LinkedList<>(bullets);
        this.borrowed = new LinkedList<>();
    }

    @Override
    public synchronized Bullet borrow() {
        if ( ! isAvailable() ) {
            throw new IllegalStateException("Pool is empty!");
        }
        Bullet bullet = available.poll();
        borrowed.add(bullet);
        return bullet;
    }

    @Override
    public synchronized void restore(Bullet bullet) {
        borrowed.remove(bullet);
        available.add(bullet);
    }

    @Override
    public boolean isAvailable() {
        return available.size() > 0;
    }

    @Override
    public int available() {
        return available.size();
    }
}
