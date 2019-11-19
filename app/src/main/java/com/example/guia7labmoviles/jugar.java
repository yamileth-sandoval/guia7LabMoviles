package com.example.guia7labmoviles;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import static com.example.guia7labmoviles.MainActivity.NAME_FILE;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class jugar extends AppCompatActivity {
    private SharedPreferences configuracion;
    private int numeroAleatorio=-1;
    private int numPuntuacion=0;
    TextView txtPuntuacionA;
    TextView usuarioJugar;
    EditText edtRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);
        this.configuracion = getSharedPreferences(NAME_FILE, MODE_PRIVATE);
        this.txtPuntuacionA=findViewById(R.id.txtPuntuacionA);
        this.edtRespuesta=findViewById(R.id.edtRespuesta);
        this.usuarioJugar=findViewById(R.id.usuarioJugar);
        cargarConfiguracion();
    }

    private void cargarConfiguracion(){
        this.numPuntuacion=this.configuracion.getInt("puntuacion",10);
        this.txtPuntuacionA.setText(Integer.toString(this.numPuntuacion));
        this.numeroAleatorio= this.configuracion.getInt("numAleatorio",-1);
        String nom=this.configuracion.getString("usuario","Anonimo");
        this.usuarioJugar.setText("Usuario: "+nom);
        if(this.numeroAleatorio==-1){//si dentra es porque NO hay un numero aleatorio
            generarAleatorio();
        }
    }
    private void generarAleatorio(){
        Random numAleatorio=new Random();
        this.numeroAleatorio=1+numAleatorio.nextInt(10);
        SharedPreferences.Editor editorConfigutacion=this.configuracion.edit();
        if(configuracion != null){
            editorConfigutacion.putInt("numAleatorio",this.numeroAleatorio);
            editorConfigutacion.commit();
        }
    }

    public void clickAdivinar(View v){
        int aux=this.numeroAleatorio;
        String res=this.edtRespuesta.getText().toString();
        if(!res.equals("")){
            SharedPreferences.Editor editorConfigutacion=this.configuracion.edit();
            if(res.equals(String.valueOf(this.numeroAleatorio))){
                this.numPuntuacion=this.numPuntuacion+10;
                generarAleatorio();
                editorConfigutacion.putInt("puntuacion",this.numPuntuacion);
                editorConfigutacion.commit();
                notificar(aux);
            }else {
                this.numPuntuacion=this.numPuntuacion-1;
                editorConfigutacion.putInt("puntuacion",this.numPuntuacion);
                editorConfigutacion.commit();
            }
            this.txtPuntuacionA.setText(Integer.toString(this.numPuntuacion));
            this.edtRespuesta.setText("");
        }
    }

    private void notificar(int aux){
        AlertDialog.Builder alerta=new AlertDialog.Builder(this);
        alerta.setTitle("Felicidades Adivinó!!!!");
        alerta.setMessage("El numero era: "+aux);
        alerta.setCancelable(false);
        alerta.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Intenta adivinar el nuevo numero!!",Toast.LENGTH_SHORT).show();
            }
        });
        alerta.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog alertaDialog=alerta.create();
        alertaDialog.show();
    }
}
