package org.bukkit.craftbukkit.entity;

import net.minecraft.server.EntityGiantZombie;

import io.lilybukkit.lilybukkit3.CraftServer;
import org.bukkit.entity.Giant;

public class CraftGiant extends CraftMonster implements Giant {

    public CraftGiant(CraftServer server, EntityGiantZombie entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "CraftGiant";
    }

}
