package tetris;

import java.util.Arrays;
import java.util.Random;

public class Shape {
	enum Tetrominoes{
		NoShape, 
		ZShape, 
		SShape, 
		LineShape,
		TShape, 
		SquareShape, 
		LShape, 
		MirroredLShape
	};
	
	private Tetrominoes pieceShape;
	private int[][] coords;
	private int[][][] coordsTable;
	
	public Shape(){
		coords = new int[4][2];
		setShape(Tetrominoes.NoShape);
	}
	
	public void setShape(Tetrominoes shape){
		coordsTable = new int[][][]{
				{{0,0},{0,0},{0,0},{0,0}},	//noShape
				{{0,-1},{0,0},{-1,0},{-1,1}},
				{{0,-1},{0,0},{1,0},{1,1}},
				{{0,-1},{0,0},{0,1},{0,2}},
				{{-1,0},{0,0},{1,0},{0,1}},
				{{0,0},{1,0},{0,1},{1,1}},
				{{-1,-1},{0,-1},{0,0},{0,1}},
				{{1,-1},{0,-1},{0,0},{0,1}}
		};
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 2; ++j){
				coords[i][j] = coordsTable[shape.ordinal()][i][j];
			}
		}
		
		pieceShape = shape;
	}
	
	private void setX(int index, int x){
		coords[index][0] = x;
	}
	
	private void setY(int index, int y){
		coords[index][1] = y;
	}
	
	public int getX(int index){
		return coords[index][0];
	}
	
	public int getY(int index){
		return coords[index][1];
	}
	
	public Tetrominoes getShape(){
		return pieceShape;
	}
	
	public void setRandomShape(){
		Random r = new Random();
//		int x = Math.abs(r.nextInt()) % 7 + 1;
		int x = Math.abs(r.nextInt()) % (Tetrominoes.values().length-1) + 1;
//		int x = 3;
		
//		System.out.println("setRandomShape:"+x);
		
		Tetrominoes[] values = Tetrominoes.values();
		setShape(values[x]);
	}
	
	public int minX(){
		int m = coords[0][0];
		for(int i=0 ; i < 4 ; i++){
			m = Math.min(m, coords[i][0]);
		}
		
//		System.out.println("minX: coords = "+Utilities.print2DArray(coords));
//		System.out.println("minX: minX = "+m);
		
		return m;
	}
	
	public int minY(){
		int m = coords[0][1];
		for(int i = 0 ; i < 4 ; i++){
			m = Math.min(m, coords[i][1]);
		}
		
//		System.out.println("minY: coords = "+Utilities.print2DArray(coords));
//		System.out.println("minY: minY = "+m);
		
		return m;
	}
	
	public Shape rotateLeft(){
		if(pieceShape == Tetrominoes.SquareShape){
			return this;
		}
		
		Shape result = new Shape();
		result.pieceShape = pieceShape;
		for(int i = 0 ; i < 4 ; ++i){
			result.setX(i, getY(i));
			result.setY(i,-getX(i));
		}
		return result;
	}
	
	public Shape rotateRight(){
		if(pieceShape == Tetrominoes.SquareShape){
			return this;
		}
		
		Shape result = new Shape();
		result.pieceShape = pieceShape;
		for(int i = 0 ; i < 4 ; ++i){
			result.setX(i, -getY(i));
			result.setY(i, getX(i));
		}
		return result;
	}
}
