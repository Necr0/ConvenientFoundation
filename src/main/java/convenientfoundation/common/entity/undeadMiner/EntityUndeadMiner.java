package convenientfoundation.common.entity.undeadMiner;

import convenientfoundation.common.loot.ModLoot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderServer;

import javax.annotation.Nullable;

/**
 * Created by Necro on 9/14/2017.
 */
public class EntityUndeadMiner extends EntityZombie
{
    public EntityUndeadMiner(World worldIn)
    {
        super(worldIn);
    }

    public static void registerFixesHusk(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityUndeadMiner.class);
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        if(super.getCanSpawnHere() && !this.world.canSeeSky(new BlockPos(this))){
           IChunkProvider provider=this.world.getChunkProvider();
           if(provider instanceof ChunkProviderServer)
               return ((ChunkProviderServer) provider).chunkGenerator.isInsideStructure(this.world,"Mineshaft",this.getPosition());
        }
        return false;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return ModLoot.undead_miner;
    }

    /*public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = super.attackEntityAsMob(entityIn);

        if (flag && this.getHeldItemMainhand().isEmpty() && entityIn instanceof EntityLivingBase)
        {
            float f = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();
            ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.HUNGER, 140 * (int)f));
        }

        return flag;
    }*/

    protected ItemStack getSkullDrop()
    {
        return ItemStack.EMPTY;
    }

    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        super.setEquipmentBasedOnDifficulty(difficulty);

        ItemStack pickaxe;
        int pickaxe_level=this.rand.nextInt(18);
        if(pickaxe_level<7)
            pickaxe=new ItemStack(Items.WOODEN_PICKAXE);
        else if(pickaxe_level<12)
            pickaxe=new ItemStack(Items.STONE_PICKAXE);
        else if(pickaxe_level<16)
            pickaxe=new ItemStack(Items.IRON_PICKAXE);
        else if(pickaxe_level<18)
            pickaxe=new ItemStack(Items.DIAMOND_PICKAXE);
        else
            pickaxe=new ItemStack(Items.GOLDEN_PICKAXE);

        int enchantment_level=10+this.rand.nextInt(28);
        pickaxe=EnchantmentHelper.addRandomEnchantment(this.rand,pickaxe,enchantment_level,false);
        if(this.rand.nextInt(42)==0)
            pickaxe.addEnchantment(Enchantment.getEnchantmentByLocation("minecraft:mending"),1);
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND,pickaxe);
        this.inventoryHandsDropChances[EntityEquipmentSlot.MAINHAND.getSlotIndex()] = .25f;
    }
}