/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author anthony.ricse
 */
public class RoundRObin {
    private int Contador;
    private int nProceso;
    private int duracion = 0 ; 
    private int prioridad = 0;
    private int ResiduoRfaga = 0 ; 
    private int TiempoProceso = 0 ; 
    private int valorBarra;
    private int TiempoLlegada;
    private int CantidadProcesos;
    
    public RoundRObin(){
        this.Contador =Contador; 
        this.nProceso = nProceso;
        this.duracion = duracion;
        this.ResiduoRfaga = ResiduoRfaga;
    }

    public int getContador() {
        return Contador;
        
    }

    public int getResiduoRfaga() {
        return ResiduoRfaga;
    }

    public void setContador() {
        this.Contador ++;
    }

    public void setnProceso(int nProceso) {
        this.nProceso = nProceso;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public void setResiduoRfaga(int ResiduoRfaga) {
        this.ResiduoRfaga = ResiduoRfaga;
    }

    public void setTiempoLlegada(int TiempoLlegada) {
        this.TiempoLlegada = TiempoLlegada;
    }

    public int getnProceso() {
        return nProceso;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public int getTiempoLlegada() {
        return TiempoLlegada;
    }

    public int getCantidadProcesos() {
        return CantidadProcesos;
    }

    public int getValorBarra() {
        return valorBarra;
    }

    public void setValorBarra(int valorBarra) {
        this.valorBarra = valorBarra;
    }

    public int getTiempoProceso() {
        return TiempoProceso;
    }

    public void setTiempoProceso(int TiempoProceso) {
        this.TiempoProceso = TiempoProceso;
    }

    public void setCantidadProcesos(int CantidadProcesos) {
        this.CantidadProcesos = CantidadProcesos;
    }
    
}
