package convenientfoundation.item;

import convenientfoundation.libs.LibItems;
import convenientfoundation.item.base.CFItem;
import convenientfoundation.util.Helper;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ItemSlimeBucket extends CFItem {
    public ItemSlimeBucket() {
        super(LibItems.slime_bucket);
        this.setDefaultInfo(false).setMaxStackSize(1);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(Items.BUCKET);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @SubscribeEvent
    public void onEntityInteract(PlayerInteractEvent.EntityInteract event){
        ItemStack stack=event.getItemStack();
        EntityPlayer player=event.getEntityPlayer();

        if (stack.getItem() == Items.BUCKET)
        {
            if(!(EntityRegistry.getEntry(event.getTarget().getClass()).getRegistryName().toString().equals("minecraft:slime") && ((EntitySlime)event.getTarget()).getSlimeSize()>2))
                return;
            player.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);
            stack.shrink(1);

            if (stack.isEmpty())
            {
                player.setHeldItem(event.getHand(), new ItemStack(this));
            }
            else
                Helper.insertOrDrop(player, new ItemStack(this));

            event.setCanceled(true);
        }
    }
}
