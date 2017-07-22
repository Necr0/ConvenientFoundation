package convenientfoundation;

import convenientfoundation.entity.ModEntities;
import convenientfoundation.event.ModEventHandlers;
import convenientfoundation.item.ModItems;
import convenientfoundation.network.ModNetworking;
import convenientfoundation.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Necro on 7/22/2017.
 */
@Mod(modid = ModConstants.Mod.MODID, version = ModConstants.Mod.VERSION, dependencies = ModConstants.Mod.DEPENDENCIES)
public class ConvenientFoundation {
    @Mod.Instance(ModConstants.Mod.MODID)
    public static ConvenientFoundation INSTANCE;

    @SidedProxy(modId = ModConstants.Mod.MODID, serverSide = ModConstants.Mod.commonProxyClassPath, clientSide = ModConstants.Mod.clientProxyClassPath)
    public static CommonProxy PROXY;

    public static final CreativeTabs CREATIVETAB = new CreativeTabs(ModConstants.Mod.MODID) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.SLIME_BUCKET);
        }
    };

    public static String configFile;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event){
        configFile=event.getSuggestedConfigurationFile().getAbsolutePath();
        PROXY.registerRenderers();
        ModEventHandlers.init();
        ModNetworking.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModEntities.init();
    }
}
