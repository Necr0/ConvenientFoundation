package convenientfoundation.client;

import convenientfoundation.ConvenientFoundation;
import convenientfoundation.libs.LibMappings;
import net.minecraft.client.renderer.texture.ITextureMapPopulator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResourceManager;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class FixedTextureMap extends TextureMap {
    public static final Method INIT_MISSING_IMAGE = ReflectionHelper.findMethod(TextureMap.class,LibMappings.Methods.TextureMap_initMissingImage[0],LibMappings.Methods.TextureMap_initMissingImage[1]);
    public static final Field MAP_REGISTERED_SPRITES = ReflectionHelper.findField(TextureMap.class,LibMappings.Fields.TextureMap_mapRegisteredSprites);

    public FixedTextureMap(String basePathIn) {
        super(basePathIn);
    }

    public FixedTextureMap(String basePathIn, @Nullable ITextureMapPopulator iconCreatorIn) {
        super(basePathIn, iconCreatorIn);
    }

    public FixedTextureMap(String basePathIn, boolean skipFirst) {
        super(basePathIn, skipFirst);
    }

    public FixedTextureMap(String basePathIn, @Nullable ITextureMapPopulator iconCreatorIn, boolean skipFirst) {
        super(basePathIn, iconCreatorIn, skipFirst);
    }

    @Override
    public void loadSprites(IResourceManager resourceManager, ITextureMapPopulator iconCreatorIn)
    {
        try {
            ((Map<String, TextureAtlasSprite>) MAP_REGISTERED_SPRITES.get(this)).clear();
            iconCreatorIn.registerSprites(this);
            INIT_MISSING_IMAGE.invoke(this);
            this.deleteGlTexture();
            this.loadTextureAtlas(resourceManager);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            ConvenientFoundation.LOG.error(e);
        }
    }
}
