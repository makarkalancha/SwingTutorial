package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import tetris.Shape.Tetrominoes;

public class Board extends JPanel implements ActionListener, Subject{
	private final int BoardWidth = 10;
	private final int BoardHeight = 22;
	private Timer timer;
	private boolean isFallingFinished = false;
	private boolean isStarted = false;
	private boolean isPaused = false;
	private int numLinesRemoved = 0;
	private int curX = 0;
	private int curY = 0;
	private JLabel statusbar;
	private Shape curPiece;
	private Shape nextPiece;
	private Tetrominoes[] board;
	
	private List<Observer> __observers;
	private boolean __changed;
	private final Object LOCK = new Object();
	
	public Board(Tetris parent){
		this.__observers = new ArrayList<>();
		
		setFocusable(true);
		curPiece = new Shape();
		nextPiece = new Shape();
//		timer = new Timer(400, this);
		timer = new Timer(3000, this);
//		timer.start();
		statusbar = parent.getStatusBar();
		board = new Tetrominoes[BoardWidth * (BoardHeight + 1)];//TODO ?
//		board = new Tetrominoes[500];//TODO ?
		addKeyListener(new TAdapter());
		clearBoard();
		setBorder(BorderFactory.createLineBorder(Color.black));
		
//		System.out.println(BoardWidth * BoardHeight);
	}
	
	int squareWidth(){
//		System.out.println("size / width = "+ getSize().getWidth());
//		System.out.println("width / bW = "+ (int) getSize().getWidth() / BoardWidth);
		return (int) getSize().getWidth() / BoardWidth;
	}
		
	int squareHeight(){
//		System.out.println("size / height = "+ getSize().getHeight());
//		System.out.println("height / bH = "+ (int) getSize().getHeight() / BoardHeight);
		return (int) getSize().getHeight() / BoardHeight;
	}
	
	//TODO ?
	Tetrominoes shapeAt(int x, int y){
//		System.out.println("shapeAt: y = "+y+"; bw = "+BoardWidth +"; x = "+ x);
//		System.out.println("shapeAt: (y * BoardWidth) + x = "+ ((y * BoardWidth) + x));
//		System.out.println("shapeAt: obj = "+ board[(y * BoardWidth) + x]);
		
//		System.out.println("shapeAt: "+board);
		
		return board[(y * BoardWidth) + x];
	}
	
	private void clearBoard(){
//		for(int i = 0 ; i < BoardHeight * BoardWidth ; ++i){
		for(int i = 0 ; i < board.length ; ++i){
			board[i] = Tetrominoes.NoShape;
		}
	}
	
	private boolean tryMove(Shape newPiece, int newX, int newY){
		for(int i = 0 ; i < 4 ; ++i){
			
			
			int x = newX + newPiece.getX(i);
//			int y = newY + newPiece.getY(i);
			int y = newY - newPiece.getY(i);
			
//			System.out.println("tryMove1: x"+i+"="+x+"; y"+i+"="+y);
			
//			if(x < 0 || x >= BoardWidth || y < 0 || y >= BoardHeight){
			if(x < 0 || x >= BoardWidth || y < 0 || y > BoardHeight){
//				System.out.println("tryMove2: if(x < 0 || x > BoardWidth || y < 0 || y > BoardHeight)");
//				System.out.println("tryMove2: BoardWidth = "+BoardWidth+"; BoardHeight = "+BoardHeight+"; x = "+x+"; y = "+y);
				return false;
			}
			
			if(shapeAt(x, y) != Tetrominoes.NoShape){
//				System.out.println("tryMove3: if(shapeAt(x, y) != Tetrominoes.NoShape): x = "+x+"; y = "+y+"; obj = "+shapeAt(x, y));
				return false;
			}
		}
		curPiece = newPiece;
		curX = newX;
		curY = newY;
		repaint();
		return true;
	}
	
