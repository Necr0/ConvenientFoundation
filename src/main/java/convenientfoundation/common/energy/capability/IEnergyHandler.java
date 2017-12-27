package convenientfoundation.common.energy.capability;

import convenientfoundation.common.energy.EnergyStack;

import javax.annotation.Nullable;

public interface IEnergyHandler
{
    @Nullable
    EnergyStack insert(EnergyStack energy, boolean simulate);
    @Nullable
    EnergyStack extract(EnergyStack energy, boolean simulate);
    @Nullable
    EnergyStack extract(int amount, boolean simulate);
}
