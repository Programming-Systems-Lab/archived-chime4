package psl.chime4.server.cwm;

import psl.chime4.server.data.Persistent;

/**
 * An abstract implementation of the Persistent interface.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class AbstractPersistentObject implements Persistent
{
   /** the persistence id **/
   private int persistenceID;
   
   public int getPersistenceID()
   {
      return persistenceID;
   }
   
   public void setPersistenceID(int persistenceID)
   {
      this.persistenceID = persistenceID;
   }
}
