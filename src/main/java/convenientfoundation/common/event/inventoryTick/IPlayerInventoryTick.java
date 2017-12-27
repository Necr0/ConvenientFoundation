package convenientfoundation.common.event.inventoryTick;

import convenientfoundation.common.inventory.SlotNotation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IPlayerInventoryTick {
    void onPlayerInventoryTick(ItemStack item, SlotNotation slot, EntityPlayer player);
}
