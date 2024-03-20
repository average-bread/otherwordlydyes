package cursedbread.otherdye;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.block.Block;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemDye;
import net.minecraft.core.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.helper.recipeBuilders.RecipeBuilderShaped;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;


public class OtherDyeCrafts implements RecipeEntrypoint {
	public static final String MOD_ID = "otherdye";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onRecipesReady() {
		// Shapeless Recipe examples
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(Item.dustGlowstone)
			.addInput(Item.bone)
			.create("glowMealCraft", new ItemStack(OtherDyeMod.glowMeal, 3));

	}
}
