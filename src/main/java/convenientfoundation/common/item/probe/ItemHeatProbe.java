package convenientfoundation.common.item.probe;

import convenientfoundation.client.gui.test.GuiEnergyViewTest;
import convenientfoundation.common.capabilities.heat.CapabilityHeatVessel;
import convenientfoundation.common.capabilities.heat.IHeatVessel;
import convenientfoundation.common.item.base.CFItem;
import convenientfoundation.common.item.base.EnumItemCategory;
import convenientfoundation.libs.LibItems;
import convenientfoundation.util.ClientHelper;
import convenientfoundation.util.Helper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Necro on 7/22/2017.
 */
public class ItemHeatProbe extends CFItem {

    public ItemHeatProbe() {
        super(LibItems.heat_probe);
        this.setMaxStackSize(1);
        this.setCategory(EnumItemCategory.DEBUG).setDefaultInfo(false);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(worldIn.isRemote){
            TileEntity te=worldIn.getTileEntity(pos);
            if(te!=null){
                IHeatVessel accumulator;
                if(te.hasCapability(CapabilityHeatVessel.HEAT_VESSEL_CAPABILITY,facing))
                    accumulator=te.getCapability(CapabilityHeatVessel.HEAT_VESSEL_CAPABILITY,facing);
                else if(te.hasCapability(CapabilityHeatVessel.HEAT_VESSEL_CAPABILITY,null))
                    accumulator=te.getCapability(CapabilityHeatVessel.HEAT_VESSEL_CAPABILITY,null);
                else
                    return EnumActionResult.FAIL;
                ClientHelper.displayGui(new GuiHeatProbe(pos,te,accumulator));
                return EnumActionResult.SUCCESS;
            }
        }
        return EnumActionResult.FAIL;
    }
}
