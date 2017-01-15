package com.example.nivaldogomesdasilvafilho.testemaps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.nivaldogomesdasilvafilho.testemaps.util.Cidade;
import com.inlocomedia.android.InLocoMedia;
import com.inlocomedia.android.InLocoMediaOptions;
import com.inlocomedia.android.ads.AdError;
import com.inlocomedia.android.ads.AdRequest;
import com.inlocomedia.android.ads.interstitial.InterstitialAd;
import com.inlocomedia.android.ads.interstitial.InterstitialAdListener;

public class CityInfoActivity extends AppCompatActivity {
    public static final String EXTRA_CIDADE_INFO =
            "helloandroid.EXTRA_CIDADE_INFO";
    public static final String ACAO_EXIBIR_CIDADE_INFO =
            "helloandroid.ACAO_EXIBIR_CIDADE_INFO";
    public static final String CATEGORIA_CIDADE_INFO =
            "helloandroid.CATEGORIA_CIDADE_INFO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_info);

        // In Loco Media SDK Init
        InLocoMediaOptions options = InLocoMediaOptions.getInstance(this);

        // The AppId you acquired in earlier steps
        options.setAdsKey("2082505fefc35e4b6bfcbf6e672c7537fcd80b3001bc7938405338724449df76");

        // Verbose mode flag, if this is set as true InLocoMedia SDK will let you know about errors on the Logcat
        options.setLogEnabled(true);

        // Development Devices set here are only going to receive test ads
        options.setDevelopmentDevices("DE010C8DE6BA76ABC563A40733BBE6");

        InLocoMedia.init(this, options);

        TextView nomeTextView =
                (TextView) findViewById(R.id.nomeCidadeTextView);
        TextView tempMaxTextView =
                (TextView) findViewById(R.id.tempMaxTextView);
        TextView tempMinTextView =
                (TextView) findViewById(R.id.tempMinTextView);
        TextView descTempoTextView =
                (TextView) findViewById(R.id.descTempoTextView);

        // Set valores na tela
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_CIDADE_INFO)) {
            Cidade cidade = (Cidade) intent.getExtras().getSerializable(EXTRA_CIDADE_INFO);
            nomeTextView.setText("Cidade: " + cidade.getNome());
            tempMaxTextView.setText("Temperatura Máxima: " + cidade.getTempMax() + "°C");
            tempMinTextView.setText("Temperatura Mínima: " + cidade.getTempMin() + "°C");
            descTempoTextView.setText("Descrição de tempo: " + cidade.getDescTempo());
        } else {
            nomeTextView.setText("O nome do usuário não foi informado");
        }
    }

    //Add ads quando o usuário voltar para lista de cidades
    @Override
    public void onBackPressed() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setInterstitialAdListener(new InterstitialAdListener() {

            @Override
            public void onAdReady(final InterstitialAd ad) {
                ad.show();
            }

            @Override
            public void onAdError(InterstitialAd ad, AdError error) {
                Log.w("InLocoMedia", "Your interstitial has failed with error: " + error);
            }
        });

        AdRequest adRequest = new AdRequest();
        adRequest.setAdUnitId("ee2b171039dad7451df6288c7fe843a1e68fcd5a486df7febbfde025d9eb76f8");
        interstitialAd.loadAd(adRequest);

        CityInfoActivity.this.finish();

        return;
    }


}
