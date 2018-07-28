package convenientfoundation.common.event.resourceDrop;

import convenientfoundation.common.loot.ModLoot;
import convenientfoundation.libs.LibMod;
import convenientfoundation.util.ItemHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Necro on 9/12/2017.
 */
@Mod.EventBusSubscriber(modid = LibMod.MODID)
public class DragonDropHandler {
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event){
        EntityLivingBase e=event.getEntityLiving();
        World w=e.world;
        if(w.isRemote||!(e instanceof EntityDragon))
            return;

        LootTable loottable = w.getLootTableManager().getLootTableFromLocation(ModLoot.ender_dragon);
        LootContext.Builder builder = (new LootContext.Builder((WorldServer)w));
        for (ItemStack itemstack : loottable.generateLootForPools(w.rand, builder.build()))
        {
            if(itemstack.isEmpty())
                continue;
            for(int i=0;i<itemstack.getCount();i++){
                ItemStack drop_stack=itemstack.copy();
                drop_stack.setCount(1);
                ItemHelper.spawnItem(w,e.posX-1.5d+(w.rand.nextDouble()*3),e.posY-1.5d+(w.rand.nextDouble()*3),e.posZ-1.5d+(w.rand.nextDouble()*3),drop_stack);
            }
        }
    }
}
