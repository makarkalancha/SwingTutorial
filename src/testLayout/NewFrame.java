package testLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class NewFrame extends JFrame{
	public NewFrame(){
		JPanel left = new JPanel();
		left.setBackground(Color.RED);
		left.setPreferredSize(new Dimension(200, 400));
//		left.setSize(200,400);
//		left.setLayout(new FlowLayout());
		
		JPanel right = new JPanel();
		right.setBackground(Color.BLUE);
		right.setPreferredSize(new Dimension(200, 400));
//		right.setSize(200,400);
//		right.setLayout(new FlowLayout());
		
		add(left, BorderLayout.WEST);
		add(right, BorderLayout.EAST);
		setSize(400, 400);
		setTitle("test");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NewFrame fr = new NewFrame();
		fr.setVisible(true);
		fr.setLocationRelativeTo(null);
	}

}
