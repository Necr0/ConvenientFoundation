package convenientfoundation.capabilities.energy;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fluids.capability.IFluidHandler;

/**
 * Created by Necro on 8/27/2017.
 */
public class CapabilityEnergyHandler {
    @CapabilityInject(IEnergyHandler.class)
    public static Capability<IFluidHandler> ENERGY_HANDLER_CAPABILITY = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IEnergyHandler.class, new DefaultEnergyHandlerStorage<>(), () -> new EnergyBattery());
    }

    private static class DefaultEnergyHandlerStorage<T extends IEnergyHandler> implements Capability.IStorage<T> {
        @Override
        public NBTBase writeNBT(Capability<T> capability, T instance, EnumFacing side)
        {
            if (!(instance instanceof EnergyBattery))
                throw new RuntimeException("Provided IEnergyHandler is not instance of EnergyBattery");
            EnergyBattery battery = (EnergyBattery) instance;
            return battery.serializeNBT();
        }

        @Override
        public void readNBT(Capability<T> capability, T instance, EnumFacing side, NBTBase nbt)
        {
            if (!(instance instanceof EnergyBattery))
                throw new RuntimeException("Provided IEnergyHandler is not instance of EnergyBattery");
            NBTTagCompound tags = (NBTTagCompound) nbt;
            EnergyBattery battery = (EnergyBattery) instance;
            battery.deserializeNBT(tags);
        }
    }
}
