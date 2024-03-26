package cursedbread.otherdye.BlockThings;

import cursedbread.otherdye.OtherDyeMod;
import net.minecraft.core.Global;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import turniplabs.halplibe.helper.TextureHelper;

import java.util.Random;

import static cursedbread.otherdye.OtherDyeCrafts.MOD_ID;

public class CustomLamp extends Block {
	boolean isOn;

	public CustomLamp(String key, int id, boolean isActivated) {
		super(key, id, Material.stone);
		this.setTicking(true);
		this.isOn = isActivated;
	}

	public int tickRate() {
		return 2;
	}

	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		world.scheduleBlockUpdate(x, y, z, this.id, this.tickRate());
	}

	public void updateTick(World world, int x, int y, int z, Random rand) {
		boolean isPoweredByBlock = world.isBlockGettingPowered(x, y, z) || world.isBlockIndirectlyGettingPowered(x, y, z);
		if (this.isOn) {
			if (!isPoweredByBlock) {
				world.setBlock(x, y, z, OtherDyeMod.glowLampOFF.id);
			}
		} else if (isPoweredByBlock) {
			world.setBlock(x, y, z, OtherDyeMod.glowLampON.id);
		}
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
		world.scheduleBlockUpdate(x, y, z, this.id, this.tickRate());
	}

	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		return new ItemStack[]{new ItemStack(OtherDyeMod.glowLampOFF, 1, meta)};
	}
}

