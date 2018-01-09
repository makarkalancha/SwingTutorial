package p001_100;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class p006_CenterOnScreen extends JFrame{
	
	public p006_CenterOnScreen(){
		setSize(300,200);
		setTitle("Center on screen");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2 );
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		p006_CenterOnScreen centerOnScreen = new p006_CenterOnScreen();
		centerOnScreen.setVisible(true);
	}

}
