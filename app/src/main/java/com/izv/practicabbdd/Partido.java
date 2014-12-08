package com.izv.practicabbdd;

/**
 * Created by rober on 08/12/2014.
 */
public class Partido {

    long id, idJugador;
    String contrincante;
    int valoracion;

    public Partido(long id, long idJugador, String contrincante, int valoracion) {
        this.id = id;
        this.idJugador = idJugador;
        this.contrincante = contrincante;
        this.valoracion = valoracion;
    }

    public Partido() {
        this(0,0,null,0);
    }

    public Partido(String idJugador,String contrincante,String valoracion){
        this.id = 0;
        this.contrincante = contrincante;
        try{
            this.idJugador = Long.parseLong(idJugador);
            this.valoracion = Integer.parseInt(valoracion);
        }catch (Exception e){
            this.idJugador=0;
            this.valoracion=0;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(long idJugador) {
        this.idJugador = idJugador;
    }

    public String getContrincante() {
        return contrincante;
    }

    public void setContrincante(String contrincante) {
        this.contrincante = contrincante;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }
}
