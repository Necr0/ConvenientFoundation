package convenientfoundation.entity.specialitem;

import convenientfoundation.entity.DataSerializers;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EntitySpecialItem extends EntityItem {
    public static final DataParameter<List<String>> BEHAVIOURS = EntityDataManager.<List<String>>createKey(EntitySpecialItem.class, DataSerializers.LISTSTRING);
    public List<String> behaviours = new ArrayList<>();

    public EntitySpecialItem(World world) {
        super(world);
    }

    public EntitySpecialItem(World world, double p_i1709_2_, double p_i1709_4_, double p_i1709_6_) {
        super(world, p_i1709_2_, p_i1709_4_, p_i1709_6_);
    }

    public EntitySpecialItem(World world, double p_i1710_2_, double p_i1710_4_, double p_i1710_6_, ItemStack p_i1710_8_) {
        super(world, p_i1710_2_, p_i1710_4_, p_i1710_6_, p_i1710_8_);
    }

    public List<String> getBehaviours() {
        return getEntityWorld().isRemote ? this.getDataManager().get(BEHAVIOURS) : this.behaviours;
    }

    public void setBehaviours(List<String> behaviours) {
        this.behaviours = behaviours;
        this.getDataManager().set(BEHAVIOURS, this.behaviours);
        this.getDataManager().setDirty(BEHAVIOURS);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        NBTTagCompound bnbt = new NBTTagCompound();
        bnbt.setInteger("BEHAVIOUR_COUNT", behaviours.size());
        int i = 0;
        for (String b : behaviours) {
            bnbt.setString("BEHAVIOUR" + i, b);
            i++;
        }
        par1NBTTagCompound.setTag("BEHAVIOURS", bnbt);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.behaviours = new ArrayList<>();
        if (par1NBTTagCompound.hasKey("BEHAVIOURS")) {
            NBTTagCompound bnbt = par1NBTTagCompound.getCompoundTag("BEHAVIOURS");
            if(bnbt.hasKey("BEHAVIOUR_COUNT")){
                for (int i = 0; i < bnbt.getInteger("BEHAVIOUR_COUNT"); i++) {
                    addBehaviourSilent(bnbt.getString("BEHAVIOUR" + i));
                }
            }
            syncBehaviours();
        }
    }

    @Override
    public void onUpdate() {
        for (String b : getBehaviours()) {
            IEntitySpecialItemBehaviour behaviour=BehaviourRegistry.getBehaviour(b);
            if(behaviour==null)
                continue;
            behaviour.onItemEntityUpdate(this);
            if(this.getItem().isEmpty()){
                this.setDead();
                break;
            }
        }
        super.onUpdate();
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        boolean flag = false;
        for (String b : getBehaviours()) {
            IEntitySpecialItemBehaviour behaviour=BehaviourRegistry.getBehaviour(b);
            if(behaviour==null)
                continue;
            if (behaviour.onAttackItemEntityFrom(this, source, damage))
                flag = true;
        }
        return !flag && super.attackEntityFrom(source, damage);
    }

    //DATA STUFF
    @Override
    protected void entityInit() {
        behaviours = (behaviours != null ? behaviours : new ArrayList<>());
        this.getDataManager().register(BEHAVIOURS, behaviours);
        for (String b : getBehaviours()) {
            IEntitySpecialItemBehaviour behaviour=BehaviourRegistry.getBehaviour(b);
            if(behaviour==null)
                continue;
            behaviour.onCreate(this);
        }
        super.entityInit();
    }

    public void addBehaviour(String... behaviours) {
        for (String b : behaviours) {
            this.behaviours.add(b);
        }
        this.getDataManager().set(BEHAVIOURS, this.behaviours);
        this.getDataManager().setDirty(BEHAVIOURS);
    }

    public void addBehaviours(List<String> behaviours) {
        this.behaviours.addAll(behaviours);
        this.getDataManager().set(BEHAVIOURS, this.behaviours);
        this.getDataManager().setDirty(BEHAVIOURS);
    }

    public void addBehaviourSilent(String... behaviours) {
        for (String b : behaviours) {
            this.behaviours.add(b);
        }
    }

    public void addBehavioursSilent(List<String> behaviours) {
        this.behaviours.addAll(behaviours);
    }

    public void syncBehaviours() {
        this.getDataManager().set(BEHAVIOURS, this.behaviours);
        this.getDataManager().setDirty(BEHAVIOURS);
    }
}
