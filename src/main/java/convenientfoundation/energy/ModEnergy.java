package convenientfoundation.energy;

import convenientfoundation.libs.LibEnergy;
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
public class ModEnergy {
    public static Energy REDSTONE = new Energy(new ResourceLocation(LibMod.MODID, LibEnergy.redstone),.25f,.01f).setUnlocalizedName(LibMod.MODID, LibEnergy.redstone).setRegistryName(new ResourceLocation(LibMod.MODID, LibEnergy.redstone));
    public static Energy ENDER = new Energy(new ResourceLocation(LibMod.MODID, LibEnergy.ender),8f,1f).setUnlocalizedName(LibMod.MODID, LibEnergy.ender).setRegistryName(new ResourceLocation(LibMod.MODID, LibEnergy.ender));

    @SubscribeEvent
    public static void registerEnergies(RegistryEvent.Register<Energy> evt) {
        IForgeRegistry<Energy> r = evt.getRegistry();

        r.register(REDSTONE);
        r.register(ENDER);
    }
}
