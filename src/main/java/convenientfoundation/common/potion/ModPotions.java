package convenientfoundation.common.potion;

import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Created by Necro on 5/14/2017.
 */
@Mod.EventBusSubscriber()
public class ModPotions {
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Potion> event) {
        IForgeRegistry<Potion> r=event.getRegistry();
        r.register(PotionLumbering.INSTANCE);
        r.register(PotionThorns.INSTANCE);
        r.register(PotionBandaged.INSTANCE);
    }
}
