package convenientfoundation.entity;

import convenientfoundation.ConvenientFoundation;
import convenientfoundation.libs.LibEntities;
import convenientfoundation.libs.LibMod;
import convenientfoundation.entity.specialitem.EntitySpecialItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
    public static void init(){
        EntityRegistry.registerModEntity(new ResourceLocation(LibMod.MODID, LibEntities.specialItemEntityName), EntitySpecialItem.class, LibEntities.specialItemEntityName, LibEntities.specialItemEntityId, ConvenientFoundation.INSTANCE, 128, 5, true);
    }
}
