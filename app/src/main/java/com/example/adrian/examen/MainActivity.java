package com.example.adrian.examen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

  Button guardar, foto;
    private final String ruta_fotos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/misfotos/";
    private File file = new File(ruta_fotos);
    Bitmap bitmap;
    ImageView imageView;
    ScaleGestureDetector SG;
    Matrix matrix;
    float SF=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foto=(Button)findViewById(R.id.boto);
        imageView=(ImageView)findViewById(R.id.imageView);



        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,1);


            }
        });


        matrix= new Matrix();
        SG = new ScaleGestureDetector(this,new Sca());
                    //
                     // Uri uri = Uri.fromFile(  );
                      //Abre la camara para tomar la foto
                      //Guarda imagen
                      //cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                      //Retorna a la actividad
        // startActivityForResult(cameraIntent, 0);

    }


    private class  Sca extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            SF=detector.getScaleFactor()*SF;
            SF=Math.max(0.1f,Math.min(SF,0.5f));
            matrix.setScale(SF,SF);
            imageView.setImageMatrix(matrix);

            return  true;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode==1&& resultCode==RESULT_OK)
        {
            bitmap=(Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        SG.onTouchEvent(event);
        return true;
    }
}
