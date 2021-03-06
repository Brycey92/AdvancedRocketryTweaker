# Advanced Rocketry Tweaker
An addon for Advanced Rocketry that allows recipe modification via CraftTweaker scripts. Original mod by [p455w0rd](https://github.com/p455w0rd/).

## Sample Script

```
// Chemical Reactor
//mods.advancedrocketry.ChemicalReactor.clear();
mods.advancedrocketry.ChemicalReactor.removeRecipe(<liquid:rocketfuel>);
mods.advancedrocketry.ChemicalReactor.addRecipe(<liquid:rocketfuel>*1000, 80/*ticks*/, 100/*rf/t*/, <liquid:oxygen>*500, <liquid:hydrogen>*500);
mods.advancedrocketry.ChemicalReactor.addRecipe(<minecraft:obsidian>*1, 80/*ticks*/, 100/*rf/t*/, <liquid:lava>*1000, <liquid:water>*1000);

// Precision Assembler
//mods.advancedrocketry.PrecisionAssembler.clear();
mods.advancedrocketry.PrecisionAssembler.removeRecipe(<advancedrocketry:blocklens>);
mods.advancedrocketry.PrecisionAssembler.addRecipe(<minecraft:stone>*1, 80/*ticks*/, 100/*rf/t*/, <minecraft:diamond>*4, <ore:gemLapis>*4);

// Cutting Machine
//mods.advancedrocketry.CuttingMachine.clear();
val junglePlanks = <minecraft:planks>;
mods.advancedrocketry.CuttingMachine.removeRecipe(junglePlanks.withDamage(3)*6);
mods.advancedrocketry.CuttingMachine.addRecipe(<minecraft:planks>*4, 80/*ticks*/, 100/*rf/t*/, <minecraft:log>*1);

// Lathe
//mods.advancedrocketry.Lathe.clear();
var titaniumRod = <advancedrocketry:productrod>;
mods.advancedrocketry.Lathe.removeRecipe(titaniumRod.withDamage(1)*2);
mods.advancedrocketry.Lathe.addRecipe(<minecraft:log>*1, 80/*ticks*/, 100/*rf/t*/, <minecraft:planks>*4);

// Rolling Machine
//mods.advancedrocketry.RollingMachine.clear();
val titaniumPlate = <advancedrocketry:productplate>;
mods.advancedrocketry.RollingMachine.removeRecipe(titaniumPlate.withDamage(1));
mods.advancedrocketry.RollingMachine.addRecipe(<minecraft:log>*1, 80/*ticks*/, 100/*rf/t*/, 100/*mB water*/, <minecraft:planks>*4);

// Electrolyser
//mods.advancedrocketry.Electrolyser.clear();
mods.advancedrocketry.Electrolyser.removeRecipe(<liquid:hydrogen>,<liquid:oxygen>);
mods.advancedrocketry.Electrolyser.addRecipe(<liquid:nitrogen>*500,<liquid:oxygen>*500, 80/*ticks*/, 100/*rf/t*/, <liquid:lava>*1000);

// Arc Furnace
//mods.advancedrocketry.ArcFurnace.clear();
val titaniumIngot = <libvulpes:productingot>;
mods.advancedrocketry.ArcFurnace.removeRecipe(titaniumIngot.withDamage(7));
mods.advancedrocketry.ArcFurnace.addRecipe(<minecraft:dirt>*1, 20/*ticks*/, 10/*rf/t*/, <minecraft:apple>*1, <minecraft:gold_ingot>*1);

// Crystallizer
//mods.advancedrocketry.Crystallizer.clear();
mods.advancedrocketry.Crystallizer.removeRecipe(<libvulpes:productcrystal>);
val notchApple=<minecraft:golden_apple>;
mods.advancedrocketry.Crystallizer.addRecipe(notchApple.withDamage(1), 200/*ticks*/, 10/*rf/t*/, <minecraft:apple>*1, <minecraft:gold_block>*1);

// Plate Presser
//mods.advancedrocketry.PlatePresser.clear();
val ironPlate = <libvulpes:productplate>;
mods.advancedrocketry.PlatePresser.removeRecipe(ironPlate.withDamage(1));
mods.advancedrocketry.PlatePresser.removeRecipe(titaniumPlate.withDamage(1));
mods.advancedrocketry.PlatePresser.addRecipe(<minecraft:stone>*1,<minecraft:gold_block>*1);
```
