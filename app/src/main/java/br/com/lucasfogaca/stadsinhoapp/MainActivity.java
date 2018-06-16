package br.com.lucasfogaca.stadsinhoapp;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpDelete;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;
import cz.msebera.android.httpclient.util.EntityUtils;


public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private HttpClient httpClient = HttpClientBuilder.create().build();
    private CustumAdapter ca;
    private ArrayList<Trabalho> trabalho = new ArrayList<Trabalho>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        listView = findViewById(R.id.listview);

        try {
            findAll();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        if (item.getItemId() == R.id.addTrabalho) {
            Intent intent = new Intent(this, add_trabalho.class);

            startActivity(intent);
            return true;
        }

        return false;
    }

    public void findAll() throws IOException, JSONException {
        HttpGet client = new HttpGet("http://192.168.1.100:8000/api/trabalhos/");
        client.addHeader("Content-Type", "application/json");

        HttpResponse response = httpClient.execute(client);

        String json = EntityUtils.toString(response.getEntity());

        JSONArray obj = new JSONArray(json);

        for (int i=0; i<obj.length(); i++){
            JSONObject data = obj.getJSONObject(i);
            trabalho.add(new Trabalho( data.getString("titulo"), data.getString("dataEntrega"), data.getString("dataConcluida"), data.getString("anotacoes"), data.getInt("idUsuario"), data.getInt("id")));
        }

        ca = new CustumAdapter(this, 0, trabalho);
        listView.setAdapter(ca);

    }

    public void remove(View view) throws IOException {
        View v =  (View)view.getParent();

        TextView txtId = (TextView) v.findViewById(R.id.txt_label_id);
        Integer id = Integer.parseInt(String.valueOf(txtId.getText()));

        HttpDelete client = new HttpDelete("http://192.168.1.100:8000/api/trabalhos/" + id);
        client.addHeader("Content-Type", "application/json");

        HttpResponse response = httpClient.execute(client);

        int statusCode = response.getStatusLine().getStatusCode();

        if(statusCode == 200){
            Toast.makeText(getApplicationContext(), "Deletado com sucesso", Toast.LENGTH_SHORT).show();
        }
        finish();

        startActivity(getIntent());
    }

    public void updateUi(ArrayList<Trabalho> itens) {
        ca.clear();

        if (itens != null) {
            for (Object object : itens) {
                ca.insert((Trabalho) object, ca.getCount());
            }
        }

        ca.notifyDataSetChanged();
    }

}
