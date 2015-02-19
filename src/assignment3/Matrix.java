package assignment3;

import java.util.ArrayList;

public class Matrix {
	
	//Vector is actually a row vector so we use the transpose of it
	public static double[] MatrixDotVector(double[][] matrix, double[] vector){
		int numRows = matrix.length;
		int numCols = matrix[0].length;
		if(numCols != vector.length) return null;
		double[] result = new double[numCols];
		for(int i = 0; i < numRows; i++){
			result[i] = dotVector(matrix[i],vector);
		}
		return result;
	}
	
	//Dot product of two arrays
	public static double dotVector(double[] left, double[] right){
		int size = left.length;
		if(size != right.length) return -1;
		double result = 0.0;
		for(int i = 0; i < size; i++){
			result = result + left[i]*right[i];
		}
		return result;
	}
	
	public static double[] addVectors(double[] a, double[] b){
		if(a.length != b.length) return null;
		int size = a.length;
		double[] result = new double[size];
		for(int i = 0; i < size; i++){
			result[i] = a[i] + b[i];
		}
		return result;
	}
	
	//Makes sure the components of the matrices column vector sum to one
	public static void constrainSumToOne(double[][] matrix){
		int numRows = matrix.length;
		int numCols = matrix[0].length;
		for(int i = 0; i < numCols; i++){
			//Find the sum of the column vector
			double sum = 0.0;
			for(int j = 0; j < numRows; j++){
				sum = sum + matrix[j][i];
			}
			if(sum != 0.0){
				//Divide each element by the sum
				for(int j = 0; j < numRows; j++){
					matrix[j][i] = matrix[j][i]/sum;
				}
			}
		}
	}
	
	//Multiplies matrix by a scalar
	public static void constMultMatrix(double scalar, double[][] matrix){
		int numRows = matrix.length;
		for(int i = 0; i < numRows; i++){
			constMultVector(scalar,matrix[i]);
		}
	}	
	
	//Multiplies vector by a scalar
	public static void constMultVector(double scalar, double[] vector){
		int size = vector.length;
		for(int i = 0; i < size; i++){
			vector[i] = scalar*vector[i];
		}
	}
	
	//Returns a vector with each element  as the value
	public static double[] makeVector(int size, double value){
		double[] ar = new double[size];
		for(int i = 0; i < size; i++){
			ar[i] = value;
		}
		return ar;
	}
	
	public static ArrayList<Double> convertToArrayList(double[] ar){
		int size = ar.length;
		ArrayList<Double> list = new ArrayList<Double>(size);
		for(int i = 0; i < size; i++){
			list.add(new Double(ar[i]));
		}
		return list;
	}
	
	public static double[] copyArray(double[] src){
		int size = src.length;
		double[] dest = new double[size];
		System.arraycopy(src,0,dest,0,size); 
		return dest;
	}
	
	public static double[][] copyMatrix(double[][] srcMatrix){
		int numRows = srcMatrix.length;
		int numCols = srcMatrix[0].length;
		double[][] destMatrix = new double[numRows][numCols];
		for(int i = 0; i < numRows; i++){
			System.arraycopy(srcMatrix[i],0,destMatrix[i],0,numCols);
		}
		return destMatrix;
	}
}
