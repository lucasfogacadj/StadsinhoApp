package br.com.lucasfogaca.stadsinhoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;

public class add_trabalho extends AppCompatActivity {

    private EditText txtTitulo;
    private EditText txtDataE;
    private EditText txtDataC;
    private EditText txtAnotacoes;
    private HttpClient httpClient = HttpClientBuilder.create().build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_trabalho);

        txtTitulo = findViewById(R.id.txtTitulo);
        txtDataE = findViewById(R.id.txtDataE);
        txtDataC = findViewById(R.id.txtDataC);
        txtAnotacoes = findViewById(R.id.txtAnt);
    }

    public void save(View v) throws JSONException, IOException {
        HttpPost client = new HttpPost("http://192.168.1.100:8000/api/trabalhos");

        JSONObject obj = new JSONObject();

        obj.put("titulo", txtTitulo.getText());
        obj.put("idUsuario", 566917526);
        obj.put("dataEntrega", txtDataE.getText());
        obj.put("dataConcluida", txtDataC.getText());
        obj.put("anotacoes", txtAnotacoes.getText());

        client.addHeader("Content-Type", "application/json");
        client.addHeader("Accept", "application/json");
        StringEntity strObj = new StringEntity(obj.toString());

        client.setEntity(strObj);

        HttpResponse reponse = httpClient.execute(client);
    }
}
