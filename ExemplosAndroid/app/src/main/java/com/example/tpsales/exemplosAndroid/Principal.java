package com.example.tpsales.exemplosAndroid;

import android.*;
import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

public class Principal extends AppCompatActivity {

    private static final int REQUEST_PER = 127;
    private static final int REQUEST_FOTO = 128;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        if (!existePermissaoMapa()){
            solicitarPermissaoMapa();
        }
        if(!existePermissaoFoto()){
            solicitarPermissaoFoto();
        }

    }

    public void acessarMapa(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setMessage(Html.fromHtml("É necessário <b>permissão</b> para acessar sua localização."));
        AlertDialog dialog = builder.create();
        dialog.setIcon(R.drawable.question);
        dialog.setTitle("Permitir localização?");
        dialog.show();
    }

    private boolean existePermissaoMapa() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                ) {
            return false;
        }else{
            return true;
        }
    }

    private boolean existePermissaoFoto(){
        if( ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ){
            return false;
        }else{
            return true;
        }
    }

    private void solicitarPermissaoMapa() {
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PER);
    }

    private void solicitarPermissaoFoto() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_FOTO);
    }

    public void acessarFoto(View view){
        Intent i = new Intent(this, FotoActivity.class);
        startActivity(i);
    }
}
