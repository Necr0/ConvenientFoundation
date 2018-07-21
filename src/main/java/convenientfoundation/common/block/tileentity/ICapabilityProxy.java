package convenientfoundation.common.block.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.IItemHandler;

public interface ICapabilityProxy {
    <T> T tryFetchHandler(Capability<T> capability, EnumFacing f, int proxyIndex);
}
