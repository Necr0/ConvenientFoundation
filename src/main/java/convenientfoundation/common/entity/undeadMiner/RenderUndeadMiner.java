package convenientfoundation.common.entity.undeadMiner;

import convenientfoundation.libs.LibMod;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Necro on 9/14/2017.
 */
@SideOnly(Side.CLIENT)
public class RenderUndeadMiner extends RenderZombie
{
    private static final ResourceLocation UNDEAD_MINER_ZOMBIE_TEXTURES = new ResourceLocation(LibMod.MODID,"textures/entity/undead_miner.png");

    public RenderUndeadMiner(RenderManager manager)
    {
        super(manager);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityZombie entity)
    {
        return UNDEAD_MINER_ZOMBIE_TEXTURES;
    }
}
