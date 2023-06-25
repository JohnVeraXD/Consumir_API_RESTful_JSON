package com.example.consumir_api_restful_json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    TextView lblLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Sacar la referencia
        lblLista = (TextView) findViewById(R.id.lblLista);

        //Hacemos uan peticion get al servidor
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(
                "https://dummyjson.com/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET") ;

    }


    @Override
    public void processFinish(String result) throws JSONException {
        String ListaUsuarios = "";

        JSONObject jsonObjecto = new JSONObject(result);
        JSONArray jsonLisUsers = jsonObjecto.getJSONArray("users");

        //Parceamos los datos
        for (int i=0;i< jsonLisUsers.length();i++){
            JSONObject usuarios = jsonLisUsers.getJSONObject(i);
            ListaUsuarios = ListaUsuarios + "\n" + "    " +
                    usuarios.getString("id").toString()+ ", " +
                    usuarios.getString("firstName").toString()+ ", "+
                    usuarios.getString("age").toString()+", "+
                    usuarios.getString("email").toString();
        }

        //Mostramos en la actividad
        lblLista.setText(ListaUsuarios);

    }
}