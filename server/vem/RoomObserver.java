/*
 * RoomObserver.java
 *
 * Created on June 14, 2002, 7:07 PM
 */

package vem_devel;

	
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import psl.chime4.server.cwm.world.view.*;

/**
 *
 * @author  Vlad
 */
public class RoomObserver
{
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame("Room Observer");

		RoomView view = new RoomView();
		view.setWidth(500);
		view.setLength(300);
		LocatableWorldObjectView obj = new LocatableWorldObjectView();
		obj.setWidth(30);
		obj.setLength(30);
		obj.setX(50);
		obj.setY(50);
		LocatableWorldObjectView list[] = {obj};

		DoorView door = new DoorView();
		door.setWidth(100);
		door.setLength(5);
		door.setX(100);
		door.setY(0);
		DoorView doors[] = {door};

		frame.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		RoomPanel drawPane = new RoomPanel();

		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(drawPane, BorderLayout.CENTER);


		frame.pack();
		frame.setSize(800, 700);
		frame.setVisible(true);

		// list should be set to an array containing the objects in the room

		drawPane.drawRoom(view, list, doors);

	}
	
	
}
