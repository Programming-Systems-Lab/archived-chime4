package psl.chime4.server.di;

public interface DIEventReceiver
{
    public void receiveEvent( DIEvent event );
    public void receiveResult( DIHost result );
    public void receiveMessage( DIMessage msg );
}
