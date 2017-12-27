package convenientfoundation.common.block;

import net.minecraft.block.state.IBlockState;

/**
 * Created by Necro on 6/20/2017.
 */
public interface IEnderProof {
    default boolean isEnderProof(IBlockState state){
        return true;
    }
}
