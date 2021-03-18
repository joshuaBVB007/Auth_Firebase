package com.example.auth_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    /*Pasos para crear una autenticacion en firebase--Realtime Database
    1-crear el proyecto en firebase console
    2-Aqui en android debes ir a la pestaña superior en tools-->firebase-->te abre un asistente en la parte
    derecha-->autenticacion usando un custom system autenticado.
    3-agregar las dependencias del sdk en la parte de abajo de tu misma pantalla derecha.
    4-volvemos al firebase console y entramos en autenticacion y clicamos en comenzar en el servidor
    5-clicamos en correo electronico y lo habilitamos
    6-creamos 2 textview aqui en nuestra aplicacion que seran los campos correo y contraseña
    7-recoger esas valores una vez se ejecute un metodo y validar que no sean nulos
    7-crear una instancia de firebase ej: static FirebaseAuth instancia_Firebase;
    8-en el metodo de Dar_alta_usuario_en_firebase utilizar esa instancia y aplicar el metodo
    createuserwithEmailandPassword y el metodo oncompleteLister para que nos avise que se han cumplido
    nuestro ingreso a realtime Database.

    A tener en cuenta:
    debes usar un SDK no inferior a 28
    */
    static FirebaseAuth instancia_Firebase;
    EditText correo;
    EditText contraseña;
    String receptor_datos_correo;
    String receptor_datos_contraseña;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        correo=findViewById(R.id.correo);
        contraseña=findViewById(R.id.password);
        send=findViewById(R.id.send);
        instancia_Firebase=FirebaseAuth.getInstance();


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                receptor_datos_correo=correo.getText().toString();
                receptor_datos_contraseña=contraseña.getText().toString();
                if(receptor_datos_correo!=null && receptor_datos_contraseña!=null){
                    Dar_Alta_Usuario_en_Firebase(receptor_datos_correo,receptor_datos_contraseña);
                }else{
                    Toast.makeText(MainActivity.this, "Datos incorrectos",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void Dar_Alta_Usuario_en_Firebase(String a,String b){
        instancia_Firebase.createUserWithEmailAndPassword(a,b)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Usuario creado",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Error al crear usuario",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}