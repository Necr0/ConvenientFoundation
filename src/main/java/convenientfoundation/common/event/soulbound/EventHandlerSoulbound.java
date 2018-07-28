package convenientfoundation.common.event.soulbound;

import convenientfoundation.common.enchantments.EnchantmentEverlasting;
import convenientfoundation.libs.LibMod;
import convenientfoundation.util.ItemHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.Iterator;

@Mod.EventBusSubscriber(modid = LibMod.MODID)
public class EventHandlerSoulbound {
	
	@SubscribeEvent
	public static void onPlayerDrops(PlayerDropsEvent e)
    {
    	EntityPlayer player=e.getEntityPlayer();
    	if(player.getEntityWorld().isRemote || player.getEntityWorld().getGameRules().getBoolean("keepInventory"))
    		return;
		Iterator<EntityItem> i=e.getDrops().iterator();
    	while(i.hasNext()){
    		EntityItem ent=i.next();
    		ItemStack stack=ent.getItem();
    		if(EnchantmentHelper.getEnchantmentLevel(EnchantmentEverlasting.INSTANCE,stack)>0 ||
					stack.getItem() instanceof ISoulbound && ((ISoulbound)stack.getItem()).isSoulbound(stack, player)){
				ItemHelper.insertOrDrop(player,stack);
				i.remove();
    		}
    	}
    }
	
	@SubscribeEvent
	public static void onPlayerClone(PlayerEvent.Clone e)
    {
		EntityPlayer clone=e.getEntityPlayer();
		if(clone.getEntityWorld().isRemote || clone.getEntityWorld().getGameRules().getBoolean("keepInventory"))
			return;
		EntityPlayer original=e.getOriginal();
		IItemHandler h=original.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
		for(int slot=0;slot<h.getSlots();slot++){
			ItemStack stack=h.getStackInSlot(slot);
    		if(EnchantmentHelper.getEnchantmentLevel(EnchantmentEverlasting.INSTANCE,stack)>0 ||
					stack.getItem() instanceof ISoulbound&&((ISoulbound)stack.getItem()).isSoulbound(stack, original)){
				ItemHelper.insertOrDrop(clone,stack);
				h.extractItem(slot,64,false);
    		}
		}
    }
}
