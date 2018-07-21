package convenientfoundation.common.capabilities.energy;

import convenientfoundation.common.energy.EnergyStack;

import javax.annotation.Nullable;

public interface IEnergyBattery extends IEnergyHandler
{
    @Nullable
    EnergyStack getEnergy();
    int getEnergyAmount();
    int getCapacity();
}
