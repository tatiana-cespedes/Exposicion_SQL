package com.cespedes.tatiana.exposicion_sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText edit1, edit2, edit3;
    Button beliminar, bguardar, bmodificar, blimpiar, bconsultar;

    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setContentView(R.layout.activity_main);

        edit1 = (EditText) findViewById(R.id.Edi1);
        edit2 = (EditText) findViewById(R.id.Edi2);
        edit3 = (EditText) findViewById(R.id.Edi3);
        beliminar = (Button) findViewById(R.id.beliminar);
        bconsultar = (Button) findViewById(R.id.bconsultar);
        bguardar = (Button) findViewById(R.id.bguardar);
        blimpiar = (Button) findViewById(R.id.blimpiar);
        bmodificar = (Button) findViewById(R.id.bmodificar);
    }

    public void bguardar(View view){
        BaseDeDatos estudiante= new BaseDeDatos(this, "basededatos", null,1);
        SQLiteDatabase bd= estudiante.getWritableDatabase();

        String nombre = edit1.getText().toString();
        String identificacion = edit2.getText().toString();
        String nota= edit3.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("identificacion", identificacion);
        registro.put("nombre" , nombre);
        registro.put("nota", nota);

        bd.insert("estudiantes", null, registro);
        bd.close();//cerrar para que guarde

        edit1.setText("");
        edit2.setText("");
        edit3.setText("");

        Toast.makeText(this, "se guardaron", Toast.LENGTH_SHORT).show();

    }

    public void consultar (View view){
        BaseDeDatos estudiantes = new BaseDeDatos(this, "basededatos", null, 1);
        SQLiteDatabase bd = estudiantes.getWritableDatabase();

        String identificacion = edit2.getText().toString();

        c= bd.rawQuery("select nombre, nota from estudiantes where identificacion="+ identificacion, null);

        if(c.moveToFirst()==true){
            edit1.setText(c.getString(0));
            edit3.setText(c.getString(1));


        }else{
            Toast.makeText(this, "No existe estudiante", Toast.LENGTH_SHORT).show();

        }
        bd.close();
    }


    public void modificar (View view){
        BaseDeDatos estudiante = new BaseDeDatos(this, "basededatos" , null, 1);
        SQLiteDatabase bd = estudiante.getWritableDatabase();

        String identificacion = edit2.getText().toString();
        String nombre = edit1.getText().toString();
        String nota = edit3.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("nota", nota);

        int cant = bd.update("estudiantes", registro, "identificacion=" + identificacion, null);
        bd.close();
        if(cant == 1) {
            Toast.makeText(this, "se modificaron", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "no existe estudiante", Toast.LENGTH_SHORT).show();

        }
    }

    public void eliminar (View v){
        BaseDeDatos admin = new BaseDeDatos(this, "basededatos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String identificacion = edit2.getText().toString();
        int cant = bd.delete("estudiantes", "identificacion=" + identificacion, null);
        bd.close();
        edit2.setText("");
        edit1.setText("");
        edit3.setText("");


        if(cant == 1) {
            Toast.makeText(this, "se borró", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "no existe estudiante", Toast.LENGTH_SHORT).show();

        }


    }



}
