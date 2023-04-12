package org.bukkit.craftbukkit.entity;

import net.minecraft.server.EntityMinecart;
import io.lilybukkit.lilybukkit3.CraftServer;
import org.bukkit.entity.PoweredMinecart;

/**
 * A powered minecart.
 *
 * @author sk89q
 */
public class CraftPoweredMinecart extends CraftMinecart implements PoweredMinecart {
    public CraftPoweredMinecart(CraftServer server, EntityMinecart entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "CraftPoweredMinecart";
    }

}
