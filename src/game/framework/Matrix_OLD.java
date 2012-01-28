package game.framework;

import java.io.Serializable;

/**
 * A class that represents a matrix using a 2D array
 * @author mike, paul
 */
public class Matrix_OLD implements Serializable {
    
    // <editor-fold defaultstate="collapsed" desc="Properties">
    /**
     * A variable that stores the default amount of rows in a Matrix
     */
    private final static int DEFAULT_SIZE_ROW = 10;
    
    /**
     * A variable that stores the default amount of columns in a Matrix
     */
    private final static int DEFAULT_SIZE_COlUMN = 10;
    
    /**
     * The 2D array that stores the data inside of the Matrix
     */
    private float[][] data;
    
    /**
     * The amount of rows this Matrix has
     */
    private int rows;
    
    /**
     * The amount of columns this matrix has
     */
    private int columns;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Constructs a Matrix that contains all of the data from the 2D array input
     *
     * @param matrix a 2D array containing all of that data to be included in
     * the new Matrix
     */
    public Matrix_OLD(float[][] data) {
        this.data = data;
        this.rows = data.length;
        this.columns = data[0].length;
    }
    
    /**
     * A constructor that copies a Matrix
     * @param matrix the Matrix that will be copied.
     */
    public Matrix_OLD(Matrix_OLD matrix){
        this(matrix.getData());
    }

    /**
     * Constructs a new Matrix with null data but with the dimensions specified
     *
     * @param rows the amount of rows the new Matrix will have
     * @param columns the amount of columns the new Matrix will have.
     */
    public Matrix_OLD(int rows, int columns) {
        this(new float[rows][columns]);
    }

