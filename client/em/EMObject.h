#ifndef __ENVIRONMENT_MODELER_OBJECT__
#define __ENVIRONMENT_MODELER_OBJECT__

//object definition
class EMObject: public EMElement, public EMRoomList
{
	public:
		EMObject( ChimeID setID, ChimeID setRoom, Coords setDim,
			Coords setCoords, Coords setRotate, ChimeID setModel );

		void setRoomCoords( Coords theCoords );
		void setRotation( Coords theCoords );
		void setRoom( EMRoom *theRoom );

		Coords getRoomCoords( void );
		Coords getRotation( void );
		EMRoom *getRoom( void );

	protected:
		EMRoom *room;
		Coords roomCoords;
		Coords rotation;
};

#endif