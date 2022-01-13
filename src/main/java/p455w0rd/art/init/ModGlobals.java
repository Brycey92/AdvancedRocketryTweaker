package p455w0rd.art.init;

public class ModGlobals {
  public static final String MODID = "@MODID@";
  public static final String VERSION = "@VERSION@";
  public static final String NAME = "@MODNAME@";
  public static final String MCVERSION = "@MCVERSION@";
  public static final String AR_MODID = "advancedrocketry";
  public static final String CT_MODID = "crafttweaker";
  public static final String DEP_LIST = "required-after:" + AR_MODID + "@[1.12.2-@ARVERSION@-@ARBUILD@);required-after:jei@[@JEIVERSION@,);required-after:" + CT_MODID + ";required-after:mtlib;";
  
  public static class ZENCLASSES {
    private static final String AR_ZENCLASS_BASE = "mods." + AR_MODID + ".";
    public static final String CHEMICALREACTOR_HANDLER = AR_ZENCLASS_BASE + "ChemicalReactor";
    public static final String PRECISIONASSEMBLER_HANDLER = AR_ZENCLASS_BASE + "PrecisionAssembler";
    public static final String CUTTINGMACHINE_HANDLER = AR_ZENCLASS_BASE + "CuttingMachine";
    public static final String LATHE_HANDLER = AR_ZENCLASS_BASE + "Lathe";
    public static final String ROLLINGMACHINE_HANDLER = AR_ZENCLASS_BASE + "RollingMachine";
    public static final String ELECTROLYSER_HANDLER = AR_ZENCLASS_BASE + "Electrolyser";
    public static final String ARCFURNACE_HANDLER = AR_ZENCLASS_BASE + "ArcFurnace";
    public static final String CRYSTALLIZER_HANDLER = AR_ZENCLASS_BASE + "Crystallizer";
    public static final String PLATEPRESSER_HANDLER = AR_ZENCLASS_BASE + "PlatePresser";
  }
}
