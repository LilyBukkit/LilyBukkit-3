package org.bukkit.craftbukkit.entity;

import net.minecraft.server.EntityGhast;
import io.lilybukkit.lilybukkit3.CraftServer;
import org.bukkit.entity.Ghast;

public class CraftGhast extends CraftFlying implements Ghast {

    public CraftGhast(CraftServer server, EntityGhast entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "CraftGhast";
    }

}
