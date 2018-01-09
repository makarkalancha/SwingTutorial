package p001_100;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class p071_TipOfDay extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JPopupMenu menu;
	private Toolkit toolkit;
	
	public p071_TipOfDay(){
		setSize(750, 500);
		setTitle("Tip of the Day");
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
		
		JPanel basic = new JPanel();
		basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
		add(basic);
		
		JPanel topPanel = new JPanel(new BorderLayout(0,0));
		topPanel.setMaximumSize(new Dimension(450,0));
		JLabel hint = new JLabel("JDeveloper Productivity Hints");
		hint.setBorder(BorderFactory.createEmptyBorder(0,25,0,0));
		topPanel.add(hint);
		
		JLabel label = new JLabel(iconNew);
		label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		topPanel.add(label,BorderLayout.EAST);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.gray);
		
		topPanel.add(separator,BorderLayout.SOUTH);
		basic.add(topPanel);
		
		JPanel textPanel = new JPanel(new BorderLayout());
		textPanel.setBorder(BorderFactory.createEmptyBorder(15,25,15,25));
		
		JTextPane pane = new JTextPane();
		pane.setContentType("text/html");
		String text = "<p><b>Closing windows using the mouse wheel</b></p>"+
				"<p>Clicking with the mouse wheel on an editor tab closes the window. "+
				"This method works also with dockable windows or Log window tabs.</p>";
		pane.setText(text);
		pane.setEditable(false);
		textPanel.add(pane);
		basic.add(textPanel);
		
		JPanel boxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,20,0));
		JCheckBox box = new JCheckBox("Show Tips at startup");
		box.setMnemonic(KeyEvent.VK_S);
		boxPanel.add(box);
		basic.add(boxPanel);
		
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton ntip = new JButton("Next Tip");
		ntip.setMnemonic(KeyEvent.VK_N);
		JButton close = new JButton("Close");
		close.setMnemonic(KeyEvent.VK_C);
		close.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		bottom.add(ntip);
		bottom.add(close);
		basic.add(bottom);
		
		bottom.setMaximumSize(new Dimension(450,0));
		setResizable(false);
		
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
		
		new p071_TipOfDay();
	}
	
	private class CloseApp implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
		
	}


}
