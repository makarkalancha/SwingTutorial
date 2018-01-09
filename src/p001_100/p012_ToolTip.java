package p001_100;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class p012_ToolTip extends JFrame{
	
	private Toolkit toolkit;
	
	public p012_ToolTip(){
		setSize(300,200);
		setTitle("Tooltip");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2 );
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		
		panel.setToolTipText("A panel container");
		
		JButton button = new JButton("Button");
		button.setBounds(100, 60, 80, 30);
		button.setToolTipText("A button component");
		
		panel.add(button);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		p012_ToolTip tooltip = new p012_ToolTip();
		tooltip.setVisible(true);
	}

}
