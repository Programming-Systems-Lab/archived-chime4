#ifndef __ENVIRONMENT_MODELER_AVATAR__
#define __ENVIRONMENT_MODELER_AVATAR__

//avatar definition
class EMAvatar: public EMElement, public EMRoomList
{
	public:
		EMAvatar( ChimeID setID, ChimeID setRoom, Coords setDim,
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