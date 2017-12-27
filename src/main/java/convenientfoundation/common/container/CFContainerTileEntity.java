package convenientfoundation.common.container;

import convenientfoundation.common.tileentity.CFTileEntity;
import net.minecraft.entity.player.EntityPlayer;

public abstract class CFContainerTileEntity extends CFContainer {
	public static final double MAX_DISTANCE=Math.pow(15,2);

	public CFTileEntity tile;

	public CFContainerTileEntity(CFTileEntity tile){
		super();
		this.tile=tile;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return tile.getPos().distanceSq(playerIn.getPosition()) <= MAX_DISTANCE;
	}
}
