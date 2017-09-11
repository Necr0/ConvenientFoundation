package convenientfoundation.item.probe;

import convenientfoundation.gui.CFGuiScreen;
import convenientfoundation.gui.widget.label.Label;
import convenientfoundation.libs.LibImages;
import convenientfoundation.libs.LibMod;
import convenientfoundation.util.Helper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * Created by Necro on 9/4/2017.
 */
public class GuiBlockProbe extends CFGuiScreen {
    public BlockPos pos;
    public IBlockState state;
    public TileEntity te;

    public GuiBlockProbe(IBlockAccess w, BlockPos pos) {
        super(LibImages.DEMO_BACKGROUND_WIDE);
        this.pos = pos;
        this.state = w.getBlockState(pos);
        this.te = w.getTileEntity(pos);
    }

    @Override
    public void initGui() {
        super.initGui();
        addWidget(new Label(leftX+5,topY+5, I18n.format("gui."+LibMod.MODID+":blockprobe.pos", pos.getX(), pos.getY(), pos.getZ())));
        addWidget(new Label(leftX+5,topY+15, I18n.format("gui."+LibMod.MODID+":blockprobe.tile.state", state.toString())));
        addWidget(new Label(leftX+5,topY+25, I18n.format("gui."+LibMod.MODID+":blockprobe.tile.class", state.getBlock().getClass().getCanonicalName())));
        String oredict=String.join(", ",Helper.getBlockOreDict(state));
        addWidget(new Label(leftX+5,topY+35, I18n.format("gui."+LibMod.MODID+":blockprobe.oredict", oredict.equals("")?"-":oredict)));
        addWidget(new Label(leftX+5,topY+45, I18n.format("gui."+LibMod.MODID+":blockprobe.tileentity.name", te==null?"null":TileEntity.getKey(te.getClass()))));
        addWidget(new Label(leftX+5,topY+55, I18n.format("gui."+LibMod.MODID+":blockprobe.tileentity.class", te==null?"null":te.getClass().getCanonicalName())));
    }
}
