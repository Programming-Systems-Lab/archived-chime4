package psl.chime4.server.scs.service;

/**
 * Represents a Service offered by the Chime Server.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public interface Service 
{
   /**
    * Initialize the Service. At this point the Service has not started.
    *
    * @param params parameters passed from server.xml
    * @throws ServiceException
    *         if the service cannot initialize itself
    **/
   public void initialize(ServiceParamMap params) throws ServiceException;
   
   /**
    * Start the Service. At this point the service is running.
    *
    * @throws ServiceException 
    *         if the service cannot start for some reason
    **/
   public void start() throws ServiceException;
   
   /**
    * Shut the Service down. The Service has roughly two minutes to shutdown
    * before it will be forcefully shutdown.
    **/
   public void stop();
   
   /**
    * Get the ServiceContext that is managing this service.
    *
    * @return ServiceContext for this service
    **/
   public ServiceContext getServiceContext();
   
   /**
    * Set the ServiceContext that is managing this service.
    *
    * @param context ServiceContext for this service
    **/
   public void setServiceContext(ServiceContext context);
   
   /**
    * Get the name of the server. This is unique in the service context.
    *
    * @return name of the service
    **/
   public String getServiceName();
   
   /**
    * Set the name of the server. This should be unique in the entire service
    * context.
    *
    * @param name name of the service
    **/
   public void setServiceName(String name);
}