package convenientfoundation.common.capabilities.entity;

import convenientfoundation.common.entity.stack.EntityStack;

public interface IEntityStorageModifiable extends IEntityStorage
{
    void setEntity(EntityStack stack);
    void setAmount(int amount);
    void setCapacity(int capacity);
}
