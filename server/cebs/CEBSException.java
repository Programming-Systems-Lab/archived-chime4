package psl.chime4.server.cebs;

/**
 * Base class for all exceptions in the CEBS package.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class CEBSException extends Exception
{
    public CEBSException()
    {
        super();
    }
    
    public CEBSException(String msg)
    {
        super(msg);
    }
    
    public CEBSException(String msg, Throwable cause)
    {
        super(msg, cause);
    }
    
    public CEBSException(Throwable cause)
    {
        super(cause);
    }
}