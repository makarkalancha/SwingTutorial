package p001_100;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class p008_Buttons extends JFrame{
	
	private Toolkit toolkit;
	
	public p008_Buttons(){
		setSize(300,200);
		setTitle("Buttons");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2 );
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton beep = new JButton("Beep");
		beep.setBounds(150, 60, 80, 30);
		beep.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				toolkit.beep();
			}
		});
		
		JButton close = new JButton("Close");
		close.setBounds(50, 60, 80, 30);
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		panel.add(beep);
		panel.add(close);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		p008_Buttons button = new p008_Buttons();
		button.setVisible(true);
	}

}
