package psl.chime4.server.scs.jc;

import psl.chime4.server.scs.auth.AuthenticationService;
import psl.chime4.server.scs.service.*;

/**
 * A stupid implementation of the AuthenticationService that simply 
 * authenticates all users.
 *
 **/
public class NonAuthenticationService extends AbstractService 
   implements AuthenticationService
{
   
   /**
    * Authenticate a user with the given name and proof of identity.
    *
    * @param username name of the user
    * @param ticket   proof that the user is who she says she is
    * @return <code>null</code> if the user can logon else a String object
    *        explaining why the user can logon
    * @throws ServiceException
    *        if the service fails
    */
   public String authenticate(String username, byte[] ticket) 
      throws ServiceException
   {
      return null;
   } 
   
   /**
    * Used when a client requests a new Authentication ticket.
    *
    * @param username name of the user
    * @param ticket   an existing ticket that acts as proof
    * @return <code>null</code> if the ticket is denied else
    *         an array of bytes containing the new ticket for the user
    * @throws ServiceException
    *        if the service fails
    */
   public byte[] createTicket(String username, byte[] ticket) throws ServiceException
   {
      return null;
   }
}