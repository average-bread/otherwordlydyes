package cursedbread.otherdye;

import cursedbread.otherdye.BlockThings.CustomChest;
import cursedbread.otherdye.BlockThings.CustomLamp;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.render.block.model.BlockModelRenderBlocks;
import net.minecraft.core.sound.BlockSound;
import net.minecraft.core.block.*;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.data.tag.Tag;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemDye;
import net.minecraft.core.item.block.ItemBlockChestLegacy;
import net.minecraft.core.item.block.ItemBlockSlab;
import net.minecraft.core.item.tag.ItemTags;
import net.minecraft.core.sound.BlockSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.ItemHelper;
import turniplabs.halplibe.helper.TextureHelper;
import turniplabs.halplibe.util.ConfigHandler;
import turniplabs.halplibe.util.GameStartEntrypoint;
import net.minecraft.core.block.BlockChest;

import java.util.Properties;

import static net.minecraft.core.item.Item.cloth;


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

	public static BlockBuilder customWoolBlocks = new BlockBuilder(MOD_ID)
		.setBlockModel(new BlockModelRenderBlocks(0))
		.setFlammability(30,60)
		.setTags(BlockTags.MINEABLE_BY_SHEARS, BlockTags.FENCES_CONNECT)
		.setHardness(0.8f)
		.setBlockSound(BlockSounds.CLOTH);

	public static BlockBuilder customPlanksBlock = new BlockBuilder(MOD_ID)
		.setBlockModel(new BlockModelRenderBlocks(0))
		.setFlammability(5,20)
		.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT)
		.setHardness(2.5f)
		.setResistance(5.0f)
		.setBlockSound(BlockSounds.WOOD);
	public static BlockBuilder customPlanksFenceBlock = new BlockBuilder(MOD_ID)
		.setBlockModel(new BlockModelRenderBlocks(11))
		.setFlammability(5,20)
		.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT)
		.setHardness(2.5f)
		.setResistance(5.0f)
		.setBlockSound(BlockSounds.WOOD);
	public static BlockBuilder customPlanksGateBlock = new BlockBuilder(MOD_ID)
		.setBlockModel(new BlockModelRenderBlocks(18))
		.setFlammability(5,20)
		.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT)
		.setHardness(2.5f)
		.setResistance(5.0f)
		.setBlockSound(BlockSounds.WOOD);
	public static BlockBuilder customPlanksSlabBlock = new BlockBuilder(MOD_ID)
		.setBlockModel(new BlockModelRenderBlocks(0))
		.setFlammability(5,20)
		.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT)
		.setHardness(2.5f)
		.setResistance(5.0f)
		.setItemBlock(ItemBlockSlab::new)
		.setBlockSound(BlockSounds.WOOD);
	public static BlockBuilder customPlanksStairBlock = new BlockBuilder(MOD_ID)
		.setBlockModel(new BlockModelRenderBlocks(10))
		.setFlammability(5,20)
		.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT)
		.setHardness(2.5f)
		.setResistance(5.0f)
		.setBlockSound(BlockSounds.WOOD);

	public static BlockBuilder customPlanksChest = new BlockBuilder(MOD_ID)
		.setBlockModel(new BlockModelRenderBlocks(0))
		.setFlammability(5,20)
		.setBlockSound(BlockSounds.WOOD)
		.setHardness(2.5f)
		.setResistance(5.0f)
		.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT);

	public static BlockBuilder customLampON = new BlockBuilder(MOD_ID)
		.setBlockModel(new BlockModelRenderBlocks(0))
		.setFlammability(5,20)
		.setTags(BlockTags.MINEABLE_BY_PICKAXE)
		.setBlockSound(BlockSounds.GLASS)
		.setHardness(0.5f)
		.setLuminance(15)
		.setVisualUpdateOnMetadata();

	public static BlockBuilder customLampOFF = new BlockBuilder(MOD_ID)
		.setBlockModel(new BlockModelRenderBlocks(0))
		.setFlammability(5,20)
		.setTags(BlockTags.MINEABLE_BY_PICKAXE)
		.setBlockSound(BlockSounds.GLASS)
		.setHardness(0.5f)
		.setVisualUpdateOnMetadata();

	public static String[] dyeColors = {
		"glowing"};


	public static int blockId;

	public static int itemId;

	public static Block glowShroom;
	public static Block glowWool;
	public static Block glowPlanks;
	public static Block glowPlanksFence;
	public static Block glowPlanksGate;
	public static Block glowPlanksSlab;
	public static Block glowPlanksStairs;
	public static Block glowPlanksChest;
	public static Block glowLampON;
	public static Block glowLampOFF;
	public static Item glowDye;
	public static Item glowMeal;


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
		  .build(new BlockMushroom("glowShroom", blockId++)).withTags(BlockTags.PLANTABLE_IN_JAR);


      glowWool = customWoolBlocks
		  .setTextures("glowing/glowing_wool.png")
		  .setLuminance(13)
		  .build(new Block("glowWool", blockId++, Material.cloth));

	  glowPlanks = customPlanksBlock
		  .setTextures("glowing/glowing_planks.png")
		  .setLuminance(13)
		  .build(new Block("glowPlanks", blockId++, Material.wood));

		glowPlanksFence = customPlanksFenceBlock
			.setTextures("glowing/glowing_planks.png")
			.setLuminance(13)
			.build(new BlockFence("glowPlanksFence", blockId++));

		glowPlanksGate = customPlanksGateBlock
			.setTextures("glowing/glowing_planks.png")
			.setLuminance(13)
			.build(new BlockFenceGate("glowPlanksGate", blockId++));

		glowPlanksSlab = customPlanksSlabBlock
			.setTextures("glowing/glowing_planks.png")
			.setLuminance(13)
			.build(new BlockSlab(glowPlanks, blockId++));

		glowPlanksStairs = customPlanksStairBlock
			.setTextures("glowing/glowing_planks.png")
			.setLuminance(13)
			.build(new BlockStairs(glowPlanks, blockId++));

		glowPlanksChest = customPlanksChest
			.setLuminance(13)
			.build(new CustomChest("glowPlanksChest", blockId++, Material.wood));

		glowLampOFF = customLampON
			.setTextures("glowing/lamp/glowing_lamp_off.png")
			.build(new CustomLamp("glowLampOFF", blockId++, false));

		glowLampON = customLampOFF
			.setTextures("glowing/lamp/glowing_lamp_on.png")
			.build(new CustomLamp("glowLampON", blockId++, true)).withTags(BlockTags.NOT_IN_CREATIVE_MENU);

	  glowMeal = ItemHelper.createItem(MOD_ID, new GlowShroomCreate("glowMeal", itemId++), "glow_meal.png").withTags(ItemTags.renderFullbright);

	  glowDye = ItemHelper.createItem(MOD_ID, new Item("glowingDye", itemId++), "glowing_dye.png").withTags(ItemTags.renderFullbright);
	}

	@Override
	public void afterGameStart() {


	}

}
