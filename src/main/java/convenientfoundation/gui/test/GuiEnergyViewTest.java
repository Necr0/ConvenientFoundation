package convenientfoundation.gui.test;

import convenientfoundation.capabilities.energy.EnergyBattery;
import convenientfoundation.capabilities.energy.EnergyStack;
import convenientfoundation.capabilities.energy.ModEnergy;
import convenientfoundation.capabilities.heat.HeatVessel;
import convenientfoundation.gui.CFGuiScreen;
import convenientfoundation.gui.widget.HeatDisplay;
import convenientfoundation.gui.widget.energy.EnergyBatteryView;
import convenientfoundation.gui.widget.energy.EnergyStackView;
import convenientfoundation.libs.LibImages;

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
        EnergyBattery battery=new EnergyBattery(150);
        battery.setEnergy(new EnergyStack(ModEnergy.ENDER,150));
        addWidget(new EnergyBatteryView(battery,leftX+12+32,topY+12));
        HeatVessel accumulator=new HeatVessel(90.0d,1.0d,100.0d);
        addWidget(new HeatDisplay(leftX+12+128,topY+12,accumulator));
    }
}
