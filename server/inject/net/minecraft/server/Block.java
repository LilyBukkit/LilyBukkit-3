package net.minecraft.server;

import net.minecraft.src.Material;
import net.minecraft.src.World;

public class Block extends net.minecraft.src.Block {

    protected Block(int id, Material material) {
        super(id, material);
    }

    protected Block(int id, int blockIndex, Material material) {
        super(id, blockIndex, material);
    }

    //LilyBukkit start
    public void dropNaturally(World world, int i, int j, int k, int l, float f){
        this.dropBlockAsItemWithChance(world, i, j, k, l, f);
    }
    //LilyBukkit end
}
