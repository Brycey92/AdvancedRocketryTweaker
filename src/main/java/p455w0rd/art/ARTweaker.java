package p455w0rd.art;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import p455w0rd.art.init.ModGlobals;
import p455w0rd.art.integration.CraftTweaker;

@Mod(modid = ModGlobals.MODID, name = ModGlobals.NAME, version = ModGlobals.VERSION, dependencies = ModGlobals.DEP_LIST, acceptedMinecraftVersions="[" + ModGlobals.MCVERSION + "]")
public class ARTweaker {
  @Instance(ModGlobals.MODID)
  public static ARTweaker INSTANCE;
  
  @EventHandler
  public void postInit(FMLPostInitializationEvent event) {
    if (Loader.isModLoaded(ModGlobals.CT_MODID))
      CraftTweaker.performCraftTweakerActions(); 
  }
}
