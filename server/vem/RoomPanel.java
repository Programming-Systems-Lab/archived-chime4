package vem_devel;

import javax.swing.*;
import java.awt.*;
import psl.chime4.server.cwm.world.view.*;

/**
 *	Performs the actual drawing of the fractal
 */
public class RoomPanel extends JPanel
{
	private Image i;
	
	public RoomPanel()
	{
		setBackground(java.awt.Color.black);
	}
	
	
	/**
	 *	Creates a new offscreen buffer if it doesn't exist. 
	 *	Draws the buffer to the panel
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);	
	
		if (i == null) {
			getBuffer();
		}
		
		g.drawImage(i, 0, 0, this);
	}

	public void drawRoom(RoomView view, LocatableWorldObjectView[] objs, DoorView[] doors)
	{
		int offx, offy;

		offx = 20;
		offy = 20;
		
		LocatableWorldObjectView obj;
		DoorView door;

		Graphics buf = getBuffer();
		buf.setColor(Color.black);
		buf.fillRect(0, 0, getWidth(), getHeight());	

		buf.setColor(Color.yellow);
		buf.fillRect(offx, offy, view.getWidth(), view.getLength());

		for (int i=0; i<objs.length; i++)
		{
			obj = objs[i];

			buf.setColor(Color.gray);
			buf.fillRect(offx+obj.getX(), offy+obj.getY(), obj.getWidth(), obj.getLength());
		}

		for (int i=0; i<doors.length; i++)
		{
			door = doors[i];

			buf.setColor(Color.gray);
			buf.fillRect(offx+door.getX(), offy+door.getY(), door.getWidth(), door.getLength());
		}

		repaint();
	}

	private Graphics getBuffer() {
		if (i == null) {
			i = createImage(getWidth(), getHeight());
			Graphics b = i.getGraphics();	
			b.setColor(Color.black);
			b.fillRect(0, 0, getWidth(), getHeight());			
		}

		return i.getGraphics();
	}

}