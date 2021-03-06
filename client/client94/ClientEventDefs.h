#ifndef __CLIENT_EVENTS_DEFS_H__
#define __CLIENT_EVENTS_DEFS_H__

//window event masks
#define CLIENT_MENU_EVENT				0x01000000
#define CLIENT_CONSOLE_WINDOW_EVENT		0x02000000
#define CLIENT_DISPLAY_WINDOW_EVENT		0x03000000

//client menu
#define CLIENT_MENU_CONNECT				(CLIENT_MENU_EVENT + 1)
#define CLIENT_MENU_DISCONNECT			(CLIENT_MENU_EVENT + 2)
#define CLIENT_MENU_VIDEO_CONFIG		(CLIENT_MENU_EVENT + 3)
#define CLIENT_MENU_EXIT				(CLIENT_MENU_EVENT + 4)

//windows menu
#define CLIENT_MENU_DISPLAY				(CLIENT_MENU_EVENT + 5)
#define CLIENT_MENU_CONSOLE				(CLIENT_MENU_EVENT + 6)

#endif