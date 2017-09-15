package convenientfoundation;

import convenientfoundation.capabilities.energy.CapabilityEnergyHandler;
import convenientfoundation.capabilities.energy.EnergyRegistry;
import convenientfoundation.capabilities.heat.CapabilityHeatVessel;
import convenientfoundation.entity.ModEntities;
import convenientfoundation.event.ModEventHandlers;
import convenientfoundation.item.ModItems;
import convenientfoundation.loot.ModLoot;
import convenientfoundation.libs.LibMod;
import convenientfoundation.network.ModNetworking;
import convenientfoundation.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;


/**
 * Created by Necro on 7/22/2017.
 */
@Mod(modid = LibMod.MODID, version = LibMod.VERSION, dependencies = LibMod.DEPENDENCIES)
public class ConvenientFoundation {
    public static Logger LOG;

    @Mod.Instance(LibMod.MODID)
    public static ConvenientFoundation INSTANCE;

    @SidedProxy(modId = LibMod.MODID, serverSide = LibMod.commonProxyClassPath, clientSide = LibMod.clientProxyClassPath)
    public static CommonProxy PROXY;

    public static final CreativeTabs CREATIVETAB = new CreativeTabs(LibMod.MODID) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.SLIME_BUCKET);
        }
    };

    public static String configFile;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event){
        LOG=event.getModLog();
        configFile=event.getSuggestedConfigurationFile().getAbsolutePath();
        EnergyRegistry.init();
        CapabilityEnergyHandler.register();
        CapabilityHeatVessel.register();
        PROXY.registerRenderers();
        ModEventHandlers.init();
        ModNetworking.init();
        ModLoot.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModEntities.init();
        PROXY.registerReloadableResources();
    }
}
