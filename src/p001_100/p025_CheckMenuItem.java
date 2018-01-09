package p001_100;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class p025_CheckMenuItem extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel statusbar;

	public p025_CheckMenuItem(){
		setSize(360,250);
		setTitle("CheckBoxMenuItem");
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
		fileClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
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
		sbar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(statusbar.isVisible()){
					statusbar.setVisible(false);
				}else{
					statusbar.setVisible(true);
				}
			}
		});
		view.add(sbar);
		menuBar.add(view);
		
		statusbar = new JLabel(" StatusBar");
		statusbar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		add(statusbar, BorderLayout.SOUTH);
		
		setJMenuBar(menuBar);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new p025_CheckMenuItem();
	}

}
