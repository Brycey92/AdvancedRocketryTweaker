package p455w0rd.art.integration.crafttweaker;

import com.blamejared.mtlib.helpers.InputHelper;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.liquid.ILiquidStack;
import p455w0rd.art.integration.CraftTweaker;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import zmaster587.advancedRocketry.tile.multiblock.machine.TileElectrolyser;

@ZenClass("mods.advancedrocketry.Electrolyser")
@ModOnly("advancedrocketry")
@ZenRegister
public class HandlerElectrolyser extends CraftTweaker {
   private static HandlerElectrolyser INSTANCE;

   public static HandlerElectrolyser getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new HandlerElectrolyser();
      }

      return INSTANCE;
   }

   public Class getRecipeClass() {
      return TileElectrolyser.class;
   }

   @ZenMethod
   public static void addRecipe(ILiquidStack output1, ILiquidStack output2, int time, int power, ILiquidStack input) {
      ILiquidStack[] output = output2 == null ? new ILiquidStack[]{output1} : new ILiquidStack[]{output1, output2};
      addRecipe(InputHelper.toFluids(output), time, power, InputHelper.toFluid(input));
   }

   protected static void addRecipe(Object[] output, int time, int power, Object... inputs) {
      if ((output.length == 1 || output.length == 2) && inputs.length == 1) {
         addAction(getInstance().new Add(output, time, power, inputs));
      }

   }

   @ZenMethod
   public static void removeRecipe(ILiquidStack output1, ILiquidStack output2) {
      addAction(getInstance().new Remove(InputHelper.toFluids(new ILiquidStack[]{output1, output2})));
   }

   @ZenMethod
   public static void clear() {
      addAction(getInstance().new Clear());
   }
}
