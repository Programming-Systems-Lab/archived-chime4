package psl.chime4.server.scs.service;

/**
 * A parameter which can be passed to configure a Service.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public final class ServiceParam
{
   private String name;
   private Object value;
   
   /**
    * Get the name of the parameter.
    *
    * @return name of the parameter
    **/
   public String getName()
   {
      return name;
   }
   
   /**
    * Set the name of the parameter.
    *
    * @param name name of the parameter
    **/
   public void setName(String name)
   {
      this.name = name;
   }
   
   /**
    * Get the value of the parameter.
    *
    * @return value of the parameter
    **/
   public Object getValue()
   {
      return value;
   }
   
   /**
    * Set the value of the parameter.
    *
    * @param value value of the parameter
    **/
   public void setValue(Object value)
   {
      this.value = value;
   }
   
   /**
    * Get the type of the value.
    *
    * @return type of the value or <code>null</code> if there is no value
    **/
   public Class getType()
   {
      if (value != null)
      {
         return value.getClass();
      } else
      {
         return null;
      }
   }
   
   /**
    * Get the value of the parameter as a string.
    *
    * @return value of the parameter as a string
    **/
   public String getValueAsString()
   {
      return (String) value;
   }
   
   /**
    * Get the value of the parameter as an integer.
    *
    * @return value of the parameter as an integer
    **/
   public int getValueAsInt()
   {
      return ((Integer) value).intValue();
   }
   
   public String toString()
   {
      return "ServiceParam[name=" + name + ", value=" + value + ", type=" +
         getType() + "]";
   }
}