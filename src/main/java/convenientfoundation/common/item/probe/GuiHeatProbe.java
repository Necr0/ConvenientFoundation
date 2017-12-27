package convenientfoundation.common.item.probe;

import convenientfoundation.common.capabilities.heat.IHeatVessel;
import convenientfoundation.client.gui.CFGuiScreen;
import convenientfoundation.client.gui.widget.label.DynamicLabel;
import convenientfoundation.client.gui.widget.label.Label;
import convenientfoundation.libs.LibImages;
import convenientfoundation.libs.LibMod;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

/**
 * Created by Necro on 9/4/2017.
 */
public class GuiHeatProbe extends CFGuiScreen {
    public BlockPos pos;
    public IBlockState state;
    public TileEntity te;
    public IHeatVessel vessel;

    public GuiHeatProbe(BlockPos pos, TileEntity te, IHeatVessel vessel) {
        super(LibImages.DEMO_BACKGROUND_WIDE);
        this.pos = pos;
        this.state = te.getWorld().getBlockState(pos);
        this.te = te;
        this.vessel = vessel;
    }

    @Override
    public void initGui() {
        super.initGui();
        addWidget(new Label(leftX+5,topY+5, I18n.format("gui."+LibMod.MODID+":blockprobe.pos", pos.getX(), pos.getY(), pos.getZ())));
        addWidget(new DynamicLabel(leftX+5,topY+5,
                () -> I18n.format("gui."+LibMod.MODID+":heatprobe.heat",
                        this.vessel.getHeatLevel(),
                        this.vessel.canOverheat()?this.vessel.getCriticalHeat():0
                )
        ));
        addWidget(new Label(leftX+5,topY+45, I18n.format("gui."+LibMod.MODID+":blockprobe.tileentity.name", te==null?"null":TileEntity.getKey(te.getClass()))));
        addWidget(new Label(leftX+5,topY+55, I18n.format("gui."+LibMod.MODID+":blockprobe.tileentity.class", te==null?"null":te.getClass().getCanonicalName())));
    }
}
