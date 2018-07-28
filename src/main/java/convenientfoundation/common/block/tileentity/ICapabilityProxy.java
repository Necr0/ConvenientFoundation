package convenientfoundation.common.block.tileentity;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public interface ICapabilityProxy {
    <T> T tryFetchHandler(Capability<T> capability, EnumFacing f, int proxyIndex);
}
