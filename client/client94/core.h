#ifndef __CLIENT_CORE_H__
#define __CLIENT_CORE_H__

//keeps memory cleanup working
#define WIN32_VOLATILE

//standard libs
#include <stdarg.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//crystal space
#define CS_SYSDEF_PROVIDE_PATH
#define CS_SYSDEF_PROVIDE_ACCESS
#include "cssysdef.h"
#include "cssys/sysfunc.h"
#include "csws/csws.h"
#include "csgeom/math2d.h"
#include "csgeom/math3d.h"
#include "csutil/csstrvec.h"
#include "csutil/cscolor.h"
#include "csutil/cmdhelp.h"
#include "csgeom/path.h"
#include "cstool/csfxscr.h"
#include "cstool/csview.h"
#include "cstool/initapp.h"
#include "ivideo/graph3d.h"
#include "ivideo/natwin.h"
#include "ivideo/fontserv.h"
#include "ivideo/graph2d.h"
#include "ivideo/txtmgr.h"
#include "ivaria/conout.h"
#include "iengine/engine.h"
#include "iengine/sector.h"
#include "iengine/light.h"
#include "iengine/statlght.h"
#include "iengine/camera.h"
#include "iengine/mesh.h"
#include "iengine/movable.h"
#include "iengine/halo.h"
#include "iengine/material.h"
#include "imesh/thing/thing.h"
#include "imesh/thing/polygon.h"
#include "imesh/thing/ptextype.h"
#include "imesh/particle.h"
#include "imesh/sprite2d.h"
#include "imesh/sprite3d.h"
#include "imesh/ball.h"
#include "imesh/stars.h"
#include "imesh/object.h"
#include "imap/reader.h"
#include "imap/parser.h"
#include "iutil/strvec.h"
#include "iutil/comp.h"
#include "iutil/eventh.h"
#include "iutil/event.h"
#include "iutil/eventq.h"
#include "iutil/objreg.h"
#include "iutil/virtclk.h"
#include "iutil/csinput.h"
#include "iutil/cmdline.h"
#include "iutil/plugin.h"
#include "iutil/vfs.h"
#include "csutil/csstrvec.h"
#include "igraphic/imageio.h"
#include "ivaria/reporter.h"
#include "ivaria/stdrep.h"
#include "iaws/aws.h"
#include "iaws/awscnvs.h"
#include "csws/csapp.h"
#include "cstool/mdldata.h"
#include "cstool/mdltool.h"
#include "imesh/mdlconv.h"
#include "imesh/crossbld.h"

//main dependencies
#include "ClientApp.h"
#include "ClientEngine.h"

//constants
#define CLIENT_DEFAULT_AWS_DEFS			"/this/clientaws.def"
#define CLIENT_DEFAULT_CONFIG_PATH		"/config/client.cfg"
#define CLIENT_NATIVE_WINDOW_TITLE		"Client"
#define CLIENT_MENU_HEIGHT				20

//global data
extern ClientApp *gApp;

#endif
