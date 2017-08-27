package convenientfoundation.capabilities.energy;

import javax.annotation.Nullable;

public interface IEnergyBattery extends IEnergyHandler
{
    @Nullable
    EnergyStack getEnergy();
    int getEnergyAmount();
    int getCapacity();
}
