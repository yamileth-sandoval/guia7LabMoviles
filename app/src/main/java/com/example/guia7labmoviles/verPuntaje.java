package com.example.guia7labmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.guia7labmoviles.MainActivity.NAME_FILE;

public class verPuntaje extends AppCompatActivity {
    private SharedPreferences configuracion;
    TextView txtUsuario;
    TextView txtPuntaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_puntaje);

        this.configuracion=this.getSharedPreferences(NAME_FILE, MODE_PRIVATE);

        this.txtUsuario=findViewById(R.id.txtUsuario);
        this.txtPuntaje=findViewById(R.id.txtPuntaje);
        cargarValores();
    }

    private void cargarValores(){
        int num= this.configuracion.getInt("numAleatorio",-1);
        if(num!=-1){
            this.txtUsuario.setText("Usuario: "+this.configuracion.getString("usuario","Anonimo"));
            this.txtPuntaje.setText(Integer.toString(this.configuracion.getInt("puntuacion",10)));
        }
    }
}
