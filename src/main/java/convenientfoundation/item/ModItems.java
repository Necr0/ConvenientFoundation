package convenientfoundation.item;

import convenientfoundation.item.probe.ItemBlockProbe;
import convenientfoundation.item.probe.ItemHeatProbe;
import convenientfoundation.libs.LibMod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = LibMod.MODID)
public class ModItems {

    public static final Item WRENCH = new ItemWrench();
    public static final Item SLIME_BUCKET = new ItemSlimeBucket();
    public static final Item BLOCK_PROBE = new ItemBlockProbe();
    public static final Item HEAT_PROBE = new ItemHeatProbe();


    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> evt) {
        IForgeRegistry<Item> r = evt.getRegistry();

        r.register(WRENCH);
        r.register(SLIME_BUCKET);
        r.register(BLOCK_PROBE);
        r.register(HEAT_PROBE);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        registerModelLocation(WRENCH);
        registerModelLocation(SLIME_BUCKET);
        registerModelLocation(BLOCK_PROBE);
        registerModelLocation(HEAT_PROBE);
    }

    @SideOnly(Side.CLIENT)
    public static void registerModelLocation(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
