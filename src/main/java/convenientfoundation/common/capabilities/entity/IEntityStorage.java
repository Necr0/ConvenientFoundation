package convenientfoundation.common.capabilities.entity;

import convenientfoundation.common.entity.stack.EntityStack;

import javax.annotation.Nullable;

public interface IEntityStorage extends IEntityHandler
{
    @Nullable
    EntityStack getEntity();
    int getAmount();
    int getCapacity();
}
