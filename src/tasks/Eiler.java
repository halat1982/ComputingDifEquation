/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks;

import operations.CompEquations;

/**
 *
 * @author halat
 */
public class Eiler {
   double a = 1;
   double b = 0.125;
   double c = -0.3;
   double d = 0.1;
   double alpha = CompEquations.computingAlpha(a, b);
   double beta = CompEquations.computingBeta(c, d);
   double h = 0.1;   
   double x0 = 0;
   double y0 = 0;
   double finish = 1;
   
   private double getAlpha(){
      return alpha;
   }
   
   private double getBeta(){
       return beta;
   }
   
   private double returnResultFunction(double x, double y){
       return 1-Math.sin(alpha*x + y)+ beta*y/(2+x);
   }
   
   public void computing(){
       double y, y1;       
       for(double i = x0; i <= finish; i+=h){
           y = y0 + h*returnResultFunction(i, y0);
           System.out.print(i +" "+ y + " ");
           y1 = y0 +h/2*(returnResultFunction(i, y0)+returnResultFunction(i+h, y));
           System.out.println(y1);
           y0 = y1;
       }
   }
}
