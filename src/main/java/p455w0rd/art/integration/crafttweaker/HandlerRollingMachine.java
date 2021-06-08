package p455w0rd.art.integration.crafttweaker;

import com.blamejared.mtlib.helpers.InputHelper;
import com.google.common.collect.Lists;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import p455w0rd.art.init.ModGlobals;
import p455w0rd.art.integration.CraftTweaker;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import zmaster587.advancedRocketry.tile.multiblock.machine.TileRollingMachine;

@ZenClass(ModGlobals.ZENCLASSES.ROLLINGMACHINE_HANDLER)
@ModOnly(ModGlobals.AR_MODID)
@ZenRegister
public class HandlerRollingMachine extends CraftTweaker {
   private static HandlerRollingMachine INSTANCE;

   public static HandlerRollingMachine getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new HandlerRollingMachine();
      }

      return INSTANCE;
   }

   public Class getRecipeClass() {
      return TileRollingMachine.class;
   }

   @ZenMethod
   public static void addRecipe(IItemStack output, int time, int power, int waterAmount, Object... inputs) {
      addRecipe(new IItemStack[]{output}, time, power, waterAmount, inputs);
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

   private static void addRecipe(IItemStack[] output, int time, int power, int waterAmount, Object... inputs) {
      List inputList = Lists.newArrayList(inputs);
      inputList.add(1, new FluidStack(FluidRegistry.WATER, waterAmount));
      ItemStack[] outputConverted = InputHelper.toStacks(output);
      addRecipe(outputConverted, time, power, convertFromCT(inputList.toArray(new Object[inputList.size()])));
   }

   protected static void addRecipe(Object[] output, int time, int power, Object... inputs) {
      addAction(getInstance().new Add(output, time, power, inputs));
   }
}