    /**
     * A default constructor that constructs a new Matrix with null data and
     * with the dimensions specified in the DEFAULT_SIZE_ROW and
     * DEFAULT_SIZE_COlUMN variables
     */
    public Matrix_OLD() {
        this(new float[DEFAULT_SIZE_ROW][DEFAULT_SIZE_COlUMN]);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="gets">
    /**
     * Returns the number of columns in the matrix
     *
     * @return the amount of rows in this Matrix
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Returns the number of rows in the matrix
     *
     * @return the amount of rows in this Matrix
     */
    public int getRows() {
        return rows;
    }

    /**
     * Returns a clone of the 2D array inside this Matrix
     *
     * @return the data inside of this Matrix
     */
    public float[][] getData() {
        return data.clone();
    }
    
    /**
     * gets data from the Matrix at a position
     * @param row the row that the desired data is at
     * @param column the column that the desired data is at
     * @return the data the specified position
     */
    public float getData(int row, int column){
        return this.data[row][column];
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="add">
    /**
     * Adds two matrices together
     *
     * @param matrix1 the first matrix to be added
     * @param matrix2 the second matrix to be added
     * @return a Matrix containing the result of matrix1 + matrix2
     */
    public static Matrix_OLD add(Matrix_OLD matrix1, Matrix_OLD matrix2) {
        Boolean addable = (matrix1.rows == matrix2.rows) && (matrix1.columns == matrix2.columns);
        float[][] a = matrix1.getData();
        float[][] b = matrix2.getData();
        float[][] summed = null;

        if (addable) {
            summed = new float[matrix1.rows][matrix2.columns];
            for (int i = 0; i < matrix1.rows; i++) {
                for (int j = 0; j < matrix2.columns; j++) {
                    summed[i][j] = a[i][j] + b[i][j];
                }
            }
        }//END IF
        return new Matrix_OLD(summed);
    }

    /**
     * adds a matrix to this matrix
     *
     * @param matrix the second matrix to be added
     * @return a Matrix containing the result of this + matrix
     */
    public Matrix_OLD add(Matrix_OLD matrix) {
        return Matrix_OLD.add(this, matrix);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="subtract">
    /**
     * Subtracts two matrices from each other
     *
     * @param matrix1 the first matrix to be subtracted
     * @param matrix2 the second matrix to be subtracted
     * @return a Matrix containing the result of matrix1 - matrix2
     */
    public static Matrix_OLD subtract(Matrix_OLD matrix1, Matrix_OLD matrix2) {
        Boolean subtractable = (matrix1.rows == matrix2.rows) && (matrix1.columns == matrix2.columns);
        float[][] a = matrix1.getData();
        float[][] b = matrix2.getData();
        float[][] differance = null;

        if (subtractable) {
            differance = new float[matrix1.rows][matrix2.columns];
            for (int i = 0; i < matrix1.rows; i++) {
                for (int j = 0; j < matrix2.columns; j++) {
                    differance[i][j] = a[i][j] - b[i][j];
                }
            }
        }//END IF
        return new Matrix_OLD(differance);
    }

    /**
     * Subtracts a matrix from this matrix
     *
     * @param matrix the matrix that will be subtracted from this matrix
     * @return a Matrix containing the result of this - matrix
     */
    public Matrix_OLD subtract(Matrix_OLD matrix) {
        return subtract(this, matrix);
    }
    // </editor-fold>    

    // <editor-fold defaultstate="collapsed" desc="multiply">
    /**
     * Multiply two matrices together
     * @param matrix1 the first Matrix
     * @param matrix2 the second Matrix
     * @return the result of matrix1 * matrix2
     */
    public static Matrix_OLD multiply(Matrix_OLD matrix1, Matrix_OLD matrix2) {
        float[][] result = new float[0][0];
        float[][] data1;
        float[][] data2;
        
        if (matrix1.columns == matrix2.rows) {
            data1 = matrix1.getData();
            data2 = matrix2.getData();
            result = new float[matrix1.rows][matrix2.columns];

            for (int i = 0; i < matrix1.rows; i++) {
                for (int j = 0; j < matrix2.columns; j++) {
                    for (int k = 0; k < matrix1.columns; k++) {
                        result[i][j] += data1[i][k] * data2[k][j];
                    }
                }
            }
        }
        return new Matrix_OLD(result);
    }
    
    /**
     * Multiply this Matrix with another matrix
     * @param matrix the second Matrix
     * @return the result of this * matrix
     */
    public Matrix_OLD multiply(Matrix_OLD matrix){
        return Matrix_OLD.multiply(this,matrix);
    }  
    // </editor-fold>
   
    // <editor-fold defaultstate="collapsed" desc="toString">
     /**
     * Generates a String representing all of the data in this Matrix
     * @return the String that represents the data
     */
    @Override
    public String toString(){
        String str = "";
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                str += this.data[i][j] + " ";
            }
            str += "\n";
        }
        return str;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="identity">
    /**
     * returns An identity Matrix with the dimensions given
     * @param i The dimensions of the square Matrix to be returned
     * @return The identity Matrix with the square dimensions given
     */
    public static Matrix_OLD identity(int i) {
        if (i < 1) return null;
        
        float[][] data = new float[i][i];
        int oneSpot = 0;
        
        for (int j = 0; j < i; j++) {
            for (int k = 0; k < i; k++) {
                if ( k == oneSpot ){
                    data[j][k] = 1.0f;
                }else data[j][k] = 0.0f;
            }
            oneSpot++;
        }
        return new Matrix_OLD(data);
    }
    
    /**
     * returns an identity matrix if and only if the matrix argument is square
     * @param matrix the square matrix
     * @return an identity Matrix that has the same dimensions as the Matrix argument
     */
    public static Matrix_OLD identity(Matrix_OLD matrix){
        int dimention;
        boolean isSquare;
        
        isSquare = matrix.rows == matrix.columns;
        
        if(isSquare){
            dimention = matrix.rows;
        }else dimention = 0;
        
        return identity(dimention);
    }
    
    /**
     * Returns an identity Matrix with the dimensions of this Matrix
     * @return an identity Matrix with the dimensions of this Matrix
     */
    public Matrix_OLD identity(){
        return Matrix_OLD.identity(this);
    }
    // </editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="transpose">
    /**
     * Takes a Matrix and transposes it
     * @param matrix The Matrix to be transposed
     * @return the transposition of the Matrix
     */
    public static Matrix_OLD transpose(Matrix_OLD matrix){
        float[][] tMatrixData = new float[matrix.columns][matrix.rows];
        float[][] matrixData = matrix.getData();
        
        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.columns; j++) {
                tMatrixData[j][i] = matrixData[i][j];
            }
        }
        return new Matrix_OLD(tMatrixData);
    }
    
    /**
     * Transposes this Matrix
     * @return The transposition of this Matrix
     */
    public Matrix_OLD transpose(){
        return Matrix_OLD.transpose(this);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="vectorToMatrix">
    /**
     * Converts a Vector2 to a Matrix
     * @param vector2 The Vector2 to be converted
     * @return A Matrix with the same data as the Vector2
     */
    public static Matrix_OLD vectorToMatrix(Vector2 vector2){
        float[][] data = {{vector2.x},{vector2.y}};
        return new Matrix_OLD(data);
    }
    
    /**
     * Converts a Vector3 to a Matrix
     * @param vector3 The Vector3 to be converted
     * @return A Matrix with the same data as the Vector3
     */
    public static Matrix_OLD vectorToMatrix(Vector3 vector3){
        float[][] data = {{vector3.x},{vector3.y},{vector3.z}};
        return new Matrix_OLD(data);
    }
    
    /**
     * Converts a Vector4 to a Matrix
     * @param vector4 The Vector4 to be converted
     * @return A Matrix with the same data as the Vector4
     */
    public static Matrix_OLD vectorToMatrix(Vector4 vector4){
        float[][] data = {{vector4.x},{vector4.y},{vector4.z},{vector4.w}};
        return new Matrix_OLD(data);
    }
    //</editor-fold>

   
}