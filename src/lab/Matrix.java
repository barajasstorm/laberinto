/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

/**
 *
 * @author juanba
 */
public class Matrix {
    private int longitud;
    private int matriz[][];
    

    public Matrix() {      
    }
    
    public Matrix(int longitud) {
        this.longitud = longitud;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public int[][] createMatrix() {
        int valores[][] = new int[longitud][longitud];  
        return valores;
    }
    
    public void setMatValues(int x, int y, int value) {
        matriz[x][y] = value;
    }
    
    public void setMatrix(int[][] mat) {
        matriz = mat;
    }
    
    public int[][] getMatrix() {
        return matriz;
    }
    
    
}
