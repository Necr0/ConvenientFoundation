package convenientfoundation.energy.capability;

import convenientfoundation.energy.EnergyStack;

import javax.annotation.Nullable;

public interface IEnergyBattery extends IEnergyHandler
{
    @Nullable
    EnergyStack getEnergy();
    int getEnergyAmount();
    int getCapacity();
}
