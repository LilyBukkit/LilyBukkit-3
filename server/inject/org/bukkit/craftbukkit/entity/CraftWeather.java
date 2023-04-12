
package org.bukkit.craftbukkit.entity;

import net.minecraft.server.EntityWeather;
import io.lilybukkit.lilybukkit3.CraftServer;
import org.bukkit.entity.Weather;

public class CraftWeather extends CraftEntity implements Weather {
    public CraftWeather(final CraftServer server, final EntityWeather entity) {
        super(server, entity);
    }

    @Override
    public EntityWeather getHandle() {
        return (EntityWeather) super.getHandle();
    }
}
