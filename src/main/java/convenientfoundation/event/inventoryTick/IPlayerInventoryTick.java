package convenientfoundation.event.inventoryTick;

import convenientfoundation.inventory.SlotNotation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IPlayerInventoryTick {
    void onPlayerInventoryTick(ItemStack item, SlotNotation slot, EntityPlayer player);
}
