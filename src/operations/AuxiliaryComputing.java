/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operations;

/**
 *
 * @author halat
 */
public class AuxiliaryComputing {
    
    public static double[][] initZeroMatrix(int x, int t){
        double[][] zeroArr = new double[t][x];        
        return zeroArr;
    }
    
    public static void showMatrix(double[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
}
