package convenientfoundation.item;

import convenientfoundation.block.wrench.IConfigurable;
import convenientfoundation.block.wrench.IDismantleable;
import convenientfoundation.item.base.CFItem;
import convenientfoundation.item.base.EnumItemCategory;
import convenientfoundation.libs.LibItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by Necro on 7/22/2017.
 */
public class ItemWrench extends CFItem {

    public ItemWrench() {
        super(LibItems.wrench);
        this.setMaxStackSize(1);
        this.setCategory(EnumItemCategory.TOOL);
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        if (block != null) {
            if (!player.isSneaking()) {
                if (!world.isRemote) {
                    if(block instanceof IConfigurable){
                        if(((IConfigurable) block).canConfigure(player,world,pos,side)){
                            ((IConfigurable) block).configure(player,world,pos,side);
                            return EnumActionResult.SUCCESS;
                        }
                    }else if(block.hasTileEntity(state)){
                        TileEntity te=world.getTileEntity(pos);
                        if(te instanceof IConfigurable){
                            if(((IConfigurable) te).canConfigure(player,world,pos,side)){
                                ((IConfigurable) te).configure(player,world,pos,side);
                                return EnumActionResult.SUCCESS;
                            }
                        }
                    }
                    block.rotateBlock(world, pos, side);
                    return EnumActionResult.SUCCESS;
                }
                player.swingArm(hand);
                return EnumActionResult.PASS;
            } else {
                if (block instanceof IDismantleable) {
                    IDismantleable d = (IDismantleable) block;
                    if (!world.isRemote && d.canDismantle(player, world, pos)) {
                        d.dismantleBlock(player, world, pos, false);
                        return EnumActionResult.SUCCESS;
                    }
                    player.swingArm(hand);
                    return EnumActionResult.PASS;
                }
            }
        }
        return EnumActionResult.FAIL;
    }
}
