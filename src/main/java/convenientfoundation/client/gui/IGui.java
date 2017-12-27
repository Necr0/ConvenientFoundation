package convenientfoundation.client.gui;

import convenientfoundation.client.gui.widget.IWidget;

import javax.annotation.Nullable;

public interface IGui {
    void messageGui(@Nullable IWidget sender,Object message);
}
