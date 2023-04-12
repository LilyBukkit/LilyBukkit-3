package org.bukkit.craftbukkit.entity;

import net.minecraft.server.EntitySlime;

import io.lilybukkit.lilybukkit3.CraftServer;
import org.bukkit.entity.Slime;

public class CraftSlime extends CraftLivingEntity implements Slime {

    public CraftSlime(CraftServer server, EntitySlime entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "CraftSlime";
    }

    public EntitySlime getHandle() {
        return (EntitySlime) super.getHandle();
    }

    public int getSize() {
        return getHandle().getSize();
    }

    public void setSize(int size) {
        getHandle().setSize(size);
    }
}
