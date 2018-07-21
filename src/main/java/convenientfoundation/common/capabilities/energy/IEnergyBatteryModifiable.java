package convenientfoundation.common.capabilities.energy;

import convenientfoundation.common.energy.EnergyStack;

public interface IEnergyBatteryModifiable extends IEnergyBattery
{
    void setEnergy(EnergyStack stack);
    void setEnergyAmount(int amount);
    void setCapacity(int capacity);
}
