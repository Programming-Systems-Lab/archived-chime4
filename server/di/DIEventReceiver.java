package psl.chime4.server.di;

interface DIEventReceiver
{
    public void receiveEvent( DIEvent event );
    public void receiveResult( DIHost result );
    public void receiveMessage( DIMessage msg );
}
