package convenientfoundation.common.event.inventoryTick;

import convenientfoundation.common.inventory.EnumInventory;
import convenientfoundation.common.inventory.InventoryIterator;
import convenientfoundation.common.inventory.SlotNotation;
import convenientfoundation.libs.LibMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = LibMod.MODID)
public class PlayerInventoryTickHandler {
	
	@SubscribeEvent
    public static void onPlayerInventoryTick(TickEvent.PlayerTickEvent e)
    {
		EntityPlayer player = e.player;
		Iterable<SlotNotation> iter= InventoryIterator.getIterable(player, EnumInventory.MAIN);
		for (SlotNotation slot : iter) {
			ItemStack stack=slot.getItem();
			if(stack!=null && stack.getItem() instanceof IPlayerInventoryTick){
				((IPlayerInventoryTick)stack.getItem()).onPlayerInventoryTick(stack, slot, player);
			}
		}
    }
}
