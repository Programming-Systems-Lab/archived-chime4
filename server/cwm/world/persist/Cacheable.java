package psl.chime4.server.cwm.world.persist;

/**
 * A marker interface that defines whether an object can be cached. If speed
 * is absolutely critical and so an object must always remain in memory
 * it may choose not to implement this interface otherwise all other objects
 * should implement this interface.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public interface Cacheable
{
}
   

