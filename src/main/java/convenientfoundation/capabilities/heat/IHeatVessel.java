package convenientfoundation.capabilities.heat;

/**
 * Created by Necro on 8/30/2017.
 */
public interface IHeatVessel {
    double getHeatLevel();
    void setHeatLevel(double amount);
    void increaseHeatLevel(double amount);
    void decreaseHeatLevel(double amount);
    boolean canOverheat();
    double getCriticalHeat();
}
