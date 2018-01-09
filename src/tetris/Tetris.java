package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Tetris extends JFrame{
	private JLabel statusbar;
	private Board board;
	private RightBoard rightBoard;
	private final int WIDTH = 400;
	private final int HEIGHT = 400;
	public Tetris(){
		statusbar = new JLabel(" 0");
		add(statusbar,BorderLayout.SOUTH);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu game = new JMenu("Game");
		game.setMnemonic(KeyEvent.VK_G);
		
		ImageIcon iconNew = new ImageIcon("new_small.png");
		ImageIcon iconClose = new ImageIcon("exit_small.png"); 
		
		JMenuItem restart = new JMenuItem("Restart", iconNew);
		restart.setMnemonic(KeyEvent.VK_R);
		restart.setToolTipText("Restart");
		restart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		restart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				board.start();
			}
		});
		
		JMenuItem close = new JMenuItem("Close", iconClose);
		close.setMnemonic(KeyEvent.VK_C);
		close.setToolTipText("Exit Tetris");
		close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		game.add(restart);
		game.add(close);
		menuBar.add(game);
		add(menuBar);
		
		board = new Board(this);
		board.setLayout(new BoxLayout(board, BoxLayout.Y_AXIS));
		board.setPreferredSize(new Dimension(WIDTH/2,HEIGHT));
		
//		board.start();
//		add(board);
		
		rightBoard = new RightBoard(board);
//		rightBoard.setLayout(new BoxLayout(board, BoxLayout.Y_AXIS));
//		rightBoard.setBackground(Color.GREEN);
		rightBoard.setBackground(new Color(156, 160, 165));
		rightBoard.setPreferredSize(new Dimension(WIDTH/2,HEIGHT));
		
		board.register(rightBoard);
		rightBoard.setSubject(board);
		
		
		add(board, BorderLayout.WEST);
		add(rightBoard, BorderLayout.EAST);
		
		
//		JPanel a = new JPanel();
//		a.setLayout(new BoxLayout(a, BoxLayout.Y_AXIS));
//		add(a);
//		a.setBackground(Color.red);
		
		setJMenuBar(menuBar);
//		setSize(200, 400);
		setSize(WIDTH, HEIGHT);
		setTitle("TETRIS");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public JLabel getStatusBar(){
		return statusbar;
	}
	
	public static void main(String[] args) {
		Tetris game = new Tetris();
		game.setLocationRelativeTo(null);
		game.setVisible(true);
	}

}
