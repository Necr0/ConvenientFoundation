package convenientfoundation.common.item.probe;

import convenientfoundation.common.item.base.CFItem;
import convenientfoundation.common.item.base.EnumItemCategory;
import convenientfoundation.libs.LibItems;
import convenientfoundation.util.ClientHelper;
import convenientfoundation.util.Helper;
import net.minecraft.entity.player.EntityPlayer;
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
public class ItemBlockProbe extends CFItem {

    public ItemBlockProbe() {
        super(LibItems.block_probe);
        this.setMaxStackSize(1);
        this.setCategory(EnumItemCategory.DEBUG).setDefaultInfo(false);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(worldIn.isRemote){
            ClientHelper.displayGui(new GuiBlockProbe(worldIn,pos));
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }
}
