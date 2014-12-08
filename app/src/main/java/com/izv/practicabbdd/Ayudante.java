package com.izv.practicabbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by rober on 04/12/2014.
 */
public class Ayudante extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "futbol.sqlite";
    public static final int DATABASE_VERSION = 2;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            //creo tablas respaldo
            String sql = "CREATE TABLE Juga (id INTEGER, nombre TEXT, telefono TEXT, valoracion INTEGER, fnac TEXT)";
            db.execSQL(sql);
            //copio los datos
            sql = "INSERT INTO Juga SELECT * FROM jugador";
            db.execSQL(sql);
            //borro tablas originales
            sql = "drop table if exists " + Contrato.TablaJugador.TABLA;
            db.execSQL(sql);
            //creo tablas nuevas
            onCreate(db);
            //meto datos en tablas nuevas
            sql = "INSERT INTO " + Contrato.TablaJugador.TABLA + " (" + Contrato.TablaJugador.NOMBRE + " , " +
                    Contrato.TablaJugador.TELEFONO + " , " + Contrato.TablaJugador.FNAC + ") SELECT nombre, telefono, fnac FROM Juga";
            db.execSQL(sql);
            sql = "INSERT INTO " + Contrato.TablaPartido.TABLA + " (" + Contrato.TablaPartido.VALORACION + " , "
                    + Contrato.TablaPartido.IDJUGADOR +
                    " ) SELECT valoracion, " + Contrato.TablaJugador._ID + " FROM Juga j INNER JOIN " +
                    Contrato.TablaJugador.TABLA + " ju WHERE j.nombre=ju." + Contrato.TablaJugador.NOMBRE +
                    " AND j.telefono=ju." + Contrato.TablaJugador.TELEFONO +
                    " AND j.fnac=ju." + Contrato.TablaJugador.FNAC;
            db.execSQL(sql);
            sql = "UPDATE " + Contrato.TablaPartido.TABLA + " SET " + Contrato.TablaPartido.CONTRINCANTE + "='Contrincante'";
            db.execSQL(sql);
            //borro tablas respaldo
            sql = "drop table Juga";
            db.execSQL(sql);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        sql = "create table " + Contrato.TablaJugador.TABLA +
                " (" + Contrato.TablaJugador._ID +
                " integer primary key autoincrement, " +
                Contrato.TablaJugador.NOMBRE + " text, " +
                Contrato.TablaJugador.TELEFONO + " text," +
                Contrato.TablaJugador.FNAC + " text)";
        Log.v("sql", sql);
        db.execSQL(sql);

        sql = "create table " + Contrato.TablaPartido.TABLA +
                " (" + Contrato.TablaJugador._ID +
                " integer primary key autoincrement, " +
                Contrato.TablaPartido.CONTRINCANTE + " text, " +
                Contrato.TablaPartido.IDJUGADOR + " long," +
                Contrato.TablaPartido.VALORACION + " int)";
        Log.v("sql", sql);
        db.execSQL(sql);

        /*
        String sql;

        sql = "create table "+Contrato.TablaJugador.TABLA+
                " ("+Contrato.TablaJugador._ID+
                " integer primary key autoincrement, "+
                Contrato.TablaJugador.NOMBRE+" text, "+
                Contrato.TablaJugador.TELEFONO+" text,"+
                Contrato.TablaJugador.VALORACION+" integer,"+
                Contrato.TablaJugador.FNAC+" text)";
        Log.v("sql", sql);
        db.execSQL(sql);*/
    }
}
