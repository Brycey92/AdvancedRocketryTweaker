package p455w0rd.art.integration.crafttweaker;

import com.blamejared.mtlib.helpers.InputHelper;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import p455w0rd.art.integration.CraftTweaker;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import zmaster587.advancedRocketry.tile.multiblock.machine.TileChemicalReactor;

@ZenClass("mods.advancedrocketry.ChemicalReactor")
@ModOnly("advancedrocketry")
@ZenRegister
public class HandlerChemicalReactor extends CraftTweaker {
   private static HandlerChemicalReactor INSTANCE;

   public Class getRecipeClass() {
      return TileChemicalReactor.class;
   }

   public static HandlerChemicalReactor getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new HandlerChemicalReactor();
      }

      return INSTANCE;
   }

   @ZenMethod
   public static void addRecipe(IItemStack output, int time, int power, Object... inputs) {
      ItemStack outputConverted = InputHelper.toStack(output);
      addRecipe(new Object[]{outputConverted}, time, power, convertFromCT(inputs));
   }

   @ZenMethod
   public static void addRecipe(ILiquidStack output, int time, int power, Object... inputs) {
      FluidStack outputConverted = InputHelper.toFluid(output);
      addRecipe((Object[])(new FluidStack[]{outputConverted}), time, power, convertFromCT(inputs));
   }

   @ZenMethod
   public static void removeRecipe(ILiquidStack output) {
      removeRecipe(InputHelper.toFluid(output));
   }

   protected static void addRecipe(Object[] output, int time, int power, Object... inputs) {
      addAction(getInstance().new Add(output, time, power, inputs));
   }

   private static void removeRecipe(FluidStack output) {
      if (output != null) {
         removeRecipe(new FluidStack[]{output});
      }

   }

   @ZenMethod
   public static void removeRecipe(IItemStack output) {
      ItemStack[][] convertedOutput = new ItemStack[1][1];
      convertedOutput[0][0] = InputHelper.toStack(output);
      removeRecipe(convertedOutput);
   }

   private static void removeRecipe(ItemStack[][] output) {
      if (output instanceof ItemStack[][]) {
         addAction(getInstance().new Remove(output));
      }

   }

   private static void removeRecipe(FluidStack[] output) {
      addAction(getInstance().new Remove(output));
   }

   @ZenMethod
   public static void removeRecipe(ILiquidStack[] output) {
      removeRecipe(InputHelper.toFluids(output));
   }

   @ZenMethod
   public static void clear() {
      addAction(getInstance().new Clear());
   }
}
