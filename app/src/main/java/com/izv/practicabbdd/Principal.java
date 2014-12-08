package com.izv.practicabbdd;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class Principal extends Activity {

    private EditText etNombre, etFecha, etValoracion, etTelefono;
    private Button btGuardar;
    private GestionJugador gj;
    private Adaptador ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        gj = new GestionJugador(this);
        etNombre = (EditText)findViewById(R.id.etNombre);
        etFecha = (EditText)findViewById(R.id.etFecha);
        etTelefono = (EditText)findViewById(R.id.etTelefono);
        etValoracion = (EditText)findViewById(R.id.etValoracion);
        btGuardar=(Button)findViewById(R.id.btGuardar);

    }

    @Override
    protected void onResume() {
        super.onResume();
        gj.open();
        Cursor c = gj.getCursor(null, null, null);
        ad = new Adaptador(this, c);
        final ListView lv =(ListView) findViewById(R.id.lvLista);
        lv.setAdapter(ad);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor=(Cursor)lv.getItemAtPosition(position);
                Jugador j=gj.getRow(cursor);
                gj.delete(j);
                //el changecursor se usa para actualizar los datos del cursor
                ad.changeCursor(gj.getCursor(null,null,null));
                return false;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        gj.close();
    }

    public void alta(View v){
        String nombre, fecha, telefono;
        nombre=etNombre.getText().toString();
        fecha=etFecha.getText().toString();
        telefono=etTelefono.getText().toString();
        Jugador j = new Jugador(nombre,telefono,fecha);
        Long id = gj.insert(j);
        Toast.makeText(this,"El jugador que se ha insertado tiene el id: "+id,Toast.LENGTH_LONG).show();
        Cursor c = gj.getCursor(null,null,null);
        ad.changeCursor(c);
    }

}
