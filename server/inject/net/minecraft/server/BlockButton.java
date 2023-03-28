package net.minecraft.server;

import net.minecraft.src.World;

public class BlockButton extends net.minecraft.src.BlockButton {
    protected BlockButton(int id, int blockIndex) {
        super(id, blockIndex);
    }

    //LilyBukkit start
    public boolean interact(World world, int i, int j, int k, EntityHuman entityHuman) {
        this.blockActivated(world, i, j, k, entityHuman);
    }
    //LilyBukkit end
}
