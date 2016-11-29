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
public class RungeKutti {
    double a=2, b=0.125, c=2, d=0.15;
    double alpha = CompEquations.computingAlpha(a, b);
    double beta = CompEquations.computingBeta(c, d);
    double start = 0;
    double finish = 1;
    double h = 0.05;
    double x0 = 0;
    double y0 = 0.5;
    double z0 = 1;        
        
    public void computing(){           
        int i = 0;
        double tx, y, ky1, ky2, ky3, ky4, fxy, dy, z, dz, fxz,kz1, kz2, kz3, kz4;
        for(double x=start; x<=finish; x=(double)Math.round((x+h)*100)/100){
            System.out.println("Step "+i);
            tx = x; 
            fxy = getFuncResultXY(x,y0); //z'= ...
            fxz = getFuncResultXZ(x, z0); //y'= ...
            kz1 = h*fxy;
            ky1 = h*fxz;            
            System.out.println("x="+x+" z="+z0+" f1xy="+fxy+" dz="+kz1 +" y="+y0+" f1xz="+fxz+" ky1="+ky1+" dy="+ky1);
            tx = x+h/2;
            z = z0 + kz1/2;
            y = y0 + ky1/2;
            fxz =  getFuncResultXZ(tx, z);
            fxy =  getFuncResultXY(tx,y);
            ky2 = h*fxz;
            kz2 = h*fxy;
            System.out.println("x="+tx+" z="+z+" f2xy="+fxy+" dz="+2*kz2 +" y="+y+" f2xz="+fxz+" ky2="+ky2+" dy="+2*ky2);
            y = y0 + ky2/2;
            z = z0 + kz2/2;
            fxy = getFuncResultXY(tx,y);
            fxz = getFuncResultXY(tx,z);
            ky3 = h*fxz;
            kz3 = h*fxy;
            System.out.println("x="+tx+" z="+z+" f3xy="+fxy+" dz="+2*kz3 +" y="+y+" f3xz="+fxz+" ky3="+ky3+" dy="+2*ky3);
            tx += h/2;
            y = y0 + ky3;
            z = z0 + kz3;
            fxy = getFuncResultXY(tx, y);
            fxz = getFuncResultXY(tx, z);
            ky4 = h*fxz;
            kz4 = h*fxy;
            System.out.println("x="+tx+" z="+z+" f4xy="+fxy+" dz="+kz4 +" y="+y+" f4xz="+fxz+" ky4="+ky4+" dy="+ky4);
            dy = (ky1 + 2*ky2 + 2*ky3 + ky4)/6;
            dz = (kz1 + 2*kz2 + 2*kz3 +kz4)/6;
            y0 = y0 + dy;
            z0 = z0 + dz;
            //System.out.println(dy+" y0 ="+y0+" "+dz+" z0="+z0);
            //System.out.println("x="+x);
            i++;
        }
    }
    
    private double getFuncResultXY(double x, double y){
       return Math.sqrt(Math.pow(alpha, 2)*Math.pow(x, 2)+ Math.pow(y, 2));
    }
    
    private double getFuncResultXZ(double x, double z){
        return Math.log(Math.pow(beta, 2)*Math.pow(x, 2) + Math.sqrt(Math.pow(alpha, 2)*Math.pow(x, 2) + Math.pow(z, 2)));
    }
}
