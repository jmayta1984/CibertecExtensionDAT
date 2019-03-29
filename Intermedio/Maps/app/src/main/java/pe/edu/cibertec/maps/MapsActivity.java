package pe.edu.cibertec.maps;

import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;


    ArrayList<Location> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        loadItems();
    }

    private void loadItems() {

        items = new ArrayList<>();

        Location locationCibSanIsidro, locationCibMiraflores;

        locationCibSanIsidro = new Location("Sede San Isidro",
                "Cursos de extensión profesional",
                "peru",
                -12.0860428,
                -77.0661857);

        items.add(locationCibSanIsidro);

        locationCibMiraflores = new Location("Sede Miraflores",
                "Cursos de carreras técnicas",
                "peru",

                -12.1222595,
                -77.0304797);

        items.add(locationCibMiraflores);


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

        CustomInfoWindowAdapter adapter = new CustomInfoWindowAdapter(this);
        mMap.setInfoWindowAdapter(adapter);

        mMap.setOnInfoWindowClickListener(this);


        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                LatLng sede = new LatLng(
                        items.get(0).getLatitude(),
                        items.get(0).getLongitude());



                mMap.addMarker(new MarkerOptions()
                        .position(sede)
                        .title(items.get(0).getTitle())
                        .snippet(items.get(0).getDescription())
                );

                mMap.animateCamera(CameraUpdateFactory.newLatLng(sede));
/*
                LatLng cibertecSanIsidro = new LatLng(-12.0860428, -77.0661857);

                mMap.addMarker(new MarkerOptions()
                        .position(cibertecSanIsidro)
                        .title("Cibertec San Isidro")
                        .snippet("Calle Marconi 200"));

                LatLngBounds cibertec = new LatLngBounds.Builder()
                        .include(cibertecSanIsidro)
                        .include(cibertecMiraflores)
                        .build();

                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(cibertec, 20));
                */
                //  .newLatLng(cibertecMiraflores)
                //.newLatLngZoom(cibertecMiraflores, 10);
                //.newLatLngZoom(cibertec.getCenter(), 15));
            }
        });

        // Add a marker in Sydney and move the camera





        /*
        Polygon polygon = mMap.addPolygon(new PolygonOptions()
                .add(cibertecMiraflores, cibertecSanIsidro)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));
        */
        /*
        mMap.addPolyline(new PolylineOptions()
                .add(cibertecMiraflores,cibertecSanIsidro)
                .width(5)
                .color(Color.GREEN)
        );
        */

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Info window clicked",
                Toast.LENGTH_SHORT).show();
    }
}
