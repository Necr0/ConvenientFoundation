package convenientfoundation.item;

import convenientfoundation.item.base.CFItem;
import convenientfoundation.item.base.EnumItemCategory;
import convenientfoundation.item.probe.ItemBlockProbe;
import convenientfoundation.item.probe.ItemHeatProbe;
import convenientfoundation.libs.LibItems;
import convenientfoundation.libs.LibMod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = LibMod.MODID)
public class ModItems {

    public static final Item WRENCH = new ItemWrench();
    public static final Item SLIME_BUCKET = new ItemSlimeBucket();
    public static final Item BLOCK_PROBE = new ItemBlockProbe();
    public static final Item HEAT_PROBE = new ItemHeatProbe();
    //
    public static final Item ENDER_HEART = new CFItem(LibItems.ender_heart).setCategory(EnumItemCategory.RESOURCE).setDefaultInfo(false);
    public static final Item MOLTEN_CORE = new CFItem(LibItems.molten_core).setCategory(EnumItemCategory.RESOURCE).setDefaultInfo(false);
    public static final Item ARCANE_DUST = new CFItem(LibItems.arcane_dust).setCategory(EnumItemCategory.RESOURCE).setDefaultInfo(false);
    public static final Item DRAGON_SCALE = new CFItem(LibItems.dragon_scale).setCategory(EnumItemCategory.RESOURCE).setDefaultInfo(false);
    public static final Item GEODE = new ItemGeode();
    //
    public static final Item IRON_ROD = new CFItem(LibItems.iron_rod).setCategory(EnumItemCategory.CRAFTING_MATERIAL).setDefaultInfo(false);
    public static final Item GOLD_ROD = new CFItem(LibItems.gold_rod).setCategory(EnumItemCategory.CRAFTING_MATERIAL).setDefaultInfo(false);
    public static final Item DIAMOND_SHARD = new CFItem(LibItems.diamond_shard).setCategory(EnumItemCategory.RESOURCE).setDefaultInfo(false);
    public static final Item EMERALD_SHARD = new CFItem(LibItems.emerald_shard).setCategory(EnumItemCategory.RESOURCE).setDefaultInfo(false);


    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> evt) {
        IForgeRegistry<Item> r = evt.getRegistry();

        r.register(WRENCH);
        r.register(SLIME_BUCKET);
        r.register(BLOCK_PROBE);
        r.register(HEAT_PROBE);
        //
        r.register(ENDER_HEART);
        r.register(MOLTEN_CORE);
        r.register(ARCANE_DUST);
        r.register(DRAGON_SCALE);
        r.register(GEODE);
        //
        r.register(IRON_ROD);
        r.register(GOLD_ROD);
        r.register(DIAMOND_SHARD);
        r.register(EMERALD_SHARD);
        //
        OreDictionary.registerOre("slimeball",SLIME_BUCKET);
        OreDictionary.registerOre("enderpearl",ENDER_HEART);
        OreDictionary.registerOre(LibItems.ore_ender_heart,ENDER_HEART);
        OreDictionary.registerOre(LibItems.ore_molten_core,MOLTEN_CORE);
        OreDictionary.registerOre(LibItems.ore_arcane_dust,ARCANE_DUST);
        OreDictionary.registerOre(LibItems.ore_dragon_scale,DRAGON_SCALE);
        OreDictionary.registerOre(LibItems.ore_iron_rod,IRON_ROD);
        OreDictionary.registerOre(LibItems.ore_gold_rod,GOLD_ROD);
        OreDictionary.registerOre(LibItems.ore_diamond_shard_shard,DIAMOND_SHARD);
        OreDictionary.registerOre(LibItems.ore_diamond_shard_chunk,DIAMOND_SHARD);
        OreDictionary.registerOre(LibItems.ore_emerald_shard_shard,EMERALD_SHARD);
        OreDictionary.registerOre(LibItems.ore_emerald_shard_chunk,EMERALD_SHARD);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        registerModelLocation(WRENCH);
        registerModelLocation(SLIME_BUCKET,0,SLIME_BUCKET.getRegistryName());
        registerModelLocation(SLIME_BUCKET,1,new ResourceLocation(LibMod.MODID,"magma_cube_bucket"));
        registerModelLocation(BLOCK_PROBE);
        registerModelLocation(HEAT_PROBE);
        //
        registerModelLocation(ENDER_HEART);
        registerModelLocation(MOLTEN_CORE);
        registerModelLocation(ARCANE_DUST);
        registerModelLocation(DRAGON_SCALE);
        registerModelLocation(GEODE);
        //
        registerModelLocation(IRON_ROD);
        registerModelLocation(GOLD_ROD);
        registerModelLocation(DIAMOND_SHARD);
        registerModelLocation(EMERALD_SHARD);
    }

    @SideOnly(Side.CLIENT)
    public static void registerModelLocation(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    @SideOnly(Side.CLIENT)
    public static void registerModelLocation(Item item, int damage, ResourceLocation location) {
        ModelLoader.setCustomModelResourceLocation(item, damage, new ModelResourceLocation(location, "inventory"));
    }
}
