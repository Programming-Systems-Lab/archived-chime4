#ifndef __ENVIRONMENT_MODELER_DOOR__
#define __ENVIRONMENT_MODELER_DOOR__

//door definition
class EMDoor: public EMElement
{
	public:
		EMDoor( ChimeID setID, ChimeID roomOne, ChimeID roomTwo,
			Coords setDim, Coords coordsOne, Coords coordsTwo, ChimeID setModel );

		void setLinkRooms( EMRoom *roomOne, EMRoom *roomTwo );
		void setLinkCoords( Coords coordsOne, Coords coordsTwo );

		EMRoom **getLinkRooms( void );
		Coords *getLinkCoords( void );

	protected:
		EMRoom *linkRooms[2];
		Coords linkCoords[2];
};

#endif
