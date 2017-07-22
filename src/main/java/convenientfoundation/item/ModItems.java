package convenientfoundation.item;

import convenientfoundation.ModConstants;
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

@Mod.EventBusSubscriber(modid = ModConstants.Mod.MODID)
public class ModItems {

    public static final Item WRENCH = new ItemWrench();
    public static final Item SLIME_BUCKET = new ItemSlimeBucket();


    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> evt) {
        IForgeRegistry<Item> r = evt.getRegistry();

        r.register(WRENCH);
        r.register(SLIME_BUCKET);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        registerModelLocation(WRENCH);
        registerModelLocation(SLIME_BUCKET);
    }

    @SideOnly(Side.CLIENT)
    public static void registerModelLocation(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
