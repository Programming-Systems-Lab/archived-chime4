package psl.chime4.server.scs.ws.view.render;

import psl.chime4.server.scs.persist.*;

/**
 * Defines a geometry; that is a shape that exists in space independent of 
 * location.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class Geometry extends PersistentObject
{
   /** code defining this geometry **/
   protected int geomCode;
   
   /** uri defining this geometry **/
   protected String geomURI;
   
   /**
    * Get the numeric code for this geometry.
    *
    * @return code for this geometry
    **/
   public int getGemetryCode()
   {
      return geomCode;
   }
   
   /**
    * Get the uri for this geometry.
    *
    * @return uri for this geometry
    **/
   public String getGeometryURI()
   {
      return geomURI;
   }
   
   /**
    * Calculate the bounding box for this geometry and stores it in the 
    * passed CubeGeomtetry.
    *
    * @param cubeGeometry geometry to store the bounding box info
    * @throws IllegalArgumentException
    *         if <code>cubeGeometry</code> is <code>null</code>
    **/
   public void calculateBoundingBox(CubeGeometry cubeGeometry)
   {
      // @todo implement this method
   }
}