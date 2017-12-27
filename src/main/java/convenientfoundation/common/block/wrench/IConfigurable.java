package convenientfoundation.common.block.wrench;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by Necro on 7/22/2017.
 */
public interface IConfigurable {
    boolean canConfigure(@Nullable EntityPlayer player, World world, BlockPos pos, @Nullable EnumFacing face);

    void configure(@Nullable EntityPlayer player, World world, BlockPos pos, @Nullable EnumFacing face);
}
