package p001_100;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class p016_Menu extends JFrame{
	
	public p016_Menu(){
		setSize(300,200);
		setTitle("JMenuBar");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		
		JMenuBar menuBar = new JMenuBar();
		ImageIcon icon = new ImageIcon("exit_small.png");
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		
		JMenuItem fileClose = new JMenuItem("Close",icon);
		fileClose.setMnemonic(KeyEvent.VK_C);
		fileClose.setToolTipText("Exit Application");
		fileClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		file.add(fileClose);
		menuBar.add(file);
		
		setJMenuBar(menuBar);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new p016_Menu();
	}

}
