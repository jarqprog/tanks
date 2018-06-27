package com.tanksDevs.network.parser;

import com.tanksDevs.system.entity.Entity;
import com.tanksDevs.system.pojo.EntityPojo;

public interface PojoParser {

    <T extends Entity, P extends EntityPojo> T parse(P pojo);
    <T extends Entity, P extends EntityPojo> P parse(T entity);

}
