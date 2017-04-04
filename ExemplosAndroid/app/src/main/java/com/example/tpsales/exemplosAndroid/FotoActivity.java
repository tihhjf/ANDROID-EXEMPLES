package com.example.tpsales.exemplosAndroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FotoActivity extends AppCompatActivity {

    private static final int CAMERA_PIC_REQUEST = 1;
    private static final int GALERIA_PIC_REQUEST = 2;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);
    }

    public void startCamera(){
        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera_intent, CAMERA_PIC_REQUEST);
    }

    public void startGaleria(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), GALERIA_PIC_REQUEST);
    }

    public void selecionarModo(View view) {
        final CharSequence[] items = { "Tirar foto", "Galeria" };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Adicionar Foto");
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Tirar foto")) {
                    startCamera();
                } else if (items[item].equals("Galeria")) {
                    startGaleria();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        switch(requestCode){
            case CAMERA_PIC_REQUEST:
                if(resultCode==RESULT_OK){
                    Bitmap imagem = (Bitmap) data.getExtras().get("data");
                    imageView.setImageBitmap(imagem);
                }
            case GALERIA_PIC_REQUEST:
                    if(resultCode==RESULT_OK){
                        Uri uriImagemGaleria = data.getData();
                        imageView.setImageURI(uriImagemGaleria);
                    }
        }
    }

}
