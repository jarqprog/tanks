package com.tanksDevs.system.bulletPool;

import com.tanksDevs.system.entity.bullet.Bullet;

public interface BulletPool {

    Bullet borrow();
    void restore(Bullet bullet);
    boolean isAvailable();
    int available();
}
