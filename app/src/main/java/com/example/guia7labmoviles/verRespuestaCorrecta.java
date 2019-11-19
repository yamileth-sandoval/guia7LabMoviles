package com.example.guia7labmoviles;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import static com.example.guia7labmoviles.MainActivity.NAME_FILE;

public class verRespuestaCorrecta extends AppCompatActivity {
    TextView labelRespuestaCorrecta;
    private SharedPreferences configuracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_respuesta_correcta);

        this.configuracion=this.getSharedPreferences(NAME_FILE, MODE_PRIVATE);
        this.labelRespuestaCorrecta=findViewById(R.id.labelRespuestaCorrecta);
        cargarValores();
    }

    private void cargarValores(){
        int num= this.configuracion.getInt("numAleatorio",-1);
        if(num!=-1){
            this.labelRespuestaCorrecta.setText(Integer.toString(num));
        }
    }
}
