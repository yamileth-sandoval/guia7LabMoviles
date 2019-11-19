package com.example.guia7labmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.guia7labmoviles.MainActivity.NAME_FILE;

public class configUsuario extends AppCompatActivity {
    private SharedPreferences configuracion;
    EditText edtUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_usuario);
        this.configuracion = getSharedPreferences(NAME_FILE, MODE_PRIVATE);
        this.edtUsuario=findViewById(R.id.edtUsuario);
        cargarConfiguracion();
    }

    private void cargarConfiguracion(){
        String nom=this.configuracion.getString("usuario","Anonimo");
        if(!nom.equals("Anonimo")){
            this.edtUsuario.setText(nom);
        }
    }

    public void clickGuardar(View v){
        if (this.configuracion != null){
            String nom=this.edtUsuario.getText().toString();
            if(!nom.equals("")){
                SharedPreferences.Editor editorConfiguracion = this.configuracion.edit();
                editorConfiguracion.putString("usuario",nom);
                editorConfiguracion.commit();
                Toast.makeText(this,"Usuario guardado con exito!",Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(this,"No puede guardar un campo vacio!",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
