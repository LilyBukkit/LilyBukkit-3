package org.bukkit.craftbukkit.entity;

import net.minecraft.server.EntitySkeleton;

import io.lilybukkit.lilybukkit3.CraftServer;
import org.bukkit.entity.Skeleton;

public class CraftSkeleton extends CraftMonster implements Skeleton {

    public CraftSkeleton(CraftServer server, EntitySkeleton entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "CraftSkeleton";
    }

}
