package cursedbread.otherdye;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.render.block.model.BlockModelRenderBlocks;
import net.minecraft.client.sound.block.BlockSound;
import net.minecraft.core.block.BlockMushroom;
import net.minecraft.core.block.BlockWool;
import net.minecraft.core.block.material.Material;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.util.ConfigHandler;
import turniplabs.halplibe.util.GameStartEntrypoint;
import net.minecraft.core.block.Block;
import java.util.Properties;


public class OtherDyeMod implements ModInitializer, GameStartEntrypoint {
	public static final String MOD_ID = "otherdye";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static BlockBuilder standardBlockBuilder = new BlockBuilder(MOD_ID)
		.setNorthTexture(16, 8) // the coordinates of the blue N texture from the terrain atlas
		.setSouthTexture(17, 8) // the coordinates of the green S texture from the terrain atlas
		.setBottomTexture(18, 8) // the coordinates of the purple D texture from the terrain atlas
		.setEastTexture(16, 9) // the coordinates of the red E texture from the terrain atlas
		.setWestTexture(17, 9) // the coordinates of the yellow W texture from the terrain atlas
		.setTopTexture(18, 9) // the coordinates of the orange U texture from the terrain atlas
		.setHardness(5f); // Sets the hardness which affects the time to mine the blocks
	//too scared that everything will break, better keep this pieve of code as it is ^

	public static BlockBuilder crossedBlock = new BlockBuilder(MOD_ID)
		.setBlockModel(new BlockModelRenderBlocks(1))
		.setBlockSound(new BlockSound("step.grass", "step.grass", 1.0f, 1.0f));

	public static BlockBuilder customwoolBlocks = new BlockBuilder(MOD_ID)
		.setBlockModel(new BlockModelRenderBlocks(0))
		.setBlockSound(new BlockSound("step.wool", "step.wool", 1.0f, 1.0f));


	public static int blockId;

	public static int itemId;

	public static Block glowShroom;
	public static Block glowWool;

	static {
		Properties prop = new Properties();
		prop.setProperty("starting_block_id","2000");
		prop.setProperty("starting_item_id","17000");
		ConfigHandler config = new ConfigHandler(MOD_ID,prop);

		blockId = config.getInt("starting_block_id");
		itemId = config.getInt("starting_item_id");

		config.updateConfig();
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Impossible colors");
	}

	@Override
	public void beforeGameStart() {
		int startingBlockId = blockId;

      glowShroom = crossedBlock
		  .setTextures("glowing_shroom.png")
		  .setLuminance(10)
		  .build(new BlockMushroom("glowShroom", startingBlockId++));


      glowWool = customwoolBlocks
		  .setTextures("glowing_wool.png")
		  .setLuminance(13)
		  .build(new BlockWool("glowWool", startingBlockId++));


	}

	@Override
	public void afterGameStart() {


	}

}
