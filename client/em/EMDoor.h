#ifndef __ENVIRONMENT_MODELER_DOOR__
#define __ENVIRONMENT_MODELER_DOOR__

//door definition
class EMDoor: public EMElement
{
	public:
		EMDoor( ChimeID setID, ChimeID roomOne, ChimeID roomTwo,
			Coords setDim, Coords coordsOne, Coords coordsTwo,
			Coords rotOne, Coords rotTwo, ChimeID setModel );

		void setLinkRooms( EMRoom *roomOne, EMRoom *roomTwo );
		void setLinkCoords( Coords coordsOne, Coords coordsTwo );
		void setLinkRotation( Coords rotOne, Coords rotTwo );

		EMRoom **getLinkRooms( void );
		Coords *getLinkCoords( void );
		Coords *getLinkRotation( void );

	protected:
		EMRoom *linkRooms[2];
		Coords linkCoords[2];
		Coords linkRotation[2];
};

#endif
