#ifndef __ENVIRONMENT_MODELER_DOOR__
#define __ENVIRONMENT_MODELER_DOOR__

//door definition
class EMDoor: public EMElement
{
	public:
		EMDoor( ChimeID setID, ChimeID roomOne, ChimeID roomTwo,
			Coords setDim, Coords coordsOne, Coords coordsTwo, ChimeID setModel );
		virtual ~EMDoor( void );

		EMRoom *linkOne;
		EMRoom *linkTwo;

		Coords linkOneCoords;
		Coords linkTwoCoords;
};

struct EMDoorContainer
{
	struct EMDoorContainer *next;
	class EMDoor *door;
};
typedef struct EMDoorContainer EMDoorContainer;

#endif
