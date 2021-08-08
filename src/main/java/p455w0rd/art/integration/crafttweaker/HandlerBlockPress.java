package p455w0rd.art.integration.crafttweaker;

import com.blamejared.mtlib.helpers.InputHelper;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import p455w0rd.art.init.ModGlobals;
import p455w0rd.art.integration.CraftTweaker;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import zmaster587.advancedRocketry.block.BlockSmallPlatePress;

@ZenClass(ModGlobals.ZENCLASSES.PLATEPRESSER_HANDLER)
@ModOnly(ModGlobals.AR_MODID)
@ZenRegister
public class HandlerBlockPress extends CraftTweaker {
   private static HandlerBlockPress INSTANCE;

   public static HandlerBlockPress getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new HandlerBlockPress();
      }

      return INSTANCE;
   }

   public Class getRecipeClass() {
      return BlockSmallPlatePress.class;
   }

   @ZenMethod
   public static void addRecipe(IIngredient output, IIngredient... inputs) {
      addRecipe(convertFromCT(new IIngredient[]{output}), 0, 0, convertFromCT(inputs));
   }

   @ZenMethod
   public static void addRecipe(IItemStack output, IItemStack... inputs) {
      addRecipe(new ItemStack[]{InputHelper.toStack(output)}, 0, 0, (Object[]) InputHelper.toStacks(inputs));
   }

   protected static void addRecipe(Object[] output, int time, int power, Object... inputs) {
      addAction(getInstance().new Add(output, time, power, inputs));
   }

   @ZenMethod
   public static void removeRecipe(IItemStack output) {
      ItemStack[][] convertedOutput = new ItemStack[1][1];
      convertedOutput[0][0] = InputHelper.toStack(output);
      addAction(getInstance().new Remove(convertedOutput));
   }

   @ZenMethod
   public static void clear() {
      addAction(getInstance().new Clear());
   }
}
