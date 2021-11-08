/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import controlador.ControladorApp;
import modelo.RoundRObin;
import vista.Procesarfrm;

/**
 *
 * @author anthony.ricse
 */
public class app {
   
    public static void main(String []args){
    
        
        RoundRObin objeto = new RoundRObin();
        Procesarfrm fPrincipal = new Procesarfrm();
        ControladorApp controla =new ControladorApp(fPrincipal, objeto);
        controla.iniciar();
        
    }
}
