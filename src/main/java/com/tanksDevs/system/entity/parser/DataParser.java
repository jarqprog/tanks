package com.tanksDevs.system.entity.parser;

import com.tanksDevs.system.entity.Entity;
import com.tanksDevs.system.pojo.EntityPojo;

import java.util.Set;

public interface DataParser {
    Set<EntityPojo> parseMapToPojo();

    Set<Entity> parsePojosToEntities(Set<EntityPojo> pojos);
}
