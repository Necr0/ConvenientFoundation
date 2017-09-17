package convenientfoundation.block;

import convenientfoundation.block.base.CFBlock;
import convenientfoundation.item.ModItems;
import convenientfoundation.item.base.EnumItemCategory;
import convenientfoundation.libs.LibBlocks;
import convenientfoundation.libs.LibMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = LibMod.MODID)
public class ModBlocks {
    public static final Block ARCANE_BLOCK = new CFBlock(LibBlocks.arcane_block, Material.ROCK).setCategory(EnumItemCategory.RESOURCE).setDefaultInfo(false).setHardness(5.0F).setResistance(10.0F);


    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> evt) {
        IForgeRegistry<Block> r = evt.getRegistry();

        r.register(ARCANE_BLOCK);
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> evt) {
        IForgeRegistry<Item> r = evt.getRegistry();

        r.register(itemBlock(ARCANE_BLOCK));

        OreDictionary.registerOre(LibBlocks.ore_arcane_block,ARCANE_BLOCK);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ModItems.registerModelLocation(ItemBlock.getItemFromBlock(ARCANE_BLOCK));
    }

    public static ItemBlock itemBlock(Block b){
        ItemBlock i=new ItemBlock(ARCANE_BLOCK);
        i.setRegistryName(b.getRegistryName());
        return i;
    }
}
