package org.bukkit.craftbukkit.entity;

import net.minecraft.server.EntityChicken;
import io.lilybukkit.lilybukkit3.CraftServer;
import org.bukkit.entity.Chicken;


public class CraftChicken extends CraftAnimals implements Chicken {

    public CraftChicken(CraftServer server, EntityChicken entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "CraftChicken";
    }

}
