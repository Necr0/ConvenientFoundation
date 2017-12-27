package convenientfoundation.common.block.wrench;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public interface IDismantleable {
    boolean canDismantle(@Nullable EntityPlayer player, World world, BlockPos pos);

    NonNullList<ItemStack> dismantleBlock(@Nullable EntityPlayer player, World world, BlockPos pos, boolean returnDrops);
}
