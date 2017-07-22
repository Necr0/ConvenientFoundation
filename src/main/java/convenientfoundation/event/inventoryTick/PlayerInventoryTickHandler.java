package convenientfoundation.event.inventoryTick;

import convenientfoundation.inventory.EnumInventory;
import convenientfoundation.inventory.InventoryIterator;
import convenientfoundation.inventory.SlotNotation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class PlayerInventoryTickHandler {
	
	@SubscribeEvent
    public void onPlayerInventoryTick(TickEvent.PlayerTickEvent e)
    {
		EntityPlayer player = e.player;
		Iterable<SlotNotation> iter= InventoryIterator.getIterable(player, EnumInventory.MAIN);
		for (SlotNotation slot : iter) {
			ItemStack stack=slot.getItem();
			if(stack!=null && stack.getItem() instanceof IPlayerInventoryTick){
				((IPlayerInventoryTick)stack.getItem()).onPlayerInventoryTick(stack, slot, player);;
			}
		}
    }
}
