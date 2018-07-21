package convenientfoundation.common.item;

import convenientfoundation.common.item.base.EnumItemCategory;
import convenientfoundation.libs.LibItems;
import convenientfoundation.common.item.base.CFItem;
import convenientfoundation.libs.LibMod;
import convenientfoundation.util.Helper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ItemSlimeBucket extends CFItem {
    public ItemSlimeBucket() {
        super(LibItems.slime_bucket);
        this.setCategory(EnumItemCategory.RESOURCE).setDefaultInfo(false).setMaxStackSize(1).setHasSubtypes(true);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(Items.BUCKET);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @SubscribeEvent
    public void onEntityInteract(PlayerInteractEvent.EntityInteract event){
        ItemStack stack=event.getItemStack();
        EntityPlayer player=event.getEntityPlayer();

        if (stack.getItem() == Items.BUCKET)
        {
            if(!(event.getTarget() instanceof EntitySlime))
                return;
            EntitySlime target=((EntitySlime)event.getTarget());
            if(target.getHealth()<=0||target.isDead)
                return;

            player.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);
            stack.shrink(1);

            String reg=EntityRegistry.getEntry(event.getTarget().getClass()).getRegistryName().toString();
            ItemStack new_stack=ItemStack.EMPTY;
            if(reg.equals("minecraft:slime"))
                new_stack=new ItemStack(this,1,0);
            else if(reg.equals("minecraft:magma_cube"))
                new_stack=new ItemStack(this,1,1);
            else
                return;

            target.attackEntityFrom(DamageSource.STARVE,1.2f+event.getWorld().rand.nextFloat());

            if (stack.isEmpty())
            {
                player.setHeldItem(event.getHand(), new_stack);
            }
            else
                Helper.insertOrDrop(player, new_stack);

            event.setCanceled(true);
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(worldIn.isRemote)
            return new ActionResult<>(EnumActionResult.SUCCESS,new ItemStack(Items.BUCKET));
        ItemStack stack=playerIn.getHeldItem(handIn);
        if(stack.getItemDamage()==0)
            Helper.insertOrDrop(playerIn,new ItemStack(Items.SLIME_BALL,1+worldIn.rand.nextInt(2)));
        else
            Helper.insertOrDrop(playerIn,new ItemStack(Items.MAGMA_CREAM,1+worldIn.rand.nextInt(2)));
        return new ActionResult<>(EnumActionResult.SUCCESS,new ItemStack(Items.BUCKET));
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab))
        {
            items.add(new ItemStack(this,1,0));
            items.add(new ItemStack(this,1,1));
        }
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        if(stack.getItemDamage()==0)
            return super.getTranslationKey(stack);
        else
            return "item." + LibMod.MODID + ":magma_cube_bucket";
    }
}
