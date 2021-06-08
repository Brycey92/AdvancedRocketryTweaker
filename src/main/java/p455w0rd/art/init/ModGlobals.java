package p455w0rd.art.init;

public class ModGlobals {
  public static final String MODID = "@MODID@";
  public static final String VERSION = "@VERSION@";
  public static final String NAME = "@MODNAME@";
  public static final String MCVERSION = "@MCVERSION@";
  public static final String AR_MODID = "advancedrocketry";
  public static final String CT_MODID = "crafttweaker";
  public static final String DEP_LIST = "required-after:" + AR_MODID + "@[@MCVERSION@-@ARVERSION@-@ARBUILD@,);required-after:jei@[@JEIVERSION@,);required-after:" + CT_MODID + ";required-after:mtlib;";
  
  public static class ZENCLASSES {
    private static final String AR_ZENCLASS_BASE = "mods.advancedrocketry.";
    public static final String BLOCKPRESS_HANDLER = "mods.advancedrocketry.BlockPress";
    public static final String CHEMICALREACTOR_HANDLER = "mods.advancedrocketry.ChemicalReactor";
    public static final String PRECISIONASSEMBLER_HANDLER = "mods.advancedrocketry.PrecisionAssembler";
    public static final String CUTTINGMACHINE_HANDLER = "mods.advancedrocketry.CuttingMachine";
    public static final String LATHE_HANDLER = "mods.advancedrocketry.Lathe";
    public static final String ROLLINGMACHINE_HANDLER = "mods.advancedrocketry.RollingMachine";
    public static final String ELECTROLYSER_HANDLER = "mods.advancedrocketry.Electrolyser";
    public static final String ARCFURNACE_HANDLER = "mods.advancedrocketry.ArcFurnace";
    public static final String CRYSTALLIZER_HANDLER = "mods.advancedrocketry.Crystallizer";
    public static final String PLATEPRESSER_HANDLER = "mods.advancedrocketry.PlatePresser";
  }
}
