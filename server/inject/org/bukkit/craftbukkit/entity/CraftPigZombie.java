package org.bukkit.craftbukkit.entity;

import net.minecraft.server.EntityPigZombie;

import io.lilybukkit.lilybukkit3.CraftServer;
import org.bukkit.entity.PigZombie;

public class CraftPigZombie extends CraftZombie implements PigZombie {

    public CraftPigZombie(CraftServer server, EntityPigZombie entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "CraftPigZombie";
    }

}
