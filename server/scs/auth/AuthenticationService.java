package psl.chime4.server.scs.auth;

import psl.chime4.server.scs.service.*;

/**
 * The AuthenticationService is responsible for authenticating users to see
 * if they can enter or use another specific service.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public interface AuthenticationService extends Service
{
   /**
    * Used when a client requests a new Authentication ticket.
    *
    * @param username name of the user
    * @param ticket   an existing ticket that acts as proof
    * @return <code>null</code> if the ticket is denied else 
    *          an array of bytes containing the new ticket for the user
    * @throws ServiceException
    *         if the service fails
    **/
   public byte[] createTicket(String username, byte[] ticket) 
      throws ServiceException;
   
   /**
    * Authenticate a user with the given name and proof of identity.
    *
    * @param username name of the user
    * @param ticket   proof that the user is who she says she is
    * @return <code>null</code> if the user can logon else a String object
    *         explaining why the user can logon
    * @throws ServiceException
    *         if the service fails
    **/
   public String authenticate(String username, byte[] ticket)
      throws ServiceException;
}