package psl.chime4.server.cwm.world;

/**
* A WorldObject represents an actual object that exists in the world. This 
* might be a Room, Door, User or a piece of metadata.
*
* @author Azubuko Obele
* @version 0.1
**/
public abstract class WorldObject extends AbstractPersistentObject
{
    /** int describing the type **/
    private int typeID;

    /** int describing the state **/
    private int state;

    /**
	* Get a unique integer describing this instance.
	*
	* @return instance-ID for this object
	**/
    public int getInstanceID()
    {
		return super.getPersistenceID();
    }

    /**
	* Set the integer-id of this instance.
	*
	* This method should never be called by anybody except the Data Server.
	*
	* @param instanceID the instance-ID for this object
	**/
    public void setInstanceID(int instanceID)
    {
		super.setPersistenceID(instanceID);
    }

    /**
	* Get the type-ID for this object.
	*
	* @return type-ID for this object.
	**/
    public int getTypeID()
    {
		return typeID;
    }

    /**
	* Set the type-ID for this object.
	*
	* @param typeID the type-ID for this object
	**/
    public void setTypeID(int typeID)
    {
		this.typeID = typeID;
    }

    /**
	* Get the state-code of this object.
	*
	* @return state-code of the object
	**/
    public int getState()
    {
		return state;
    }

    /**
	* Set the state-code of this object.
	*
	* @param stateCode the state-code of this object
	**/
    public void setState(int stateCode)
    {
		this.state = stateCode;
    }
}
