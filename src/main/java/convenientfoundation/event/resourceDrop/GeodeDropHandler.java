package convenientfoundation.event.resourceDrop;

import convenientfoundation.item.ModItems;
import convenientfoundation.libs.LibMod;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Necro on 9/12/2017.
 */
@Mod.EventBusSubscriber(modid = LibMod.MODID)
public class GeodeDropHandler {
    public static List<String> ore_ids = Arrays.asList(
            Blocks.COAL_ORE.getRegistryName().toString(),
            Blocks.DIAMOND_ORE.getRegistryName().toString(),
            Blocks.LAPIS_ORE.getRegistryName().toString(),
            Blocks.EMERALD_ORE.getRegistryName().toString()
    );

    public static final float CHANCE=1/32f;

    @SubscribeEvent
    public static void onBlockHarvested(BlockEvent.HarvestDropsEvent event){
        World w=event.getWorld();
        if(w.isRemote||event.isSilkTouching())
            return;

        Block b=event.getState().getBlock();
        if(ore_ids.contains(b.getRegistryName().toString())){
            for(int i=0;i<event.getFortuneLevel()+1;i++){
                if(w.rand.nextFloat()<CHANCE)
                    event.getDrops().add(new ItemStack(ModItems.GEODE));
            }
        }
    }
}
