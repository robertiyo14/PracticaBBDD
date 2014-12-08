package com.izv.practicabbdd;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by rober on 04/12/2014.
 */
public class Contrato {
    private Contrato (){
    }

    public static abstract class TablaJugador implements BaseColumns {
        public static final String TABLA = "Jugador";
        public static final String NOMBRE = "nombre";
        public static final String TELEFONO = "telefono";
        public static final String VALORACION = "valoracion";
        public static final String FNAC = "fnac";
    }

    public static abstract class TablaPartido implements BaseColumns {
        public static final String TABLA = "Partido";
        public static final String CONTRINCANTE = "contrincante";
        public static final String IDJUGADOR = "idJugador";
        public static final String VALORACION = "valoracion";
    }
}
