package convenientfoundation.common.event.resourceDrop;

import convenientfoundation.CFConfig;
import convenientfoundation.common.item.ModItems;
import convenientfoundation.libs.LibMod;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;

/**
 * Created by Necro on 9/12/2017.
 */
@Mod.EventBusSubscriber(modid = LibMod.MODID)
public class ArcaneDustDropHandler {

    @SubscribeEvent
    public static void onBlockHarvested(BlockEvent.HarvestDropsEvent event){
        World w=event.getWorld();
        if(w.isRemote||event.isSilkTouching()||!CFConfig.arcane_dust)
            return;

        Block b=event.getState().getBlock();
        if(Arrays.asList(CFConfig.ArcaneDust.drop_ores).contains(b.getRegistryName().toString())){
            for(int i=0;i<event.getFortuneLevel()+1;i++){
                if(w.rand.nextFloat()<CFConfig.ArcaneDust.drop_chance)
                    event.getDrops().add(new ItemStack(ModItems.ARCANE_DUST));
            }
        }
    }
}
