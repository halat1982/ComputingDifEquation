/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks;

import operations.AuxiliaryComputing;
import operations.CompEquations;

/**
 *
 * @author halat
 */
public class Task4 {
   double a = 2, b=0.1,c=1, d=0.15;
   double alpha = CompEquations.computingAlpha(a, b);
   double beta = CompEquations.computingBeta(c, d);
   double tau = 0.02;
   double h = 0.1;    
   double tMin = 0;
   double tMax = 0.2;
   double xMin = 0;
   double xMax = 1;
   int X = (int) Math.round(xMax/h)+1; //Столбцы 11
   int T = (int) Math.round(tMax/tau)+1; //Строки  11
   int r = 10000;
   public void computing(){
       //System.out.println("Alpha= "+alpha+" Beta= "+beta);       
       System.out.println("Explicit method \n");
       double[][] grid = AuxiliaryComputing.initZeroMatrix(X, T);       
       fillBottom(grid);
       fillLeft(grid);
       fillRight(grid);
       fillExplicit(grid);
       AuxiliaryComputing.showMatrix(grid);
       System.out.println("Implicit method \n");
       grid = AuxiliaryComputing.initZeroMatrix(X, T);
       fillBottom(grid);
       fillLeft(grid);
       fillRight(grid);
       fillImplicit(grid);
       AuxiliaryComputing.showMatrix(grid); 
   }
   
   private void fillBottom(double[][] grid){
       int i=0;
       for(double x=xMin; x <= xMax; x=(double)Math.round((x+h)*10)/10){
           grid[T-1][i] = ux0(x);           
           i++;
       }
   }
   
   private void fillLeft(double[][] grid){
       int i = 0, j=T;
       for(double t=tMin; t<=tMax; t=(double)Math.round((t+tau)*100)/100){
           grid[j-1][i] = u0t(t);
           j--;          
       }
   }
   
   private void fillRight(double[][] grid){
      int i = T-1, j=X-1;
      for(double t=tMin; t<=tMax; t=(double)Math.round((t+tau)*100)/100){          
          grid[i][j]=u1t(t);
          i--;          
      }
   }
   
   private void fillExplicit(double[][] grid){  
          //int i = 9;
          for(int i=9; i>=0; i--){
          int t=1;
          for(int j=1; j<10; j++){
              //System.out.print("t= "+t*tau+" ");
            grid[i][j] = grid[i+1][j] + (tau/(alpha*Math.pow(h,2)))*(grid[i+1][j+1]-2*grid[i+1][j] + grid[i+1][j-1])+tau*fxt(j*h,t*tau);  // 
            t++;
          }
           //System.out.println("");
          
       }
   }
   
   private void fillImplicit(double[][] grid){      
      double[][] matrix = fillEquationMatrix(X);
      double[][] f = new double[X][1]; 
       
      for(int i = T-2; i>=0; i--){
          int t=1;
        for (int k = 0; k < X; k++) {
            if(k==0 || k==X-1){
                f[k][0] = grid[i][k];
                continue;
            }            
           f[k][0]= -grid[i+1][k]-tau*fxt(k*h,t*tau);//
           t++;
        } 
        //AuxiliaryComputing.showMatrix(f);
        double [][] res = gauss(matrix, f);
        for(int j=(int)xMin+1; j<X-1; j++){
              //System.out.print("t= "+t*tau+" ");
                grid[i][j] = res[j][0];   
            }      
       }
   }
  
   private double[][] fillEquationMatrix(int X){
      double[][] matrix = AuxiliaryComputing.initZeroMatrix(X, X);
      double k1 = tau/(alpha*Math.pow(h, 2));
      double k2 = -1*((Math.pow(h, 2)*alpha+2*tau)/(Math.pow(h, 2)*alpha));
      matrix[0][0] = 1;
      matrix[X-1][X-1] = 1;
      int j=0;
       for (int i = 1; i < X-1; i++) {          
         matrix[i][j] = k1;
         j++;
       }
       j=1;
       for (int i = 1; i < X-1; i++) {
           matrix[i][j] = k2;
           j++;
       }
       j=2;
       for (int i = 1; i < X-1; i++){
           matrix[i][j] = k1;
           j++;
       }        
      // AuxiliaryComputing.showMatrix(matrix);
      return matrix;   
    }    
   
   private double u0t(double t){
    return Math.pow(Math.E,alpha*t);
    }
   
   private double u1t(double t){
       return Math.pow(Math.E, alpha*(t-1));
   }
   
   private double ux0(double x){
       return Math.pow(Math.E, alpha*-x);
   }
   
   private double fxt(double x, double t){
       return beta*(Math.pow(x,2)+Math.pow(t, 2));
   }
 
   public double[][] gauss(double[][] A, double[][] f){
        double[][] q = toTriangleMatrix(A, f);             
        double[][] res = computingX(q, f);              
        
        return res;
    }
   
   private double[][] toTriangleMatrix(double[][] m, double[][] f){
        double[][] a =  AuxiliaryComputing.clone(m);
        int count = 0;        
        while(count < a.length){ 
            f[count][0] /= a[count][count];
            for (int i = a.length-1; i >=0 ; i--) {
               a[count][i] /= a[count][count];                
            }            
            for (int i = count+1; i < a.length; i++){
                f[i][0] = f[i][0] + f[count][0]*(-1)*a[i][count];
            }
            for (int i = count+1; i < a.length; i++) {
                for (int j = a[0].length-1; j >=0 ; j--) {                    
                    a[i][j] = a[i][j] + a[count][j]*(-1)*a[i][count];                    
                }
            }
            count++;
        }
        return a;
    }
   
   private double[][] computingX(double[][] a, double[][] f){
        double[][] x = new double[f.length][f[0].length];
        double temp = 0.0;
        for (int i = f.length-1; i >= 0; i--) {
            temp = 0;
            //System.out.println("STEP "+i+"\n");
            for (int j = f.length-1; j >= 0; j--) {
                //System.out.println("a["+i+"]["+j+"]="+a[i][j]+"*"+"x["+j+"]["+0+"]="+x[j][0]+" ");
                temp += (-1)*a[i][j]*x[j][0];                                
            }
            x[i][0] = temp;            
            x[i][0] += f[i][0]; 
           
        }       
        return x;
    }
   
   
   
   
}
