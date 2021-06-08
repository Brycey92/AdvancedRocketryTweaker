package p455w0rd.art;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import p455w0rd.art.integration.CraftTweaker;

@Mod(modid = "art", name = "Advanced Rocketry Tweaker", version = "1.0.0", dependencies = "required-after:advancedrocketry;required-after:jei@[4.14.3.242,);required-after:crafttweaker;required-after:mtlib;", acceptedMinecraftVersions = "[1.12.2]")
public class ARTweaker {
  @Instance("art")
  public static ARTweaker INSTANCE;
  
  @EventHandler
  public void postInit(FMLPostInitializationEvent event) {
    if (Loader.isModLoaded("crafttweaker"))
      CraftTweaker.performCraftTweakerActions(); 
  }
}
