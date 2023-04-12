package org.bukkit.craftbukkit.entity;

import io.lilybukkit.lilybukkit3.CraftServer;
import org.bukkit.entity.Vehicle;

/**
 * A vehicle.
 *
 * @author sk89q
 */
public abstract class CraftVehicle extends CraftEntity implements Vehicle {
    public CraftVehicle(CraftServer server, net.minecraft.server.Entity entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "CraftVehicle{passenger=" + getPassenger() + '}';
    }
}
