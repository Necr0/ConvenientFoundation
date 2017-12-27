package convenientfoundation.common.capabilities.entity;

import convenientfoundation.common.entity.stack.EntityStack;

import javax.annotation.Nullable;

public interface IEntityHandler
{
    @Nullable
    EntityStack insert(EntityStack energy, boolean simulate);
    @Nullable
    EntityStack extract(EntityStack energy, boolean simulate);
    @Nullable
    EntityStack extract(int amount, boolean simulate);
}
