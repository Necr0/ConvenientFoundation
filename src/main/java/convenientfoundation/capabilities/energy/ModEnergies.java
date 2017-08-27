package convenientfoundation.capabilities.energy;

import convenientfoundation.libs.LibMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Created by Necro on 8/5/2017.
 */
@Mod.EventBusSubscriber(modid = LibMod.MODID)
public class ModEnergies {
    public static Energy REDSTONE = new Energy(new ResourceLocation(LibMod.MODID,"redstone"),.05f).setRegistryName(new ResourceLocation(LibMod.MODID,"redstone"));

    @SubscribeEvent
    public static void registerEnergies(RegistryEvent.Register<Energy> evt) {
        IForgeRegistry<Energy> r = evt.getRegistry();

        r.register(REDSTONE);
    }
}
