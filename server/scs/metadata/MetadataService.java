package psl.chime4.server.scs.metadata;

import psl.chime4.server.scs.service.*;

/**
 * A MetadataService is capable of retrieving metadata about resources.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public interface MetadataService extends Service
{
   /**
    * Evaluate a Metadata by retrieving as much metadata as possible about the
    * url defined by the Metadata.
    *
    * @param mdata Metadata to evaluate
    * @throws ServiceException
    *         if something goes wrong
    **/
   public void evaluate(Metadata mdata) throws ServiceException;
}