	private void newPiece(){
//		curPiece.setRandomShape();
		if(nextPiece.getShape() == Tetrominoes.NoShape){
			curPiece.setRandomShape();
			nextPiece.setRandomShape();
			System.out.println("newPiece1: cur = "+curPiece.getShape());
			System.out.println("newPiece1: next = "+nextPiece.getShape());
		}else{
			curPiece.setShape(nextPiece.getShape());
			nextPiece.setRandomShape();
			System.out.println("newPiece2: cur = "+curPiece.getShape());
			System.out.println("newPiece2: next = "+nextPiece.getShape());
		}
		this.__changed=true;
		notifyObservers();
		
		
		curX = BoardWidth / 2 + 1;
		curY = BoardHeight - 1 + curPiece.minY();
		
//		curX = 0;
//		curY = 0;
		
//		System.out.println("newPiece1: BoardWidth = "+BoardWidth+"; BoardHeight = "+BoardHeight+"; curPiece.minY() = "+curPiece.minY());
		
		if(!tryMove(curPiece, curX, curY)){
//			System.out.println("newPiece2: obj = "+curPiece.getShape()+"; curX = "+curX+"; curY = "+curY);
			curPiece.setShape(Tetrominoes.NoShape);
			timer.stop();
			isStarted = false;
			statusbar.setText("Game Over");
		}
	}
	
	public void start(){
		if(isPaused){
			return;
		}
		isStarted = true;
		isFallingFinished = false;
		numLinesRemoved = 0;
		clearBoard();
		newPiece();
		timer.start();
	}
	
	private void pause(){
		if(!isStarted){
			return;
		}
		isPaused = !isPaused;
		if(isPaused){
			timer.stop();
			statusbar.setText("Paused");
		}else{
			timer.start();
			statusbar.setText(String.valueOf(numLinesRemoved));
		}
		repaint();
	}
	
	private void drawSquare(Graphics g, int x, int y, Tetrominoes shape){
		Color colors[] = {
			new Color(0,0,0), new Color(204,102,102),
			new Color(102,204,102), new Color(102,102,204),
			new Color(204,204,102), new Color(204,102,204),
			new Color(102,204,204), new Color(218,170,0)
		};
		Color color = colors[shape.ordinal()];
		g.setColor(color);
		g.fillRect(x+1, y+1, squareWidth() - 2, squareHeight() - 2);
		
		g.setColor(color.brighter());
		g.drawLine(x, y + squareHeight() - 1, x, y);
		g.drawLine(x, y, x + squareWidth() - 1, y);
		
		g.setColor(color.darker());
		g.drawLine(x + 1, y + squareHeight() - 1, x + squareWidth() - 1, y + squareHeight() - 1);
		g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1, x + squareWidth() - 1, y + 1);
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Dimension size = getSize();
		int boardTop = (int) size.getHeight() - BoardHeight * squareHeight();
		
//		System.out.println("Paint0: boardTop = "+boardTop);
		
		for(int i = 0 ; i < BoardHeight ; ++i){
			for(int j = 0 ; j < BoardWidth ; ++j){
				Tetrominoes shape = shapeAt(j, BoardHeight - i - 1);
				if(shape != Tetrominoes.NoShape){
					
//					System.out.println("Paint1: x("+i+","+j+")="+(0 + j * squareWidth())+
//							"; y("+i+","+j+")="+(boardTop + i * squareHeight())+"; shape="+shape);
					
					drawSquare(g, 0 + j * squareWidth(), boardTop + i * squareHeight(), shape);
//					drawSquare(g, 0 + j * squareWidth(), 0 + i * squareHeight(), shape);
				}
			}
		}
		
