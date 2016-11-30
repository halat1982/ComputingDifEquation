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
   int X = (int) Math.round(xMax/h)+1; //Столбцы
   int T = (int) Math.round(tMax/tau)+1; //Строки  
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
       for(int i=T-2; i>=0; i--){
          for(int j=(int)xMin+1; j<X-1; j++){
            grid[i][j] = (grid[i+1][j]/tau + (1/alpha)*(grid[i+1][j+1]-2*grid[i+1][j] + grid[i+1][j-1])/Math.pow(h,2))*tau;   
          }
       }
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
   
   
   
   
   
}
