package com.example.nivaldogomesdasilvafilho.testemaps;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nivaldogomesdasilvafilho.testemaps.util.Localizacao;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Double longitude, latitude;
    private Button b_get;
    private TrackGPS gps;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient.Builder client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        longitude = -34.8716389;
        latitude = -8.063122;

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng pos = new LatLng(latitude, longitude);
        final Marker marcador = mMap.addMarker(new MarkerOptions().position(pos).title(
                pos.latitude + " : " + pos.longitude));
        marcador.setDraggable(true);
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(pos));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 15));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        //Quando clicar no botão MyLocation
        mMap.setOnMyLocationButtonClickListener(
                new GoogleMap.OnMyLocationButtonClickListener() {
                    @Override
                    public boolean onMyLocationButtonClick() {

                        //com.google.android.gms.location.FusedLocationProviderApi.

                        gps = new TrackGPS(MainActivity.this);

                        if (gps.canGetLocation()) {
                            //add um marcador no mapa
                            addMarker(mMap, gps.latitude, gps.longitude);

                        } else {
                            //Pedir para ativar o GPS
                            gps.showSettingsAlert();
                        }

                        return true;
                    }
                }

        );

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {
                //Add um marcador no mapa
                addMarker(mMap, latLng.latitude, latLng.longitude);
            }
        });
    }

    // Quando o usuário clicar no botão buscar
    public void buscar(View view) {
        Localizacao localizacao = new Localizacao(latitude, longitude);
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra(ListActivity.EXTRA_CIDADE, localizacao);
        startActivity(intent);
    }

    // Add um marcador no mapa
    public void addMarker(GoogleMap googleMap, double latitude, double longitude){
        // Cria um marcador
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(latitude, longitude));
        markerOptions.title(latitude + " : " + longitude);
        googleMap.clear();
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude)));
        googleMap.addMarker(markerOptions);

        // Set as variavéis globais de loc
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
