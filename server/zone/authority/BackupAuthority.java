
package psl.chime4.server.zone.authority;

import psl.chime4.server.auth.NetworkNode;
import psl.chime4.server.zone.Zone;


public interface BackupAuthority {

    public void handlePrimaryBackupRequest(Zone[] list, NetworkNode source);
    public void handleSecondaryBackupRequest(Zone[] list, NetworkNode source);
    public void handlePrimaryBackupOffer(Zone[] list, NetworkNode source);
    public void handleSecondaryBackupOffer(Zone[] list, NetworkNode source);
    public void handleBackupRejection(Zone[] list, NetworkNode source);
    public void handleBackupAcceptance(Zone[] list, NetworkNode source);

} // end BackupAuthority interface
