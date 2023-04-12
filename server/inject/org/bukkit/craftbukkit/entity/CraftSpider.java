package org.bukkit.craftbukkit.entity;

import net.minecraft.server.EntitySpider;

import io.lilybukkit.lilybukkit3.CraftServer;
import org.bukkit.entity.Spider;

public class CraftSpider extends CraftMonster implements Spider {

    public CraftSpider(CraftServer server, EntitySpider entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "CraftSpider";
    }

}