		if(curPiece.getShape() != Tetrominoes.NoShape){
			for(int i = 0 ; i < 4 ; ++i){
				int x = curX + curPiece.getX(i);
				int y = curY - curPiece.getY(i);
				
//				System.out.println("Paint2: x = "+x+"; curX = "+curX + "; curPiece.getX(i) = " +curPiece.getX(i)+
//						"; y = "+y+"; curY = "+curY + "; curPiece.getY(i) = " +curPiece.getY(i));
//				System.out.println("Paint2: squareWidth = "+squareWidth()+"; squareHeight = "+squareHeight() + "; BoardHeight = "+BoardHeight);
//						
//				System.out.println("Paint2: x"+i+"="+(0 + x * squareWidth())+
//						"; y"+i+"="+(boardTop + (BoardHeight - y - 1) * squareHeight())+"; shape="+curPiece.getShape());
				
				drawSquare(g, 0 + x * squareWidth(), boardTop + (BoardHeight - y - 1) * squareHeight(), curPiece.getShape());
//				drawSquare(g, 0 + x * squareWidth(), 0 + (BoardHeight - y - 1) * squareHeight(), curPiece.getShape());
			}
		}
	}
	
	//TODO ?
	private void removeFullLines(){
		int numFullLines = 0;
		for(int i = BoardHeight - 1 ; i >= 0 ; --i){
			boolean linesIsFull = true;
			
			for(int j = 0 ; j < BoardWidth ; ++j){
				if(shapeAt(j, i) == Tetrominoes.NoShape){
					linesIsFull = false;
					break;
				}
			}
			
			if(linesIsFull){
				++numFullLines;
				for(int k = i ; k < BoardHeight - 1 ; ++k){
					for(int j = 0 ; j < BoardWidth ; ++j){
						board[ (k * BoardWidth) + j ] = shapeAt(j, k+1);
					}
				}
			}
		}
		
		if(numFullLines > 0){
			numLinesRemoved += (numFullLines*numFullLines*100);
			statusbar.setText(String.valueOf(numLinesRemoved));
			isFallingFinished = true;
			curPiece.setShape(Tetrominoes.NoShape);
			repaint();
		}
	}
	
	private void pieceDropped(){
		for(int i = 0 ; i < 4 ; ++i){
			int x = curX + curPiece.getX(i);
			int y = curY - curPiece.getY(i);
//			int y = curY + curPiece.getY(i);
//			int x = curX;
//			int y = curY;
//			int x = curX + curPiece.getX(i);
//			int y = curY - curPiece.getY(i) + 1;
			
//			System.out.println("pieceDropped1: i = "+i+"; curX = "+curX + "; curPiece.getX(i) = "+curPiece.getX(i));
//			System.out.println("pieceDropped1: i = "+i+"; curY = "+curY + "; curPiece.getY(i) = "+curPiece.getY(i));
			
			board[(y * BoardWidth) + x] = curPiece.getShape();
		}
		
//		System.out.println("pieceDropped2: "+Utilities.print1DArray(board));
		
		removeFullLines();
		if(!isFallingFinished){
			newPiece();
		}
		
	}
	
	private void dropDown(){
		int newY = curY;
		
//		System.out.println("dropDown1: newY = "+newY);
		
		while(newY > 0){
			if(!tryMove(curPiece, curX, newY - 1)){
				break;
			}
			--newY;
		}
		pieceDropped();
	}
	
	private void oneLineDown(){
		if(!tryMove(curPiece, curX, curY - 1)){
			pieceDropped();
		}
	}

	@Override
 	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(isFallingFinished){
			
//			System.out.println("action1: new piece");
			
			isFallingFinished = false;
			newPiece();
		}else{
			
//			System.out.println("action2: one line down");
			
			oneLineDown();
		}
	}
	
	class TAdapter extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent arg0) {
			if(!isStarted || curPiece.getShape() == Tetrominoes.NoShape){
				return;
			}
			
			int keyCode = arg0.getKeyCode();
			if(keyCode == 'p' || keyCode == 'P'){
				pause();
				return;
			}
			
			if(isPaused){
				return;
			}
			
			switch(keyCode){
				case KeyEvent.VK_LEFT:
					tryMove(curPiece, curX - 1, curY);
					break;
				case KeyEvent.VK_RIGHT:
					tryMove(curPiece, curX + 1, curY);
					break;
				case KeyEvent.VK_DOWN:
					tryMove(curPiece.rotateRight(), curX, curY);
					break;
				case KeyEvent.VK_UP:
					tryMove(curPiece.rotateLeft(), curX, curY);
					break;
				case KeyEvent.VK_SPACE:
					dropDown();
					break;
				case 'd':
				case 'D':
					oneLineDown();
					break;
			}
		}		
	}
	
	
	
	@Override
	public void register(Observer observer) {
		if(observer == null){
			throw new NullPointerException("Null Observer");
		}
		
		if(!__observers.contains(observer)){
			__observers.add(observer);
		}
	}

	@Override
	public void unregister(Observer observer) {
		__observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		List<Observer> observers = null;
		synchronized (LOCK) {
			if(!__changed){
				return;
			}
			
			observers = new ArrayList<>(this.__observers);
			this.__changed = false;
		}
		
		for(Observer obs : observers){
			obs.update();
		}
	}

	@Override
	public Object getUpdate(Observer observer) {
		return getNextShape();
	}

	public Shape getNextShape(){
		return nextPiece;
	}
}
