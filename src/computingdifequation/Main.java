/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computingdifequation;
import tasks.*;
import training.*;
/**
 *
 * @author halat
 */
public class Main {
    
    static final public double  K = 2.5;
    static final public double N = -3.3;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Eiler eiler = new Eiler();  
        //eiler.computing();
        
        RungeKutti rk = new RungeKutti();
        //rk.computing();
        
        Task4 t4 = new Task4();
        
        t4.computing();    
        //ImplicitMethods imp = new ImplicitMethods();
        //imp.computing();
    }
    
}
