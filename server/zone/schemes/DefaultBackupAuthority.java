
package psl.chime4.server.zone.schemes;

import psl.chime4.server.auth.NetworkNode;
import psl.chime4.server.di.*;
import psl.chime4.server.zone.*;
import psl.chime4.server.zone.authority.BackupAuthority;





public class DefaultBackupAuthority implements BackupAuthority {

    
    private ZoneManager zoneManager;
    private DataManager dataManager;
    private DirectoryInterface di;



    public DefaultBackupAuthority(ZoneManager zs, DataManager dm, 
				  DirectoryInterface di) {

	this.zoneManager = zs;
	this.dataManager = dm;
	this.di = di;
    }





    public void handlePrimaryBackupRequest(Zone[] list, NetworkNode source) {
	zoneManager.acceptZoneBackupRequest(list, source, true);
    }


    public void handleSecondaryBackupRequest(Zone[] list, NetworkNode source) {
	zoneManager.acceptZoneBackupRequest(list, source, false);
    }


    public void handlePrimaryBackupOffer(Zone[] list, NetworkNode source) {
    }


    public void handleSecondaryBackupOffer(Zone[] list, NetworkNode source) {
    }


    public void handleBackupRejection(Zone[] list, NetworkNode source) {
    }


    public void handleBackupAcceptance(Zone[] list, NetworkNode source) {
	zoneManager.completeZoneBackup(list, source, true);
	// note, this authority should remember in the completion if we're
	// dealing with a primary or secondary and deal with it accordingly
    }




}   // end DefaultBackupAuthority class




