package convenientfoundation.entity;

import convenientfoundation.ConvenientFoundation;
import convenientfoundation.ModConstants;
import convenientfoundation.entity.specialitem.EntitySpecialItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
    public static void init(){
        EntityRegistry.registerModEntity(new ResourceLocation(ModConstants.Mod.MODID,ModConstants.Entities.specialItemEntityName), EntitySpecialItem.class, ModConstants.Entities.specialItemEntityName, ModConstants.Entities.specialItemEntityId, ConvenientFoundation.INSTANCE, 128, 5, true);
    }
}
