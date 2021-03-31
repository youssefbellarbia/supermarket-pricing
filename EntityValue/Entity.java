package EntityValue;

import java.util.UUID;

public abstract class Entity
{
	protected Entity(UUID id2)
	{
		Id = id2;
	}

	private UUID Id;
	public final UUID getId()
	{
		return Id;
	}
	
	public final boolean equals(Entity other)
	{
		return other != null && getId().equals(other.getId());
	}

	@Override
	public boolean equals(Object obj)
	{
		boolean tempVar = obj instanceof Entity;
		Entity entity = tempVar ? (Entity)obj : null;
		if (tempVar)
		{
			return equals(entity);
		}

		return super.equals(obj);
	}
}