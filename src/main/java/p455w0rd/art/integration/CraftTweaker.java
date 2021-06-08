package p455w0rd.art.integration;

import com.blamejared.mtlib.helpers.InputHelper;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.oredict.IOreDictEntry;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import zmaster587.libVulpes.interfaces.IRecipe;
import zmaster587.libVulpes.recipe.NumberedOreDictStack;
import zmaster587.libVulpes.recipe.RecipesMachine;

public class CraftTweaker {
   protected static final List<IAction> LATE_ACTIONS = new LinkedList();
   private static CraftTweaker INSTANCE;

   public Class getRecipeClass() {
      return null;
   }

   public static CraftTweaker getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new CraftTweaker();
      }

      return INSTANCE;
   }

   protected static List getRecipesForMachine(Class machineClass) {
      try {
         Class.forName(machineClass.getName());
         List recipeList = RecipesMachine.getInstance().getRecipes(machineClass);
         if (recipeList == null) {
            throw new ClassNotFoundException("Machine Recipe Class " + machineClass.toString() + " not found in AR Recipe Registry");
         } else {
            return recipeList;
         }
      } catch (ClassNotFoundException var2) {
         var2.printStackTrace();
         return new ArrayList();
      }
   }

   public static void performCraftTweakerActions() {
      try {
         LATE_ACTIONS.forEach(CraftTweakerAPI::apply);
      } catch (Exception var1) {
         var1.printStackTrace();
         CraftTweakerAPI.logError("Error while applying actions", var1);
      }

   }

   protected static void addAction(IAction action) {
      if (!LATE_ACTIONS.contains(action)) {
         LATE_ACTIONS.add(action);
      }

   }

   public String formatObjectArray(Object[] objArray) {
      String ret = "";
      boolean first = true;
      Object[] var4 = objArray;
      int var5 = objArray.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         Object obj = var4[var6];
         if (!first) {
            ret = ret + "; ";
         }

         ret = ret + this.formatObject(obj);
         if (first) {
            first = false;
         }
      }

      return ret;
   }

   private String formatObject(Object obj) {
      if (obj instanceof FluidStack) {
         return "Fluid: " + ((FluidStack)obj).getFluid().getName() + "|Count: " + ((FluidStack)obj).amount;
      } else if (obj instanceof ILiquidStack) {
         return "Fluid: " + ((ILiquidStack)obj).getDisplayName() + "|Count: " + ((ILiquidStack)obj).getAmount();
      } else if (obj instanceof ItemStack) {
         return "Item: " + ((ItemStack)obj).getDisplayName() + "|Count: " + ((ItemStack)obj).getCount();
      } else if (obj instanceof IItemStack) {
         return "Item: " + ((IItemStack)obj).getDisplayName() + "|Count: " + ((IItemStack)obj).getAmount();
      } else if (obj instanceof IOreDictEntry) {
         return "OreDict: " + ((IOreDictEntry)obj).getName() + "|Count: " + ((IOreDictEntry)obj).getAmount();
      } else {
         return obj instanceof NumberedOreDictStack ? " OreDict: " + ((NumberedOreDictStack)obj).getOre() + "|Count: " + ((NumberedOreDictStack)obj).getNumber() : "Null: null";
      }
   }

   protected static Object[] convertFromCT(Object[] input) {
      if (input != null && input.length > 0) {
         List vanillaFluids = new ArrayList();
         List vanillaItems = new ArrayList();
         List oreDicts = new ArrayList();
         Object[] returnObj = input;
         int objIndex = input.length;

         int i;
         for(i = 0; i < objIndex; ++i) {
            Object element = returnObj[i];
            if (element != null) {
               if (isLiquid(element)) {
                  FluidStack liquidStack = getLiquid(element);
                  if (liquidStack != null) {
                     vanillaFluids.add(liquidStack);
                  }
               } else if (isItem(element)) {
                  ItemStack itemStack = getItem(element);
                  if (itemStack != null) {
                     vanillaItems.add(itemStack);
                  }
               } else if (isOreDict(element)) {
                  NumberedOreDictStack oreStack = getOreDict(element);
                  if (oreStack != null) {
                     oreDicts.add(oreStack);
                  }
               }
            }
         }

         returnObj = new Object[vanillaFluids.size() + vanillaItems.size() + oreDicts.size()];
         objIndex = 0;

         for(i = 0; i < vanillaFluids.size(); ++i) {
            returnObj[objIndex] = vanillaFluids.get(i);
            ++objIndex;
         }

         for(i = 0; i < vanillaItems.size(); ++i) {
            returnObj[objIndex] = vanillaItems.get(i);
            ++objIndex;
         }

         for(i = 0; i < oreDicts.size(); ++i) {
            returnObj[objIndex] = oreDicts.get(i);
            ++objIndex;
         }

         return returnObj;
      } else {
         return new Object[0];
      }
   }

   private static boolean isItem(Object ctObj) {
      if (!(ctObj instanceof IItemStack) && !(ctObj instanceof ItemStack)) {
         if (!(ctObj instanceof IIngredient)) {
            return false;
         } else {
            return ((IIngredient)ctObj).getInternal() instanceof IItemStack || ((IIngredient)ctObj).getInternal() instanceof ItemStack;
         }
      } else {
         return true;
      }
   }

   protected static boolean isLiquid(Object ctObj) {
      if (!(ctObj instanceof ILiquidStack) && !(ctObj instanceof FluidStack)) {
         if (!(ctObj instanceof IIngredient)) {
            return false;
         } else {
            return ((IIngredient)ctObj).getInternal() instanceof ILiquidStack || ((IIngredient)ctObj).getInternal() instanceof FluidStack;
         }
      } else {
         return true;
      }
   }

   private static boolean isOreDict(Object ctObj) {
      return ctObj instanceof IIngredient ? ((IIngredient)ctObj).getInternal() instanceof IOreDictEntry : ctObj instanceof IOreDictEntry;
   }

   private static ItemStack getItem(Object obj) {
      if (isItem(obj)) {
         if (obj instanceof ItemStack) {
            return (ItemStack)obj;
         } else if (obj instanceof IIngredient) {
            return ((IIngredient)obj).getInternal() instanceof ItemStack ? (ItemStack)((IIngredient)obj).getInternal() : toVanilla((IItemStack)((IIngredient)obj).getInternal());
         } else {
            return toVanilla((IItemStack)obj);
         }
      } else {
         return ItemStack.EMPTY;
      }
   }

   private static FluidStack getLiquid(Object obj) {
      if (isLiquid(obj)) {
         if (obj instanceof FluidStack) {
            return (FluidStack)obj;
         } else if (obj instanceof IIngredient) {
            return ((IIngredient)obj).getInternal() instanceof FluidStack ? (FluidStack)((IIngredient)obj).getInternal() : toVanilla((ILiquidStack)((IIngredient)obj).getInternal());
         } else {
            return toVanilla((ILiquidStack)obj);
         }
      } else {
         return null;
      }
   }

   private static NumberedOreDictStack getOreDict(Object obj) {
      if (obj instanceof IIngredient && isOreDict(obj)) {
         IIngredient ingredient = (IIngredient)obj;
         Object type = ingredient.getInternal();
         if (type instanceof IOreDictEntry) {
            return toVanilla((IOreDictEntry)type, ingredient.getAmount());
         }
      }

      return obj instanceof IOreDictEntry ? toVanilla((IOreDictEntry)obj, ((IOreDictEntry)obj).getAmount()) : null;
   }

   private static NumberedOreDictStack toVanilla(IOreDictEntry oreDict, int amount) {
      return new NumberedOreDictStack(oreDict.getName(), amount);
   }

   private static ItemStack toVanilla(IItemStack item) {
      return InputHelper.toStack(item);
   }

   private static FluidStack toVanilla(ILiquidStack liquid) {
      return InputHelper.toFluid(liquid);
   }

   protected class Clear implements IAction {
      public Clear() {
      }

      public void apply() {
         if (CraftTweaker.this.getRecipeClass() != null) {
            CraftTweaker.getRecipesForMachine(CraftTweaker.this.getRecipeClass()).clear();
         }

      }

      public String describe() {
         return CraftTweaker.this.getRecipeClass() != null ? "Cleared " + CraftTweaker.this.getRecipeClass().getSimpleName() + " Recipes" : "";
      }
   }

   protected class Remove implements IAction {
      final FluidStack[] fluidOutput;
      final ItemStack[][] itemOutput;

      public Remove(FluidStack[] output) {
         this(output, (ItemStack[][])null);
      }

      public Remove(ItemStack[][] output) {
         this((FluidStack[])null, output);
      }

      public Remove(FluidStack[] fluidOutput, ItemStack[][] itemOutput) {
         this.fluidOutput = fluidOutput;
         this.itemOutput = itemOutput;
      }

      private ItemStack[] getAllStacks(ItemStack[][] stacks) {
         List stackList = new ArrayList();
         ItemStack[][] var3 = stacks;
         int var4 = stacks.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            ItemStack[] stack = var3[var5];
            ItemStack[] var7 = stack;
            int var8 = stack.length;

            for(int var9 = 0; var9 < var8; ++var9) {
               ItemStack element = var7[var9];
               if (element != null) {
                  stackList.add(element);
               }
            }
         }

         return (ItemStack[])stackList.toArray(new ItemStack[stackList.size()]);
      }

      private ItemStack[] getAllStacks(List list) {
         return (ItemStack[])list.toArray(new ItemStack[list.size()]);
      }

      public boolean matches(IRecipe recipe) {
         if (recipe == null) {
            return false;
         } else {
            int numFluidsToMatch = 0;
            int numItemsToMatch = 0;
            int i;
            if (this.itemOutput != null) {
               ItemStack[] matchArray = this.getAllStacks(this.itemOutput);
               numItemsToMatch = matchArray.length;
               ItemStack[] recipeArray = this.getAllStacks(recipe.getOutput());
               if (numItemsToMatch != recipeArray.length) {
                  return false;
               }

               for(i = 0; i < matchArray.length; ++i) {
                  ItemStack[] var7 = recipeArray;
                  int var8 = recipeArray.length;

                  for(int var9 = 0; var9 < var8; ++var9) {
                     ItemStack element = var7[var9];
                     ItemStack stack1 = matchArray[i].copy();
                     stack1.setCount(1);
                     ItemStack stack2 = element.copy();
                     stack2.setCount(1);
                     if (ItemStack.areItemStacksEqual(stack1, stack2)) {
                        --numItemsToMatch;
                        if (numItemsToMatch == 0) {
                           break;
                        }
                     }
                  }
               }
            }

            if (this.fluidOutput != null) {
               if (this.fluidOutput.length != recipe.getFluidOutputs().size()) {
                  return false;
               }

               numFluidsToMatch = this.fluidOutput.length;

               for(i = 0; i < this.fluidOutput.length; ++i) {
                  for(int j = 0; j < recipe.getFluidOutputs().size(); ++j) {
                     if (this.fluidOutput[i] != null && this.fluidOutput[i].getFluid() == ((FluidStack)recipe.getFluidOutputs().get(j)).getFluid()) {
                        --numFluidsToMatch;
                     }
                  }
               }
            }

            return numFluidsToMatch == 0 && numItemsToMatch == 0;
         }
      }

      public void apply() {
         if (CraftTweaker.this.getRecipeClass() != null) {
            List recipeList = CraftTweaker.getRecipesForMachine(CraftTweaker.this.getRecipeClass());
            ListIterator recipeIterator = recipeList.listIterator();

            while(recipeIterator.hasNext()) {
               IRecipe currentRecipe = (IRecipe)recipeIterator.next();
               if (this.matches(currentRecipe)) {
                  recipeIterator.remove();
                  break;
               }
            }
         }

      }

      public String describe() {
         if (CraftTweaker.this.getRecipeClass() != null) {
            boolean itemSupplied = this.itemOutput != null;
            boolean fluidSupplied = this.fluidOutput != null;
            String outputStr = "Removed Recipe for " + CraftTweaker.this.getRecipeClass().getSimpleName() + " with output \"";
            if (itemSupplied) {
               outputStr = outputStr + "-" + CraftTweaker.this.formatObjectArray(this.getAllStacks(this.itemOutput)).split("\\|")[0];
            }

            if (fluidSupplied) {
               outputStr = outputStr + "-" + CraftTweaker.this.formatObjectArray(this.fluidOutput).split("\\|")[0] + "\"";
            }

            return outputStr + "\"";
         } else {
            return "";
         }
      }
   }

   protected class Add implements IAction {
      final Object[] out;
      final int time;
      final int power;
      final Object[] inputs;

      public Add(Object[] out, int time, int power, Object[] inputs) {
         this.out = out;
         this.time = time;
         this.power = power;
         this.inputs = inputs;
      }

      public void apply() {
         if (CraftTweaker.this.getRecipeClass() != null) {
            RecipesMachine.getInstance().addRecipe(CraftTweaker.this.getRecipeClass(), this.out, this.time, this.power, this.inputs);
         }

      }

      public String describe() {
         return "Added Recipe" + (CraftTweaker.this.getRecipeClass() != null ? " for " + CraftTweaker.this.getRecipeClass().getSimpleName() : "") + ": INPUT:[" + CraftTweaker.this.formatObjectArray(this.inputs) + "] -> OUTPUT:[" + CraftTweaker.this.formatObjectArray(this.out) + "]; Time: " + this.time + "; Power: " + this.power;
      }
   }
}
