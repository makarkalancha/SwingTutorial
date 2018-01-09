package tetris;

import tetris.Shape.Tetrominoes;

public class Utilities {

	public static StringBuilder print2DArray(int[][] array){
		StringBuilder result = new StringBuilder();
		for(int i = 0 ; i < array.length ; i++){
			result.append(i+": {");
			for(int j = 0 ; j < array[i].length ; j++){
				result.append(array[i][j]);
				result.append(", ");
			}
			result.append("}; ");
		}
		return result;
	}
	
	public static StringBuilder print1DArray(Tetrominoes[] array){
		StringBuilder result = new StringBuilder();
		for(int i = 0 ; i < array.length ; i++){
			if(array[i] != Tetrominoes.NoShape){
				result.append(i+": {");
				result.append(array[i]);
				result.append("}; ");
			}
			
		}
		return result;
	}
}
