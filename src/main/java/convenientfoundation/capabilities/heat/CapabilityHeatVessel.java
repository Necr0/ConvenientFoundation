package convenientfoundation.capabilities.heat;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

/**
 * Created by Necro on 8/27/2017.
 */
public class CapabilityHeatVessel {
    @CapabilityInject(IHeatVessel.class)
    public static Capability<IHeatVessel> HEAT_VESSEL_CAPABILITY = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IHeatVessel.class, new DefaultHeatAccumulator<>(), () -> new HeatVessel());
    }

    private static class DefaultHeatAccumulator<T extends IHeatVessel> implements Capability.IStorage<T> {
        @Override
        public NBTBase writeNBT(Capability<T> capability, T instance, EnumFacing side)
        {
            if (!(instance instanceof HeatVessel))
                throw new RuntimeException("Provided IHeatVessel is not instance of HeatVessel");
            HeatVessel vessel = (HeatVessel) instance;
            return vessel.serializeNBT();
        }

        @Override
        public void readNBT(Capability<T> capability, T instance, EnumFacing side, NBTBase nbt)
        {
            if (!(instance instanceof HeatVessel))
                throw new RuntimeException("Provided IHeatVessel is not instance of HeatVessel");
            NBTTagDouble tags = (NBTTagDouble) nbt;
            HeatVessel vessel = (HeatVessel) instance;
            vessel.deserializeNBT(tags);
        }
    }
}
