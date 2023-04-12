package org.bukkit.craftbukkit.entity;

import net.minecraft.server.EntityFallingSand;

import io.lilybukkit.lilybukkit3.CraftServer;
import org.bukkit.entity.FallingSand;

public class CraftFallingSand extends CraftEntity implements FallingSand {

    public CraftFallingSand(CraftServer server, EntityFallingSand entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "CraftFallingSand";
    }

}
