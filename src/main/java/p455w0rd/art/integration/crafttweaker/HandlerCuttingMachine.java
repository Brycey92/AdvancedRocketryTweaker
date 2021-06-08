package p455w0rd.art.integration.crafttweaker;

import com.blamejared.mtlib.helpers.InputHelper;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import p455w0rd.art.integration.CraftTweaker;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import zmaster587.advancedRocketry.tile.multiblock.machine.TileCuttingMachine;

@ZenClass("mods.advancedrocketry.CuttingMachine")
@ModOnly("advancedrocketry")
@ZenRegister
public class HandlerCuttingMachine extends CraftTweaker {
   private static HandlerCuttingMachine INSTANCE;

   public static HandlerCuttingMachine getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new HandlerCuttingMachine();
      }

      return INSTANCE;
   }

   public Class getRecipeClass() {
      return TileCuttingMachine.class;
   }

   @ZenMethod
   public static void addRecipe(IItemStack output, int time, int power, Object... inputs) {
      addRecipe(new IItemStack[]{output}, time, power, inputs);
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

   @ZenMethod
   public static void clear() {
      addAction(getInstance().new Clear());
   }

   private static void addRecipe(IItemStack[] output, int time, int power, Object... inputs) {
      addRecipe((Object[])InputHelper.toStacks(output), time, power, convertFromCT(inputs));
   }

   protected static void addRecipe(Object[] output, int time, int power, Object... inputs) {
      addAction(getInstance().new Add(output, time, power, inputs));
   }
}
