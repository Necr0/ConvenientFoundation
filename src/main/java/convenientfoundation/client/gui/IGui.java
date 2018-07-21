package convenientfoundation.client.gui;

import convenientfoundation.client.gui.widget.IWidget;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public interface IGui {
    void messageGui(@Nullable IWidget sender,Object message);
}
