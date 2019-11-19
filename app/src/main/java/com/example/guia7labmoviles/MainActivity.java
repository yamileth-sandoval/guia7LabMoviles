package com.example.guia7labmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static final String NAME_FILE= "Configuracion";
    private SharedPreferences configuracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.configuracion=this.getSharedPreferences(NAME_FILE, MODE_PRIVATE);
        cargarPuntuacion();
    }

    public void clickJugar(View v){
        Intent Jugar=new Intent(this, jugar.class);
        startActivity(Jugar);
    }

    public void clickVerPuntuacion(View v){
        Intent puntuacion=new Intent(this,verPuntaje.class);
        startActivity(puntuacion);
    }

    public void clickVerRespuestaCorrecta(View v){
        int config= this.configuracion.getInt("numAleatorio",-1);
        if(config==-1){
            Toast.makeText(this,"No hay numero Aleatorio registrado",Toast.LENGTH_SHORT).show();
        }else {
            Intent respuesta=new Intent(this,verRespuestaCorrecta.class);
            startActivity(respuesta);
        }
    }
    public void clickMisDatos(View v){
        Intent misdatos=new Intent(this, misDatos.class);
        startActivity(misdatos);
    }

    private void cargarPuntuacion(){
        int puntuacion=this.configuracion.getInt("puntuacion",10);
        String usu=this.configuracion.getString("usuario","Anonimo");
        int num=this.configuracion.getInt("numAleatorio",-1);
        if(puntuacion==10){
            SharedPreferences.Editor editorConfigutacion=this.configuracion.edit();
            if(num==-1){
                Random numAleatorio=new Random();
                int nuevo=1+numAleatorio.nextInt(10);
                editorConfigutacion.putInt("numAleatorio",nuevo);
            }
            if(configuracion != null){
                editorConfigutacion.putInt("numAleatorio",puntuacion);
                if(usu.equals("Anonimo")){
                    editorConfigutacion.putString("usuario",usu);
                }
                editorConfigutacion.commit();
            }
        }

    }

    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        crearMenu(menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        this.ItemSelected(item);
        return true;
    }
    private void ItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 0:
                Intent frmConfi=new Intent(this,configUsuario.class);
                startActivity(frmConfi);
                break;
        }
    }
    private void crearMenu(Menu menu){
        MenuItem item=menu.add(0,0,0,"Configurar Usuario");
        item.setAlphabeticShortcut('C');
    }
}
