package convenientfoundation.util;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlockHelper {

    public static boolean doesOreDictMatch(IBlockState b, String entry, boolean startsWith){
        if(b.getBlock()==Blocks.LIT_REDSTONE_ORE)
            b=Blocks.REDSTONE_ORE.getDefaultState();
        List<ItemStack> l=new ArrayList<>();
        if(startsWith){
            for(String n:OreDictionary.getOreNames()){
                if(n.startsWith(entry))
                    l.addAll(OreDictionary.getOres(n));
            }
        }else{
            if(OreDictionary.doesOreNameExist(entry))
                l.addAll(OreDictionary.getOres(entry));
        }
        for(ItemStack s:l){
            if(s.getItem() instanceof ItemBlock){
                ItemBlock ib=(ItemBlock)s.getItem();
                if(ib.getBlock()==b.getBlock()){
                    return true;
                }
            }
        }
        return false;
    }

    public static String getOreDictMatch(IBlockState b,String entry,boolean startsWith){
        if(b.getBlock()==Blocks.LIT_REDSTONE_ORE)
            b=Blocks.REDSTONE_ORE.getDefaultState();
        Multimap<String,ItemStack> m=ArrayListMultimap.create();
        if(startsWith){
            for(String n:OreDictionary.getOreNames()){
                if(n.startsWith(entry))
                    m.putAll(n,OreDictionary.getOres(n));
            }
        }else{
            if(OreDictionary.doesOreNameExist(entry))
                m.putAll(entry,OreDictionary.getOres(entry));
        }
        for(Map.Entry<String,ItemStack> e:m.entries()){
            if(e.getValue().getItem() instanceof ItemBlock){
                ItemBlock ib=(ItemBlock)e.getValue().getItem();
                if(ib.getBlock()==b.getBlock()){
                    return e.getKey();
                }
            }
        }
        return null;
    }

    public static List<String> getBlockOreDict(IBlockState b){
        ArrayList<String> ret=new ArrayList<>();
        for(String ore:OreDictionary.getOreNames()){
            for(ItemStack stack:OreDictionary.getOres(ore)){
                if(stack.getItem() instanceof ItemBlock){
                    ItemBlock ib=(ItemBlock)stack.getItem();
                    if(ib.getBlock()==b.getBlock()){
                        ret.add(ore);
                    }
                }
            }
        }
        return ret;
    }

    public static boolean checkForFire(IBlockAccess world, BlockPos pos){
        for (EnumFacing f:EnumFacing.values()) {
            if(world.getBlockState(pos.offset(f)).getBlock()==Blocks.FIRE)
                return true;
        }
        return false;
    }
}
