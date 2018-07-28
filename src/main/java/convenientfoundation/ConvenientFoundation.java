package convenientfoundation;

import convenientfoundation.common.capabilities.energy.CapabilityEnergyHandler;
import convenientfoundation.common.capabilities.entity.CapabilityEntityHandler;
import convenientfoundation.common.capabilities.heat.CapabilityHeatVessel;
import convenientfoundation.common.energy.EnergyRegistry;
import convenientfoundation.common.entity.ModEntities;
import convenientfoundation.common.entity.stack.EntityTypeRegistry;
import convenientfoundation.common.item.ModItems;
import convenientfoundation.common.loot.ModLoot;
import convenientfoundation.common.network.ModNetworking;
import convenientfoundation.config.CFConfig;
import convenientfoundation.config.ConfigHandler;
import convenientfoundation.libs.LibMod;
import convenientfoundation.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.io.File;


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
        public ItemStack createIcon() {
            return new ItemStack(ModItems.SLIME_BUCKET);
        }
    };

    public static File configDirectory;

    static {
        ConfigHandler.registerClassesRecursive(CFConfig.class);
    }

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event){
        LOG=event.getModLog();

        configDirectory=event.getModConfigurationDirectory();
        ConfigHandler.config_directory=configDirectory;
        ConfigHandler.parseConfigs();
        ConfigHandler.loadConfigs();

        EnergyRegistry.init();
        EntityTypeRegistry.init();
        CapabilityEnergyHandler.register();
        CapabilityEntityHandler.register();
        CapabilityHeatVessel.register();
        PROXY.registerRenderers();
        ModNetworking.init();
        ModLoot.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModEntities.init();
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        PROXY.registerReloadableResources();
    }
}
