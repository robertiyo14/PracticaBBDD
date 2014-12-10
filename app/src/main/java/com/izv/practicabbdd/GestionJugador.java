package com.izv.practicabbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rober on 04/12/2014.
 */
public class GestionJugador {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestionJugador(Context c) {
        abd = new Ayudante(c);
    }

    public void open() {
        bd = abd.getWritableDatabase();
    }

    public void openRead() {
        bd = abd.getReadableDatabase();
    }

    public void close() {
        abd.close();
    }

    public long insert(Jugador objeto) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaJugador.FNAC,objeto.getFnac());
        valores.put(Contrato.TablaJugador.NOMBRE,objeto.getNombre());
        valores.put(Contrato.TablaJugador.TELEFONO,objeto.getTelefono());
        long id = bd.insert(Contrato.TablaJugador.TABLA,null, valores);
        return id;//es el codio autonumerico
    }

    public int delete(Jugador objeto) {
        String condicion = Contrato.TablaJugador._ID + " = ?";
        String[] argumentos = { objeto.getId() + "" };
        int cuenta = bd.delete(Contrato.TablaJugador.TABLA, condicion,argumentos);
        return cuenta;
    }

    public int update(Jugador objeto) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaJugador.FNAC, objeto.getFnac());
        valores.put(Contrato.TablaJugador.NOMBRE, objeto.getNombre());
        valores.put(Contrato.TablaJugador.TELEFONO, objeto.getTelefono());
        String condicion = Contrato.TablaJugador._ID + " = ?";
        String[] argumentos = { objeto.getId() + "" };
        int cuenta = bd.update(Contrato.TablaJugador.TABLA, valores,condicion, argumentos);
        return cuenta;
    }

    public List<Jugador> select(String condicion,String[] parametros,String orden) {
        List<Jugador> alj = new ArrayList<Jugador>();
        Cursor cursor = bd.query(Contrato.TablaJugador.TABLA, null,condicion, null, null, null, null);

        cursor.moveToFirst();
        Jugador objeto;
        while (!cursor.isAfterLast()) {
            objeto = getRow(cursor);
            alj.add(objeto);
            cursor.moveToNext();
        }
        cursor.close();
        return alj;
    }

    public double obtenerMedia(long id){
        String[] s = new String[]{id+""};
        Cursor c = getCursorGeneral(Contrato.TablaPartido.IDJUGADOR+" = ?",s,null);
        c.moveToFirst();
        int valoracion=0;
        double valoracionMedia=0;
        int contador = 0;
        while(!c.isAfterLast()){
            valoracion+=c.getInt(0);
            contador++;
        }
        valoracionMedia = valoracion/contador;
        return valoracionMedia;
    }

    public List<Jugador>  select(){
        return select(null,null,null);
    }

    public Jugador getRow(Cursor c) {
        Jugador objeto = new Jugador();
        objeto.setId(0);
        objeto.setNombre(c.getString(1));
        objeto.setTelefono(c.getString(2));
        objeto.setFnac(c.getString(3));
        return objeto;
    }

    public Jugador getRow(long id){
        List<Jugador> alj = select(Contrato.TablaJugador._ID+" = ? ",new String[]{id+""},null);
        if(alj.isEmpty()){
            return null;
        }
        return alj.get(0);
    }

    public Cursor getCursor(String condicion, String[] parametros, String orden) {
        Cursor cursor = bd.query(Contrato.TablaJugador.TABLA, null, condicion, parametros, null, null,orden);
        return cursor;
    }

    public Cursor getCursorGeneral( String condicion, String[] parametros, String orden) {
        Cursor cursor = bd.query(Contrato.TablaPartido.TABLA , null, condicion, parametros, null, null,orden);
        return cursor;
    }


}
