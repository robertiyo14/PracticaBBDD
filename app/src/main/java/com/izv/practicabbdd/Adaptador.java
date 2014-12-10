package com.izv.practicabbdd;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by rober on 04/12/2014.
 */

public class Adaptador extends CursorAdapter {
    private TextView tvNombre, tvTelefono, tvValoracion, tvFnac;
    private Cursor c;

    public Adaptador(Context context, Cursor c) {
        super(context, c, true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater i = LayoutInflater.from(viewGroup.getContext());
        View v = i.inflate(R.layout.detalle, viewGroup, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        GestionJugador gj = new GestionJugador(context);
        Jugador j = gj.getRow(cursor);
        tvNombre = (TextView) view.findViewById(R.id.tvNombre);
        tvTelefono = (TextView) view.findViewById(R.id.tvTelefono);
        tvValoracion = (TextView) view.findViewById(R.id.tvValoracion);
        tvFnac = (TextView) view.findViewById(R.id.tvFnac);
        tvNombre.setText(j.getNombre());
        tvFnac.setText(j.getFnac());
        tvTelefono.setText(j.getTelefono());
        tvValoracion.setText(gj.obtenerMedia(j.getId())+"");
    }
}

