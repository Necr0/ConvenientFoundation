package convenientfoundation.util;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.Collection;

public class ItemHelper {
	
	public static boolean match(ItemStack s1,ItemStack s2,boolean ignoreDamage,boolean ignoreNBT){
		if(s1.isEmpty()||s2.isEmpty())
			return false;
		if(!ignoreDamage&&!ignoreNBT){
			return s1.isItemEqual(s2)&&ItemStack.areItemStackTagsEqual(s1, s2);
		}else if(ignoreDamage&&!ignoreNBT){
			return s1.getItem()==s2.getItem()&&ItemStack.areItemStackTagsEqual(s1, s2);
		}else if(!ignoreDamage){
			return s1.isItemEqual(s2);
		}else{
			return s1.getItem()==s2.getItem();
		}
	}
	
	public static boolean match(Collection<ItemStack> c,ItemStack s,boolean ignoreDamage,boolean ignoreNBT){
		for(ItemStack stack:c){
			if(match(stack, s, ignoreDamage, ignoreNBT))
				return true;
		}
		return false;
	}

	public static NonNullList<ItemStack> deepCopyItemList(NonNullList<ItemStack> list){
		NonNullList<ItemStack> ret=NonNullList.create();
		for (ItemStack s:list)
			ret.add(s.copy());
		return ret;
	}
	public static EntityItem spawnItemInPlace(World w, double x, double y, double z, ItemStack i){
		EntityItem e=new EntityItem(w, x, y, z, i);
		e.motionX = 0;
		e.motionY = 0;
		e.motionZ = 0;
		if(!w.isRemote)
			w.spawnEntity(e);
		return e;
	}

	public static EntityItem spawnItem(World w,double x,double y,double z,ItemStack i){
		EntityItem e=new EntityItem(w, x, y, z, i);
		if(!w.isRemote)
			w.spawnEntity(e);
		return e;
	}


	public static void insertOrDrop(EntityPlayer p, ItemStack stack){
		if(stack.getCount()==0)
			return;
		World w=p.world;
		if(p.inventory.addItemStackToInventory(stack))
			w.playSound(null, p.posX, p.posY, p.posZ, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((w.rand.nextFloat() - w.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
		if(!stack.isEmpty()){
			EntityItem e=ItemHelper.spawnItemInPlace(w, p.posX, p.posY, p.posZ, stack);
			e.setOwner(p.getName());
		}
	}
}
