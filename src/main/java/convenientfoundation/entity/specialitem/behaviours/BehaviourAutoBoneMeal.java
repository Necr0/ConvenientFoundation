package convenientfoundation.entity.specialitem.behaviours;

import convenientfoundation.entity.specialitem.IEntitySpecialItemBehaviour;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemDye;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BehaviourAutoBoneMeal implements IEntitySpecialItemBehaviour {

    @Override
    public void onCreate(EntityItem item) {
    }

    @Override
    public boolean onAttackItemEntityFrom(EntityItem item, DamageSource source, float damage) { return true; }

    @Override
    public void onItemEntityUpdate(EntityItem item) {
        World w=item.getEntityWorld();
        if(w.isRemote)
            return;

        BlockPos pos = new BlockPos(MathHelper.floor(item.posX), MathHelper.floor(item.posY), MathHelper.floor(item.posZ));
        ItemDye.applyBonemeal(item.getItem(),w,pos);
        if(!item.getItem().isEmpty() && item.onGround){
            pos=pos.down();
            ItemDye.applyBonemeal(item.getItem(),w,pos);
        }
    }

}
