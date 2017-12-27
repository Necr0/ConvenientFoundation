package convenientfoundation.common.container.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public interface IFakeSlot {
    ItemStack slotClick(Container container, int button, ClickType mode, EntityPlayer player);
}
