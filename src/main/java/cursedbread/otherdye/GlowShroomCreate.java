package cursedbread.otherdye;

import net.minecraft.core.block.Block;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.block.ItemBlock;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class GlowShroomCreate extends Item {
	public GlowShroomCreate(String item, int i) {
		super(item, i);
	}
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		if (world.getBlockId(blockX, blockY, blockZ) == Block.mushroomBrown.id) {
			world.setBlockAndMetadataWithNotify(blockX, blockY, blockZ, OtherDyeMod.glowShroom.id, world.getBlockMetadata(blockX, blockY, blockZ));
			stack.consumeItem(player);
			return true;
		}
		return super.onItemUse(stack, player, world, blockX, blockY, blockZ, side, xPlaced, yPlaced);
	}
}
