package p455w0rd.art.integration.crafttweaker;

import com.blamejared.mtlib.helpers.InputHelper;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import p455w0rd.art.integration.CraftTweaker;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import zmaster587.advancedRocketry.tile.multiblock.machine.TileElectricArcFurnace;

@ZenClass("mods.advancedrocketry.ArcFurnace")
@ModOnly("advancedrocketry")
@ZenRegister
public class HandlerArcFurnace extends CraftTweaker {
   private static HandlerArcFurnace INSTANCE;

   public static HandlerArcFurnace getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new HandlerArcFurnace();
      }

      return INSTANCE;
   }

   public Class getRecipeClass() {
      return TileElectricArcFurnace.class;
   }

   @ZenMethod
   public static void addRecipe(IItemStack output, int time, int power, IItemStack... inputs) {
      addRecipe((Object[])(new ItemStack[]{InputHelper.toStack(output)}), time, power, (Object[])InputHelper.toStacks(inputs));
   }

   protected static void addRecipe(Object[] output, int time, int power, Object... inputs) {
      addAction(getInstance().new Add(output, time, power, inputs));
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
}
