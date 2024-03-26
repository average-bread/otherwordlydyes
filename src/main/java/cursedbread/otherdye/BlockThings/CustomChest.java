package cursedbread.otherdye.BlockThings;

import cursedbread.otherdye.OtherDyeMod;
import net.minecraft.core.block.BlockChest;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.WorldSource;
import turniplabs.halplibe.helper.TextureHelper;

import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;
import java.util.List;

import static cursedbread.otherdye.OtherDyeCrafts.MOD_ID;

public class CustomChest extends BlockChest {

	public CustomChest(String key, int id, Material material) {
		super(key, id, material);
	}

	public static List<int[]> textures = new ArrayList<>();
	@Override
	public int getBlockTexture(WorldSource blockAccess, int x, int y, int z, Side side) {
		int meta = blockAccess.getBlockMetadata(x, y, z);
		return indexFromSideAndMeta(side, meta);
	}
	@Override
	public int getBlockTextureFromSideAndMetadata(Side side, int meta) {
		return indexFromSideAndMeta(side, meta & 0xF0 | 0b0000_0010);
	}
	private int indexFromSideAndMeta(Side side, int meta){
		int[] chestColored;
		int color = meta >> 4;
		int rotation = meta & 0b00000011;
		Type type = CustomChest.getTypeFromMeta(meta);
		if (textures.size() > color){
			chestColored = textures.get(color);
		} else {
			chestColored = textures.get(0);
		}
		int index = 0;
		int sideNum = 0;
		if (side == Side.TOP || side == Side.BOTTOM){
			index = 0;
		} else {
			switch (side){
				case NORTH:
					sideNum = 0;
					break;
				case EAST:
					sideNum = 3;
					break;
				case SOUTH:
					sideNum = 2;
					break;
				case WEST:
					sideNum = 1;
					break;
			}
			int prod = (sideNum + rotation)%4;
			if (prod == 0){
				if (type == Type.SINGLE){
					index = 2;
				} else if (type == Type.LEFT){
					index = 3;
				} else {
					index = 4;
				}
			} else if (prod == 2) {
				if (type == Type.SINGLE){
					index = 1;
				} else if (type == Type.LEFT){
					index = 6;
				} else {
					index = 5;
				}
			}else {
				index = 1;
			}
		}
		return chestColored[index];
	}


	static {
		for (String s : OtherDyeMod.dyeColors){
			int[] chestTextures = new int[7];
			String dirString = s.replace(".", "_");
			chestTextures[0] = TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, dirString + "/chest/" + dirString + "_chest_top.png");
			chestTextures[1] = TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, dirString + "/chest/" + dirString + "_chest_side.png");
			chestTextures[2] = TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, dirString + "/chest/" + dirString + "_chest_front.png");
			chestTextures[3] = TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, dirString + "/chest/" + dirString + "_chest_front_left.png");
			chestTextures[4] = TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, dirString + "/chest/" + dirString + "_chest_front_right.png");
			chestTextures[5] = TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, dirString + "/chest/" + dirString + "_chest_back_left.png");
			chestTextures[6] = TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, dirString + "/chest/" + dirString + "_chest_back_right.png");
			textures.add(chestTextures);
		}
	}
}
