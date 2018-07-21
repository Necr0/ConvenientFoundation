package convenientfoundation.common.enchantments;

import convenientfoundation.config.CFConfig;
import convenientfoundation.libs.LibMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = LibMod.MODID)
public class ModEnchantments {

    @SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> evt) {
        if(!CFConfig.Enchantments.enabled)
            return;

        IForgeRegistry<Enchantment> r = evt.getRegistry();
        if(CFConfig.Enchantments.curses){
            if(CFConfig.Enchantments.draining_curse)
                r.register(EnchantmentDrainingCurse.INSTANCE);
            if(CFConfig.Enchantments.harming_curse)
                r.register(EnchantmentHarmingCurse.INSTANCE);
        }
        if(CFConfig.Enchantments.treasure_enchantments){
            if(CFConfig.Enchantments.everlasting)
                r.register(EnchantmentEverlasting.INSTANCE);
        }
    }
}
