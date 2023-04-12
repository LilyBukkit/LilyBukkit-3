package org.bukkit.craftbukkit.entity;

import net.minecraft.server.EntityWaterAnimal;

import io.lilybukkit.lilybukkit3.CraftServer;
import org.bukkit.entity.WaterMob;

public class CraftWaterMob extends CraftCreature implements WaterMob {

    public CraftWaterMob(CraftServer server, EntityWaterAnimal entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "CraftWaterMob";
    }

}
