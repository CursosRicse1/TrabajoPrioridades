/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.table.DefaultTableModel;
import modelo.RoundRObin;

import vista.Procesarfrm;

/**
 *
 * @author anthony.ricse
 */
public class ControladorApp {

    public static int contador = 0;
    private Procesarfrm vista;
    private RoundRObin objeto;

    public ControladorApp(Procesarfrm vista, RoundRObin objeto) {
        this.vista = vista;
        this.objeto = objeto;

        this.vista.btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                new Thread(new Hilo()).start();

            }
        });
        this.vista.BtnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                DefaultTableModel modelo = (DefaultTableModel) vista.tbIngreso.getModel();
                contador++;

                Object[] miTabla = new Object[5];
                miTabla[0] = contador;
                miTabla[1] = vista.txtDuracion.getText();
                miTabla[2] = vista.txtPrioridad.getText();
                miTabla[3] = vista.txtDuracion.getText();
                miTabla[4] = "Listo";
                modelo.addRow(miTabla);
                vista.tbIngreso.setModel(modelo);
                vista.tbIngreso.setModel(modelo);
                vista.txtDuracion.setText(null);
                vista.txtDuracion.grabFocus();
              
                vista.txtPrioridad.setText(null);
              

            }
        });
    }

    private class Hilo implements Runnable {

        @Override
        public void run() {
            int estado = 1;
            int i = 0;
            int contadorPrioridad = 0;
            while (estado != 0) {
                while (i < contador) {
                    cargar(i);
                    if (objeto.getResiduoRfaga() != 0 && objeto.getResiduoRfaga() > objeto.getPrioridad()) {
                        for (int c = 1; c < objeto.getPrioridad(); c++) {
                            vista.tbIngreso.setValueAt("CARGANDO", i, 4);
                            objeto.setResiduoRfaga(objeto.getResiduoRfaga() - 1);
                            contadorPrioridad++;
                            barra(objeto.getDuracion(), objeto.getResiduoRfaga());
                            pintar();
                            vista.tbIngreso.setValueAt(String.valueOf(objeto.getResiduoRfaga()), i, 3);
                            objeto.setTiempoProceso(objeto.getTiempoProceso() + 1);
                            Dormir();

                        }
                        vista.tbIngreso.setValueAt("Espera", i, 4);
                        if (objeto.getResiduoRfaga() == 0) {
                            vista.tbIngreso.setValueAt("Terminado", i, 4);
                            pintar();
                            informar(i);
                            Borrar(i);
                            vista.jProgressBar1.setValue(0);
                            

                        }
                        
                    }else{
                        if(objeto.getResiduoRfaga() >0 && objeto.getPrioridad() != 0){
                            while(objeto.getResiduoRfaga()>0){
                                vista.tbIngreso.setValueAt("Procesando", i, 4);
                                objeto.setResiduoRfaga(objeto.getResiduoRfaga()-1);
                                barra(objeto.getDuracion(), objeto.getResiduoRfaga());
                                pintar();
                                vista.tbIngreso.setValueAt(String.valueOf(objeto.getResiduoRfaga()), i, 3);
                                objeto.setTiempoProceso(objeto.getTiempoProceso()+1);
                                Dormir();
                            }
                            vista.tbIngreso.setValueAt("Esperar", i, 4);
                            if(objeto.getResiduoRfaga() == 0 && objeto.getPrioridad()!= 0 ){
                            vista.tbIngreso.setValueAt("Terminado", i, 4);
                            pintar();
                                informar(i);
                                Borrar(i);
                                vista.jProgressBar1.setValue(0);
                        }
                        }else{
                            if(objeto.getResiduoRfaga() == 0 && objeto.getPrioridad()!=0){
                                vista.tbIngreso.setValueAt("Terminado", i, 4);
                                pintar();
                                informar(i);
                                Borrar(i);
                                vista.jProgressBar1.setValue(0);
                            }
                        }
                    }
                    vista.JLNumeroPorceso1.setText( String.valueOf(""));
                    vista.jlPorcentajeProceso.setText(String.valueOf(""));
                    i++;
               
                }
                i = 0 ;
                vista.JLNumeroPorceso1.setText(String.valueOf(""));
                vista.jlPorcentajeProceso.setText(String.valueOf(""));
                
            }
        }

    }

    public void iniciar() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
    }

    public void cargar(int i) {
        objeto.setnProceso((int) vista.tbIngreso.getValueAt(i, 0));
     
        objeto.setDuracion(Integer.parseInt((String) (vista.tbIngreso.getValueAt(i, 1))));
        objeto.setPrioridad(Integer.parseInt((String) (vista.tbIngreso.getValueAt(i, 2))));
        objeto.setResiduoRfaga(Integer.parseInt((String) (vista.tbIngreso.getValueAt(i, 3))));
        if (objeto.getnProceso() > 0) {
            vista.jlPorcentajeProceso.setText(String.valueOf(objeto.getnProceso()));
        }
    }

    public void barra(int duracion, int residuo) {
        int rafaga = duracion;
        int valor = 100 / rafaga;
        int porcentaje = 100 - (valor * residuo);
        objeto.setValorBarra(porcentaje);
        vista.jlPorcentajeProceso.setText(String.valueOf(objeto.getValorBarra() + "%"));
    }

    public void pintar() {
        vista.jProgressBar1.setValue(objeto.getValorBarra());
        vista.jProgressBar1.repaint();
    }

    public void Dormir() {
        try {
            Thread.sleep(750); //Dormir sistema
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(Procesarfrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    public void informar(int c) {
        DefaultTableModel modelo2 = (DefaultTableModel) vista.tbInforme.getModel();

        Object[] miTabla = new Object[5];
        miTabla[0] = c + 1;
        miTabla[1] = objeto.getDuracion();
        miTabla[2] = objeto.getPrioridad();
        miTabla[3] = objeto.getTiempoProceso() + "Segundos";
        miTabla[4] = "Terminado";
        modelo2.addRow(miTabla);
        vista.tbInforme.setModel(modelo2);

        objeto.setCantidadProcesos(objeto.getCantidadProcesos() + 1);
        vista.lblCantidadProcesos.setText(String.valueOf(objeto.getCantidadProcesos() + "Terminado"));
        vista.lblCantidadTiempo.setText(String.valueOf(objeto.getTiempoProceso() + "Segundos"));

    }

    public void Borrar(int c) {
        vista.tbIngreso.setValueAt(0, c, 0);
     
        vista.tbIngreso.setValueAt("0", c, 1);
        vista.tbIngreso.setValueAt("0", c, 2);
        vista.tbIngreso.setValueAt("0", c, 3);
        vista.tbIngreso.setValueAt("......", c, 4);

    }
}
