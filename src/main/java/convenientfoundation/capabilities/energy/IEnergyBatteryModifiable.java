package convenientfoundation.capabilities.energy;

public interface IEnergyBatteryModifiable extends IEnergyBattery
{
    void setEnergy(EnergyStack stack);
    void setEnergyAmount(int amount);
    void setCapacity(int capacity);
}
