package psl.chime4.server.scs.service;

/**
 * A default, simple implementation for the Service interface.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class AbstractService implements Service
{
   private ServiceContext context;
   private String serviceName;
   
   public ServiceContext getServiceContext()
   {
      return context;
   }
   
   public void setServiceContext(ServiceContext context)
   {
      this.context = context;
   }
   
   /**
    * Initialize the Service. At this point the Service has not started.
    *
    * @param params parameters passed from server.xml
    * @throws ServiceException
    *        if the service cannot initialize itself
    */
   public void initialize(ServiceParamMap params) throws ServiceException
   {
   }
   
   /**
    * Start the Service. At this point the service is running.
    *
    * @throws ServiceException 
    *         if the service cannot start for some reason
    **/
   public void start() throws ServiceException
   {
   }
   
   /**
    * Shut the Service down. The Service has roughly two minutes to shutdown
    * before it will be forcefully shutdown.
    **/
   public void stop()
   {
   }
   
   /**
    * Get the name of the server. This is unique in the service context.
    *
    * @return name of the service
    */
   public String getServiceName()
   {
      return serviceName;
   }
   
   /**
    * Set the name of the server. This should be unique in the entire service
    * context.
    *
    * @param name name of the service
    */
   public void setServiceName(String name)
   {
      this.serviceName = name;
   }
   
   public String toString()
   {
      return getClass().toString() + "[name=" + serviceName + "]";
   }
}