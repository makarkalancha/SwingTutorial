package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import tetris.Shape.Tetrominoes;

public class RightBoard extends JPanel implements Observer{
	private Subject __subject;
	private final int BoardWidth = 10;
	private final int BoardHeight = 22;
	private Shape oldShape;
	public RightBoard(Board board){
		
		
	}
	
	@Override
	public void update() {
		Shape nextShape = (Shape) __subject.getUpdate(this);
		if(nextShape == null){
//			System.out.println(">>>>>no new shape");
		}else{
//			System.out.println(">>>>>"+nextShape.getShape());
			repaint();
		}
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Dimension size = getSize();
		int boardTop = (int) size.getHeight() - BoardHeight * squareHeight();
		
//		System.out.println("Paint0: boardTop = "+boardTop);
		
		Shape nextShape = (Shape) __subject.getUpdate(this);
		Tetrominoes shape = nextShape.getShape();
		if(shape != Tetrominoes.NoShape){
			for(int i = 0 ; i < 4 ; ++i){
				int x = 5 + nextShape.getX(i);
				int y = 20 - nextShape.getY(i);
				
//				System.out.println("Paint2: x = "+x+"; curX = "+0 + "; curPiece.getX(i) = " +nextShape.getX(i)+
//						"; y = "+y+"; curY = "+0 + "; curPiece.getY(i) = " +nextShape.getY(i));
//				System.out.println("Paint2: squareWidth = "+squareWidth()+"; squareHeight = "+squareHeight() + "; BoardHeight = "+BoardHeight);
//						
//				System.out.println("Paint2: x"+i+"="+(0 + x * squareWidth())+
//						"; y"+i+"="+(boardTop + (BoardHeight - y - 1) * squareHeight())+"; shape="+nextShape.getShape());
				
				drawSquare(g, 0 + x * squareWidth(), boardTop + (BoardHeight - y - 1) * squareHeight(), shape);
			}
		}
	}
	
	@Override
	public void setSubject(Subject subject) {
		this.__subject = subject;
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

}
