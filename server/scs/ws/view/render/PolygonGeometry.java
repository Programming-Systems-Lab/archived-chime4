package psl.chime4.server.scs.ws.view.render;

/**
 * Defines a set of vertices that can define any random shape that has been
 * normalized.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class PolygonGeometry extends Geometry
{  
   /** number of vertices in the geometry **/
   private int numVertices;
   
   /** array of x-verts **/
   protected float[] xCoords;
   
   /** array of y-verts **/
   protected float[] yCoords;
   
   /** array of z-verts **/
   protected float[] zCoords;
   
   public PolygonGeometry()
   {
      super.geomCode = RenderConstants.POLYGON_GEOMETRY;
      super.geomURI = RenderConstants.POLYGON_GEOMETRY_URI;
   }
   
   /**
    * Get the number of vertices in the geometry.
    *
    * @return number of vertices in the geometry
    **/
   public int getNumVertices()
   {
      return numVertices;
   }
   
   /**
    * Set the number of vertices in the geometry.
    *
    * @param numVertices number of vertices in the geometry
    **/
   public void setNumVertices(int numVertices)
   {
      this.numVertices = numVertices;
   }
   
   /**
    * Set the n-th vertex of the geometry.
    *
    * @param n nth vertex of the geometry
    * @param x x-coord of the vertex
    * @param y y-coord of the vertex
    * @param z z-coord of the vertex
    **/
   public void setNthVertex(int n, float x, float y, float z)
   {
      xCoords[n] = x;
      yCoords[n] = y;
      zCoords[n] = z;
   }
   
   /**
    * Get the x coordinate of the n-th of the geometry.
    *
    * @param n number of the vertex to retrieve
    * @return x coordinate of the nth vertex
    **/
   public float getNthX(int n)
   {
      return xCoords[n];
   }
   
   /**
    * Get the y coordinate of the n-th vertex of the geometry.
    *
    * @param n number of the vertex to retrieve
    * @return y coordinate of the nth vertex
    **/
   public float getNthY(int n)
   {
      return yCoords[n];
   }
   
   /**
    * Get the z coordinate of the n-th vertex of the geometry.
    *
    * @param n number of the vertex to retrieve
    * @return z coordinate of the nth vertex
    **/
   public float getNthZ(int n)
   {
      return zCoords[n];
   }
}
  

