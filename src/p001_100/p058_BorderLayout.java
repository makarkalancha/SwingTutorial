package p001_100;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class p058_BorderLayout extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPopupMenu menu;
	private Toolkit toolkit;

	public p058_BorderLayout(){
		setSize(750,500);
		setTitle("Border");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		
		ImageIcon iconNew = new ImageIcon("new_small.png");
		ImageIcon iconOpen = new ImageIcon("open_small.png");
		ImageIcon iconSave = new ImageIcon("save_small.png");
		ImageIcon iconExit = new ImageIcon("exit_small.png");
		
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		
		JMenu imp = new JMenu("Import");
		imp.setMnemonic(KeyEvent.VK_M);
		
		JMenuItem newsf = new JMenuItem("Import newsfeed list...");
		JMenuItem bookm = new JMenuItem("Import bookmarks...");
		JMenuItem mail = new JMenuItem("Import mail...");
		imp.add(newsf);
		imp.add(bookm);
		imp.add(mail);
		
		JMenuItem fileNew = new JMenuItem("New", iconNew);
		fileNew.setMnemonic(KeyEvent.VK_N);
		JMenuItem fileOpen = new JMenuItem("Open", iconOpen);
		fileOpen.setMnemonic(KeyEvent.VK_O);
		JMenuItem fileSave = new JMenuItem("Save", iconSave);
		fileSave.setMnemonic(KeyEvent.VK_S);
		JMenuItem fileClose = new JMenuItem("Close", iconExit);
		fileClose.setMnemonic(KeyEvent.VK_C);
		fileClose.setToolTipText("Exit Application");
		fileClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		fileClose.addActionListener(new CloseApp());
		
		file.add(fileNew);
		file.add(fileOpen);
		file.add(fileSave);
		file.addSeparator();
		file.add(imp);
		file.addSeparator();
		file.add(fileClose);
		menuBar.add(file);
		
		JMenu view = new JMenu("View");
		view.setMnemonic(KeyEvent.VK_V);
		
		JCheckBoxMenuItem sbar = new JCheckBoxMenuItem("Show status");
		sbar.setState(true);
//		sbar.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				if(statusbar.isVisible()){
//					statusbar.setVisible(false);
//				}else{
//					statusbar.setVisible(true);
//				}
//			}
//		});
		view.add(sbar);
		menuBar.add(view);
		
//		statusbar = new JLabel(" StatusBar");
//		statusbar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
//		add(statusbar, BorderLayout.SOUTH);
		
		toolkit = getToolkit();
		menu = new JPopupMenu();
		JMenuItem menuItemBeep = new JMenuItem("Beep");
		menuItemBeep.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				toolkit.beep();
			}
		});
		menu.add(menuItemBeep);
		
		JMenuItem menuItemClose = new JMenuItem("Close");
		menuItemClose.addActionListener(new CloseApp());
		menu.add(menuItemClose);
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getButton() == arg0.BUTTON3){
					menu.show(arg0.getComponent(), arg0.getX(), arg0.getY());
				}
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JToolBar toolbar = new JToolBar();
		toolbar.setFloatable(false);
		JButton bexit = new JButton(iconExit);
		bexit.setBorder(new EmptyBorder(0,0,0,0));
		toolbar.add(bexit);
		add(toolbar, BorderLayout.NORTH);
		
		JToolBar vertical = new JToolBar(JToolBar.VERTICAL);
		vertical.setFloatable(false);
		vertical.setMargin(new Insets(10, 5, 5, 5));
		JButton selectb = new JButton(iconNew);
		selectb.setBorder(new EmptyBorder(3,0,3,0));
		JButton freehandb = new JButton(iconOpen);
		freehandb.setBorder(new EmptyBorder(3,0,3,0));
		JButton shapeedb = new JButton(iconSave);
		shapeedb.setBorder(new EmptyBorder(3,0,3,0));
		vertical.add(selectb);
		vertical.add(freehandb);
		vertical.add(shapeedb);
		add(vertical, BorderLayout.WEST);
		
		add(new JTextArea(),BorderLayout.CENTER);
		
		JLabel statusbar = new JLabel(" Statusbar");
		statusbar.setPreferredSize(new Dimension(-1,22));
		statusbar.setBorder(LineBorder.createGrayLineBorder());
		add(statusbar,BorderLayout.SOUTH);
				
		
		setJMenuBar(menuBar);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){
			System.out.println("Error: "+e.getStackTrace());
		}
		
		new p058_BorderLayout();
	}
	
	private class CloseApp implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
		
	}

}
