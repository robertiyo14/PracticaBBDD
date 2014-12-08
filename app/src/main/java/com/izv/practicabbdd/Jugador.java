package com.izv.practicabbdd;

import java.io.Serializable;

/**
 * Created by rober on 04/12/2014.
 */
public class Jugador implements Serializable, Comparable<Jugador>{
    private long id;
    private String nombre;
    private String telefono;
    private String fnac;


    //1º constructor predeterminado
    public Jugador() {
        this(0,"","","");
    }

    //2º constructor completo
    public Jugador(long id, String nombre, String telefono, String fnac) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fnac = fnac;
    }

    public Jugador(String nombre, String telefono, String fnac){
        this.id = 0;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fnac = fnac;
    }

    //3º todos los getters y todos los setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFnac() {
        return fnac;
    }

    public void setFnac(String fnac) {
        this.fnac = fnac;
    }

    //4º equals,hasthcode usando la clave principal de la tabla


    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    //5º implementar compareTo
    @Override
    public int compareTo(Jugador jugador) {
        if(this.nombre.compareTo(jugador.nombre)!=0){
            return this.nombre.compareTo(jugador.nombre);
        }else{
            return this.fnac.compareTo(jugador.fnac);
        }
    }

    //6º implementar tostring
    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fnac='" + fnac + '\'' +
                '}';
    }
}
