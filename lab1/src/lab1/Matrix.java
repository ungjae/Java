package lab1;
import java.util.Arrays;

/**
 * <b>Title:</b> Lab 1:<br>
 * <b>Filename:</b> Matrix.java<br>
 * <b>Date Written:</b> September 30, 2018<br>
 * <b>Due Date:</b> September 30, 2018<br>
 * <p>
 * <b>Description:</b><br>
 * Use two-dimensional arrays to create matrix object.<br>
 * </p>
 * <p>
 * We use two-dimensional arrays to simulate a matrix which are given
 * properties such as rows and columns and implement simple matrix computation
 * methods to the class which are:<br>
 * </p>
 * Addition<br>
 * Subtraction<br>
 * Multiplication<br>
 * Scaling<br>
 * Transpose<br>
 * Equal (test to see if two matrices are equal)<br>
 * toString, which prints out the matrix in a legible format<br>
 *</p>
 *@author Ung Jae Yun
 */

public class Matrix {
	
	// instance variables
	int [][] matrix;
	int rows, columns;
	
	// Constructor
	public Matrix (int[][] m) {
		// TODO: Fill matrix variable
		this.matrix = m;  //dont need "this"
		this.rows = m.length;
		this.columns = m[0].length;
	}
	
	// toString method
	public String toString() {
		// TODO: Represent the Matrix in a String format
		// Hint: How is an array printed?
		// DO NOT print directly in this method, meaning
		// don't use System.out.println here
		
		// StringBuffer enables formatting 
		StringBuffer matrixString = new StringBuffer();
		for (int r = 0; r < rows; r++) {
			matrixString.append("["); // start each row with a "["
			
			// append to each row a padded version of the value for a total of 4 spaces
			for (int c = 0; c < columns; c++) {
				if (matrix[r][c] >= 0 && matrix[r][c] < 10) {
					matrixString.append("   " + matrix[r][c]);
				} else if (matrix[r][c] < -9) {
					matrixString.append(" " + matrix[r][c]);
				} else {
					matrixString.append("  " + matrix[r][c]);
				}
			}
			// end each line of matrix with "]" and add new line
			matrixString.append(" ]" + "\n");
		}
		return matrixString.toString();
		
	}
	
	// Helper function to determine if it is legal to add or subtract two matrices
	private boolean sameDimensions(Matrix otherMatrix) {
		return this.rows == otherMatrix.rows && this.columns == otherMatrix.columns;
	}
	
	// Addition
	public Matrix add(Matrix otherMatrix) {
		// TODO: Add the two matrices, store the value
		// in a new matrix and return that matrix
		
		// checks to see if dimensions are same; cannot add if different dimensions
		if (!sameDimensions(otherMatrix)) {
			return null; 		
		}
		
		// create a new array to store the sum
		int[][] sum = new int[rows][columns]; 
		
		//iterate through both arrays and store their sum in new 'sum' array
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				sum[r][c] = matrix[r][c] + otherMatrix.matrix[r][c];
			}
		}
				
		
		return new Matrix(sum);
	}

	// Subtraction
	public Matrix subtract(Matrix otherMatrix) {
		// TODO: Add the two matrices, store the value
		// in a new matrix and return that matrix
		
		// checks to see if dimensions are same; cannot add if different dimensions
		if (!sameDimensions(otherMatrix)) {
			return null;
		}
		
		// create new array to store the difference
		int[][] sub = new int[rows][columns];
		
		// iterate through both arrays and store the difference in the new array 'sub'
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				sub[r][c] = matrix[r][c] - otherMatrix.matrix[r][c];
			}
		}
		
		return new Matrix(sub);
	}
	
	// Helper function to determine if Matrix A has the same number of columns as Matrix B has rows
	// Thus allowing legal Matrix Multiplication
	private boolean colsEqualsOthersRows(Matrix otherMatrix) {
		return this.columns == otherMatrix.rows;
	}
	
	// Matrix Multiplication
	public Matrix mult(Matrix otherMatrix) {
		// TODO: Multiply the two matrices, store the value
		// in a new matrix and return that matrix
		
		// we can only multiply matrices for which the first matrix's columns equals the rows of the other
		if (!colsEqualsOthersRows(otherMatrix)) {
			return null;
		}
		
		// create a new array to store the product
		int[][] pro = new int[rows][otherMatrix.columns];

		// iterate through both matrices and store their products in array 'pro'
		
		// since if matrix a's dimensions are b x c and matrix d's dimensions are c x e
		// a*d's dimensions are a x e and each of ae's elements are the sum of products
		// of corresponding elements in its factors, we append to the new array with the products
		// i.e. ae[1][1] = ∑ a[1][i]*b[i][1] for i = 1 ~ c, hence iterating a third loop
		// for the value of c or in this case columns of "this" matrix which is equal to
		// the rows of the "otherMatrix"
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < otherMatrix.columns; c++) {
				for (int i = 0; i < columns; i++) {
						pro[r][c] += matrix[r][i] * otherMatrix.matrix[i][c];
				}
			}
		}
		
		// creates a new matrix to return with the array that we just created
		return new Matrix(pro);
	}
	
	// Scalar Multiplication
	public Matrix mult(int scalar) {
		// TODO: Multiply the matrix with a scalar value
		// passed as a parameter
		// Store the value in a new matrix and return that matrix
		
		// new empty array to store the scalar values
		int[][] sc = new int[rows][columns];
		
		// iterate through the matrix and multiply each value by scalar and store in 'sc'
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				sc[r][c] = matrix[r][c] * scalar;
			}
		}
		
		return new Matrix(sc);
	}

	// Transpose
	public Matrix transpose() {
		// TODO: Transpose the matrix
		// Store the value in a new matrix and return that matrix
		
		// create an empty array to store the transpose
		int[][] tran = new int[columns][rows];	
		
		// iterate through the matrix and store its rows as columns and vice versa
		for (int r = 0; r < columns; r++) { // original rows become columns
			for (int c = 0; c < rows; c++) {
				tran[r][c] = matrix[c][r];
			}
		}
		
		return new Matrix(tran);
	}
	
	// Equal
	public boolean equal(Matrix otherMatrix) {
		// TODO: Test whether two matrices are equal
		// Return true/false
		
		// if two matrices aren't the same dimensions, they cannot be equal
		if (!sameDimensions(otherMatrix)) {
			return false;
		}
		
		// iterate through the matrices and return false to exit if any of them aren't equal
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				if (matrix[r][c] != otherMatrix.matrix[r][c]) {
					return false;
				}
			}
		}
		
		return true; // if no differences are found, return true
	}
}