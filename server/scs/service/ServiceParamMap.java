package psl.chime4.server.scs.service;

import java.util.*;

/**
 * A collection of parameters which might be passed to a service in order to
 * initialize it.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public final class ServiceParamMap
{
   private Map params = new HashMap();
   
   /**
    * Add a parameter to the map.
    *
    * @param name  the name of the parameter
    * @param param the parameter to add to the map
    * @throws IllegalArgumentException
    *         if any parameter is <code>null</code>
    **/
   public void addParam(String name, ServiceParam param)
   {
      if ((name == null) || (param == null))
      {
         String msg = "no parameter can be null";
         throw new IllegalArgumentException(msg);
      }
      
      params.put(name, param);
   }
   
   /**
    * Get a parameter from the map.
    *
    * @param name the name of the parameter
    * @return ServiceParam mapped to <code>name</code> or <code>null</code>
    * @throws NoSuchElementException
    *         if no parameter exists with <code>name</code>
    * @throws IllegalArgumentException
    *         if <code>name</code> is <code>null</code>
    **/
   public ServiceParam getParam(String name)
   {
      if (!params.containsKey(name))
      {
         String msg = "no such parameter: " + name;
         throw new NoSuchElementException(msg);
      }
      
      return (ServiceParam) params.get(name);
   }
   
   /**
    * Erase all parameters in the map. The map can be reused after this call
    * is made.
    **/
   public void clear()
   {
      params.clear();
   }
   
   public String toString()
   {
      return "ServiceParamMap[params=" + params + "]";
   }
}