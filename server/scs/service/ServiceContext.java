package psl.chime4.server.scs.service;

import java.util.*;
import java.io.*;

import org.jdom.*;
import org.jdom.input.*;

/**
 * A collection of services that define a server.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class ServiceContext
{
   Map serviceMap = new HashMap();
   
   /**
    * Place a Service in the context under a given name.
    *
    * @param name the name of the service
    * @return Service located under <code>name</code> or <code>null</code>
    * @throws IllegalArgumentException
    *         if <code>name</code> is <code>null</code>
    **/
   public Service getService(String name)
   {
      if (name == null)
      {
         String msg = "name cannot be null";
         throw new IllegalArgumentException(msg);
      }

      synchronized(serviceMap)
      {
         return (Service) serviceMap.get(name);
      }
   }
   
   /**
    * Put a Service in the context. It will be available to all other
    * services.
    *
    * @param name    name to place the Service under
    * @param service the service to place in the context
    * @throws IllegalArgumentException
    *         if any parameter is <code>null</code> or if a service is 
    *         already registered under that exception
    **/
   public void putService(String name, Service service)
   {
      if ((name == null) || (service == null))
      {
         String msg = "no param can be null";
         throw new IllegalArgumentException(msg);
      }
    
      if (serviceMap.containsKey(name))
      {
         String msg = "service already registered under name: " + name;
         throw new IllegalArgumentException(msg);
      }
      
      synchronized(serviceMap)
      {
         serviceMap.put(name, service);
      }
   }
   
   /**
    * Initialized the ServiceContext from a given server.xml file.
    *
    * @param in InputStream containing XML data to configure the server
    * @throws IllegalArguemntException
    *         if <code>in</code> is <code>null</code>
    * @throws ServiceException
    *         if the server.xml contains bad data
    **/
   public void initialize(InputStream in) throws ServiceException
   {
      try
      {
         SAXBuilder builder = new SAXBuilder();
         Document doc = builder.build(in);
         Element root = doc.getRootElement();
         in.close();
        
         // get all of the children named 'service', instantiate the classes
         // and then put them in the map
         List serviceList = root.getChildren("service");
         for (Iterator it = serviceList.iterator(); it.hasNext(); )
         {
            Element serviceNode = (Element) it.next();
            String serviceName = serviceNode.getAttributeValue("name");
            String serviceClass = serviceNode.getAttributeValue("class");
            Service service = 
               (Service) Class.forName(serviceClass).newInstance();
            
            // put it in the service map and set the context
            service.setServiceContext(this);
            service.setServiceName(serviceName);
            serviceMap.put(serviceName, service);
         }
         
         // now go through and do the parameters of each one
         ServiceParamMap serviceParamMap = new ServiceParamMap();
         
         for (Iterator it = serviceList.iterator(); it.hasNext(); )
         {
            Element serviceNode = (Element) it.next();
            String serviceName = serviceNode.getAttributeValue("name");
            Service currentService = getService(serviceName);
            
            // get all the parameters
            serviceParamMap.clear();
            List paramList = serviceNode.getChildren("param");          
            for (Iterator pit = paramList.iterator(); pit.hasNext();)
            {
               Element paramNode = (Element) pit.next();
               String paramName = paramNode.getAttributeValue("name");
               String paramType = paramNode.getAttributeValue("type");
               String paramVal = paramNode.getAttributeValue("value");
               
               // figure out the name, value, and put it in the map
               ServiceParam param = new ServiceParam();
               param.setName(paramName);
               
               if (paramType.equals("string"))
               {
                  param.setValue(paramVal);
               } else if (paramType.equals("integer"))
               {
                  param.setValue(Integer.valueOf(paramVal));
               } else if (paramType.equals("service"))
               {
                  // if it's another service then the value is name of that
                  // service
                  param.setValue(getService(paramVal));
               } else
               {
                  String msg = "unknown param value type";
                  throw new ServiceException(msg);
               }
                  
               serviceParamMap.addParam(paramName, param);
            }
            
            // now initialize the service
            currentService.initialize(serviceParamMap);
         }
      } 
      catch (Exception e)
      {
         String msg = "couldn't initialize service context";
         throw new ServiceException(msg, e);
      }
   }
}

