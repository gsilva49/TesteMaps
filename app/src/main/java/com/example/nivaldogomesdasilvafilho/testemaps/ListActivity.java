package com.example.nivaldogomesdasilvafilho.testemaps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nivaldogomesdasilvafilho.testemaps.util.Cidade;
import com.example.nivaldogomesdasilvafilho.testemaps.util.Localizacao;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends android.app.ListActivity {

    public static final String EXTRA_CIDADE =
            "helloandroid.EXTRA_CIDADE";
    public static final String ACAO_EXIBIR_CIDADE =
            "helloandroid.ACAO_EXIBIR_CIDADE";
    public static final String CATEGORIA_CIDADE =
            "helloandroid.CATEGORIA_CIDADE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Tenta obter a localizacao da cidade
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_CIDADE)) {
            Localizacao location = (Localizacao) intent.getExtras().getSerializable(EXTRA_CIDADE);

            //Request - lista de cidades
            new WeatherTask().execute(location.getLatitudeString(), location.getLongitutudeString());
        }
        else{
            Toast.makeText(this, "Cidade não encontrada", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Cidade cidade = (Cidade) l.getAdapter().getItem(position);

        Intent intent = new Intent(this, CityInfoActivity.class);
        intent.putExtra(CityInfoActivity.EXTRA_CIDADE_INFO, cidade);
        startActivity(intent);
    }

    //Obter os dados das cidades através do API
    private class WeatherTask extends AsyncTask<String, Void, List<Cidade>> {
        ProgressDialog dialog;

        //Mostrar load
        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(ListActivity.this);
            dialog.setMessage("Aguarde");
            dialog.show();
        }

        //Listar as cidades na tela
        @Override
        protected void onPostExecute(List<Cidade> result) {
            if(result.size() > 0){
                ArrayAdapter<Cidade> adapter =
                        new ArrayAdapter<Cidade>(getBaseContext(),
                                android.R.layout.simple_list_item_1, result);
                setListAdapter(adapter);
            }
            dialog.dismiss();
        }

        //Obter as cidades
        @Override
        protected List<Cidade> doInBackground(String... params) {
            try {
                String lat = params[0];
                String lon = params[1];
                if(TextUtils.isEmpty(lat) || TextUtils.isEmpty(lon)){
                    return null;
                }
                // Resquest
                String urlWeather = "http://api.openweathermap.org/data/2.5/find?lat=" + lat + "&lon=" + lon + "&cnt=15&APPID=2112a270fb5b0467eb62cd4b0809fa0b";
                String url = Uri.parse(urlWeather).toString();
                String conteudo = HTTPUtils.acessar(url);

                // Respond
                JSONObject jsonObject = new JSONObject(conteudo);
                JSONArray resultados = jsonObject.getJSONArray("list");

                // Formata o resultado
                List<Cidade> cidades = new ArrayList<Cidade>();
                for (int i = 0; i < resultados.length(); i++) {
                    JSONObject cidade = resultados.getJSONObject(i);

                    Cidade objetoCidade = new Cidade();
                    objetoCidade.setNome(cidade. getString("name"));
                    objetoCidade.setId(cidade.getString("id"));
                    double tempMax = Double.valueOf(cidade.getJSONObject("main").getString("temp_max")) - 273.15;
                    double tempMin = Double.valueOf(cidade.getJSONObject("main").getString("temp_min")) - 273.15;
                    objetoCidade.setTempMin(String.format("%.1f", tempMin));
                    objetoCidade.setTempMax(String.format("%.1f", tempMax));
                    objetoCidade.setDescTempo(cidade.getJSONArray("weather").getJSONObject(0).getString("description"));
                    cidades.add(objetoCidade);
                }
                return cidades;


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }



    }
}
