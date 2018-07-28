package convenientfoundation.common.item;

import convenientfoundation.common.item.base.CFItem;
import convenientfoundation.common.item.base.EnumItemCategory;
import convenientfoundation.common.loot.ModLoot;
import convenientfoundation.libs.LibItems;
import convenientfoundation.util.ItemHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;

public class ItemGeode extends CFItem {
    public ItemGeode() {
        super(LibItems.geode);
        this.setCategory(EnumItemCategory.RESOURCE).setDefaultInfo(false).setMaxStackSize(16);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack=playerIn.getHeldItem(handIn);
        if(world.isRemote)
            return new ActionResult<>(EnumActionResult.SUCCESS,new ItemStack(this,stack.getCount()-1));
        LootTable loottable = world.getLootTableManager().getLootTableFromLocation(ModLoot.geode);
        LootContext.Builder builder = (new LootContext.Builder((WorldServer)world)).withPlayer(playerIn).withLuck(playerIn.getLuck());

        for (ItemStack itemstack : loottable.generateLootForPools(world.rand, builder.build()))
        {
            ItemHelper.insertOrDrop(playerIn,itemstack);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS,new ItemStack(this,stack.getCount()-1));
    }
}
