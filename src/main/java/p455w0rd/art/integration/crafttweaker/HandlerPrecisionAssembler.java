package p455w0rd.art.integration.crafttweaker;

import com.blamejared.mtlib.helpers.InputHelper;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import p455w0rd.art.init.ModGlobals;
import p455w0rd.art.integration.CraftTweaker;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import zmaster587.advancedRocketry.tile.multiblock.machine.TilePrecisionAssembler;

@ZenClass(ModGlobals.ZENCLASSES.PRECISIONASSEMBLER_HANDLER)
@ModOnly(ModGlobals.AR_MODID)
@ZenRegister
public class HandlerPrecisionAssembler extends CraftTweaker {
   private static HandlerPrecisionAssembler INSTANCE;

   public static HandlerPrecisionAssembler getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new HandlerPrecisionAssembler();
      }

      return INSTANCE;
   }

   public Class getRecipeClass() {
      return TilePrecisionAssembler.class;
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
      addAction(getInstance().new Remove(output));
   }

   @ZenMethod
   public static void clear() {
      addAction(getInstance().new Clear());
   }

   private static void addRecipe(IItemStack[] output, int time, int power, Object... inputs) {
      addRecipe(InputHelper.toStacks(output), time, power, convertFromCT(inputs));
   }

   protected static void addRecipe(Object[] output, int time, int power, Object... inputs) {
      addAction(getInstance().new Add(output, time, power, inputs));
   }
}
