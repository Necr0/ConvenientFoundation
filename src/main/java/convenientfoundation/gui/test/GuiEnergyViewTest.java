package convenientfoundation.gui.test;

import convenientfoundation.capabilities.entity.EntityStack;
import convenientfoundation.energy.capability.EnergyBattery;
import convenientfoundation.energy.EnergyStack;
import convenientfoundation.energy.ModEnergy;
import convenientfoundation.capabilities.heat.HeatVessel;
import convenientfoundation.entity.undeadMiner.EntityUndeadMiner;
import convenientfoundation.gui.CFGuiScreen;
import convenientfoundation.gui.widget.EntityStackView;
import convenientfoundation.gui.widget.HeatDisplay;
import convenientfoundation.gui.widget.energy.EnergyBatteryView;
import convenientfoundation.gui.widget.energy.EnergyStackView;
import convenientfoundation.libs.LibImages;
import convenientfoundation.util.Helper;
import net.minecraft.entity.monster.EntityCreeper;

/**
 * Created by Necro on 8/28/2017.
 */
public class GuiEnergyViewTest extends CFGuiScreen {
    public GuiEnergyViewTest() {
        super(LibImages.DEMO_BACKGROUND);
    }

    @Override
    public void initGui() {
        super.initGui();
        addWidget(new EnergyStackView(new EnergyStack(ModEnergy.REDSTONE,32604123),leftX+12,topY+12));
        addWidget(new EntityStackView(new EntityStack(new EntityCreeper(Helper.getClientWorld())),leftX+12,topY+32));
        EnergyBattery battery1=new EnergyBattery(150);
        battery1.setEnergy(new EnergyStack(ModEnergy.ENDER,150));
        addWidget(new EnergyBatteryView(battery1,leftX+12+32,topY+12));
        EnergyBattery battery2=new EnergyBattery(150);
        battery2.setEnergy(new EnergyStack(ModEnergy.REDSTONE,150));
        addWidget(new EnergyBatteryView(battery2,leftX+12+64,topY+12));
        HeatVessel accumulator=new HeatVessel(90.0d,1.0d,100.0d);
        addWidget(new HeatDisplay(leftX+12+128,topY+12,accumulator));
    }
}
