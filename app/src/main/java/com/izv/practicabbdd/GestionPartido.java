package com.izv.practicabbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rober on 08/12/2014.
 */
public class GestionPartido {
    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestionPartido(Context c) {
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

    public long insert(Partido objeto) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaPartido.VALORACION,objeto.getValoracion());
        valores.put(Contrato.TablaPartido.IDJUGADOR,objeto.getIdJugador());
        valores.put(Contrato.TablaPartido.CONTRINCANTE,objeto.getContrincante());
        long id = bd.insert(Contrato.TablaPartido.TABLA,null, valores);
        return id;//es el codio autonumerico
    }

    public int delete(Partido objeto) {
        String condicion = Contrato.TablaPartido._ID + " = ?";
        String[] argumentos = { objeto.getId() + "" };
        int cuenta = bd.delete(Contrato.TablaPartido.TABLA, condicion,argumentos);
        return cuenta;
    }

    public int update(Partido objeto) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaPartido.CONTRINCANTE, objeto.getContrincante());
        valores.put(Contrato.TablaPartido.IDJUGADOR, objeto.getIdJugador());
        valores.put(Contrato.TablaPartido.VALORACION, objeto.getValoracion());
        String condicion = Contrato.TablaPartido._ID + " = ?";
        String[] argumentos = { objeto.getId() + "" };
        int cuenta = bd.update(Contrato.TablaPartido.TABLA, valores,condicion, argumentos);
        return cuenta;
    }

    public List<Partido> select(String condicion,String[] parametros,String orden) {
        List<Partido> alp = new ArrayList<Partido>();
        Cursor cursor = bd.query(Contrato.TablaPartido.TABLA, null,condicion, null, null, null, null);
        cursor.moveToFirst();
        Partido objeto;
        while (!cursor.isAfterLast()) {
            objeto = getRow(cursor);
            alp.add(objeto);
            cursor.moveToNext();
        }
        cursor.close();
        return alp;
    }

    public List<Partido>  select(){
        return select(null,null,null);
    }

    public Partido getRow(Cursor c) {
        Partido objeto = new Partido();
        objeto.setId(0);
        objeto.setContrincante(c.getString(1));
        objeto.setIdJugador(c.getLong(2));
        objeto.setValoracion(c.getInt(3));
        return objeto;
    }

    public Partido getRow(long id){
        List<Partido> alp = select(Contrato.TablaPartido._ID+" = ? ",new String[]{id+""},null);
        if(alp.isEmpty()){
            return null;
        }
        return alp.get(0);
    }

    public Cursor getCursor(String condicion, String[] parametros, String orden) {
        Cursor cursor = bd.query(Contrato.TablaPartido.TABLA, null, condicion, parametros, null, null,orden);
        return cursor;
    }

    public boolean repetido(Partido p){
        String[] parametros = {p.getIdJugador()+"",p.getContrincante()};
        Cursor c = getCursor(Contrato.TablaPartido.CONTRINCANTE+" = ? AND "+Contrato.TablaPartido.IDJUGADOR+" = ?",parametros,null);
        if(c==null){
            return false;
        }
        return true;
    }
}
