package psl.chime4.server.scs.ws;

import org.jdom.*;

/**
 * A class who implements this interface indicates that it can write out its
 * total state to XML.
 *
 * Currently XML serialization happens using JDOM. This is very slow and ugly
 * and it leads to the creation and destruction of hundreds of unnecessary
 * object. In the future a mechanism will be created for creating XML in a 
 * SAX-like manner like the XmlWriter class in .NET.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public interface XmlSerializable
{
   /**
    * Write out the current state of the object to XML. The serilizable 
    * instance is passed a node to which it should construct a new node and
    * add to it. The serilizable instance may not alter the passed node in
    * any other manner.
    *
    * @param node node to add a child to representing the state
    * @throws IllegalArgumentException
    *         if <code>node</code> is <code>null</code>
    **/
   public void writeObject(Element node);
}