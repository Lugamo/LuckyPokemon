package com.application.luckypokemon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.AsyncTask;
import android .app.ProgressDialog;
import android.util.Log;
import android.view.View;




public class MainActivity extends AppCompatActivity {


    public static final String URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/376.png";
    public static final String URL2 = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/698.png";
    private ImageView Aliado;
    private ImageView Enemigo;
    private Button Atacar;
    double DañoAliado,DañoEnemigo,VidaAliado=100.0,VidaEnemigo=100.0;
    private TextView Status;
    private TextView Status2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Aliado = (ImageView) findViewById(R.id.Aliado);
        Enemigo= (ImageView) findViewById(R.id.Enemigo);
        Atacar = (Button) findViewById(R.id.button);
        Status=(TextView) findViewById(R.id.textView2);
        Status2=(TextView) findViewById(R.id.textView3);

        CargaImagenes nuevaTarea = new CargaImagenes();
        nuevaTarea.execute(URL);
        CargaImagenes2 nuevaTarea2 = new CargaImagenes2();
        nuevaTarea2.execute(URL2);
        Atacar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DañoAliado=Math.random()*24;
                DañoEnemigo=Math.random()*24;
                VidaAliado=VidaAliado-DañoEnemigo;
                VidaEnemigo=VidaEnemigo-DañoAliado;
                String Estado=String.valueOf(VidaAliado);
                Status.setText("Vida Aliado: "+Estado);
                String Estado2=String.valueOf(VidaEnemigo);
                Status2.setText("Vida Enemigo: "+Estado2);
                if(VidaAliado<0.0){
                    Status.setText("Gana el pokemon Enemigo");
                    Status2.setText("Gana el pokemon Enemigo");
                }
                if(0.0>VidaEnemigo){
                    Status.setText("Gana el pokemon Aliado");
                    Status2.setText("Gana el pokemon Aliado");
                }


            }
        });

    }
    private class CargaImagenes extends AsyncTask<String, Void, Bitmap>{

        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Cargando Imagen");
            pDialog.setCancelable(true);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.show();

        }

        @Override
        protected Bitmap doInBackground(String... params) {
            // TODO Auto-generated method stub
            Log.i("doInBackground" , "Entra en doInBackground");
            String url = params[0];
            Bitmap imagen = descargarImagen(url);
            return imagen;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            Aliado.setImageBitmap(result);
            pDialog.dismiss();
        }

    }
    private class CargaImagenes2 extends AsyncTask<String, Void, Bitmap>{

        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Cargando Imagen");
            pDialog.setCancelable(true);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.show();

        }

        @Override
        protected Bitmap doInBackground(String... params) {
            // TODO Auto-generated method stub
            Log.i("doInBackground" , "Entra en doInBackground");
            String url = params[0];
            Bitmap imagen = descargarImagen(url);
            return imagen;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            Enemigo.setImageBitmap(result);
            pDialog.dismiss();
        }

    }
    private Bitmap descargarImagen (String imageHttpAddress){
        URL imageUrl = null;
        Bitmap imagen = null;
        try{
            imageUrl = new URL(imageHttpAddress);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();
            imagen = BitmapFactory.decodeStream(conn.getInputStream());
        }catch(IOException ex){
            ex.printStackTrace();
        }

        return imagen;
    }


}


