package com.example.dell.smartfixapp;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, View.OnClickListener{

    private GoogleMap mMap;

    private GoogleApiClient client;
    private LocationRequest locationRequest;
    private Location lastlocation;
    private Marker currentLocationmMarker;
    double latitude,longitude;
    public static final int REQUEST_LOCATION_CODE = 99;

    private Spinner services;
    private Button conform;

    Dialog MyDialog;
    Button hello,close;

    DatabaseReference query;
    List<user> arrlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        init();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkLocationPermission();

        }




        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }




    void init()
    {
        services = (Spinner)findViewById(R.id.map_services);
        conform = (Button)findViewById(R.id.map_btn);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(MapsActivity.this,R.array.services,R.layout.spinner_item);
        services.setAdapter(adapter);
        query= FirebaseDatabase.getInstance().getReference("user");

        conform.setOnClickListener(this);
        arrlist=new ArrayList<>();
        conform.setOnClickListener(this);

    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode)
        {
            case REQUEST_LOCATION_CODE:
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) !=  PackageManager.PERMISSION_GRANTED)
                    {
                        if(client == null)
                        {
                            bulidGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else
                {
                    Toast.makeText(this,"Permission Denied" , Toast.LENGTH_LONG).show();
                }

        }

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

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            bulidGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }




    }

    protected synchronized void bulidGoogleApiClient() {
        client = new GoogleApiClient
                .Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        client.connect();

    }





    @Override
    public void onLocationChanged(Location location) {

        latitude = location.getLatitude();
        longitude = location.getLongitude();
        lastlocation = location;
        if(currentLocationmMarker != null)
        {
            currentLocationmMarker.remove();

        }

        Log.d("lat = ",""+latitude);

        LatLng latLng = new LatLng(location.getLatitude() , location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.draggable(true);
        markerOptions.title("Your Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        currentLocationmMarker = mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(15));

        if(client != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(client,this);
            //LocationServices.getFusedLocationProviderClient(this);
        }


    }



    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.map_btn)
        {
            //temp
            MarkerOptions s=new MarkerOptions();

            double k=0.002111;

            LatLng newLL=new LatLng(latitude,longitude-k);
            s.position(newLL);
            s.title("Result");
            s.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            mMap.addMarker(s);


            mMap.animateCamera(CameraUpdateFactory.newLatLng(newLL));
            MyCustomAlertDialog();
            Toast.makeText(this,"Comform enter",Toast.LENGTH_SHORT).show();



            for(int i=0; i<arrlist.size();i++){
                if(arrlist.get(i).getUsertype().equals("technician")){
                    double ll=arrlist.get(i).getLongitude();
                    double la=arrlist.get(i).getLattitude();


                    MarkerOptions ss=new MarkerOptions();

                    double kk=0.002111;

                    LatLng newLLL=new LatLng(ll,la-kk);
                    ss.position(newLLL);
                    ss.title("Result");
                    ss.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    mMap.addMarker(ss);


                    //mMap.animateCamera(CameraUpdateFactory.newLatLng(newLL));


                }

            }



        }



    }


    public void MyCustomAlertDialog(){
        MyDialog = new Dialog(MapsActivity.this);
        MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        MyDialog.setContentView(R.layout.customdialog);
        MyDialog.setTitle("My Custom Dialog");



        //rertreive the name
//        final TextView nameToChange = (TextView) findViewById(R.id.nam);
        //       nameToChange.setText("write the name");

        //retreive id
        //      final TextView idToChange = (TextView) findViewById(R.id.nam);
        //     idToChange.setText("write the name");


        double longitudee; double lattitude;       // the long and the latt of the tech , shahzad will provide me
      /*  DatabaseReference refbase=FirebaseDatabase.getInstance().getReference("item1");

        refbase.addChildEventListener(new ChildEventListener() {
            @Override

            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //String value=dataSnapshot.getValue(String.class);
String name;
                String key=dataSnapshot.getKey();
                if(key.equalsIgnoreCase("sale_price"))
                {
                    name=dataSnapshot.getValue(String.class);
                   // obj.setSale_price(price);
                    //menu.add(Integer.toString(obj.getSale_price()));
                    // keys.add(key);
                    // textView.setText("price"+price);
                    Log.d("item id ",snapshot.child("item_id").getValue().toString());

                }

*/
        //arrlist.clear();

        final ArrayList<String> a=new ArrayList<>();
        //////////////////
        FirebaseDatabase.getInstance().getReference("user")
                .addListenerForSingleValueEvent(new ValueEventListener() {


                    int i=0;
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            i++;

                            //arrlist.add(use);
                            //  DataSnapshot snapshot=null;
                            // dataSnapshot.getChildren();
                            //user userr = snapshot.getValue(user.class);
                            //System.out.println(userr.email);
                            String x =snapshot.child("name").getValue().toString();
                            String c=snapshot.child("email").getValue().toString();
                            double longi=Double.parseDouble(snapshot.child("longitude").getValue().toString());
                            double latt=Double.parseDouble(snapshot.child("lattitude").getValue().toString());
                            String utype =snapshot.child("usertype").getValue().toString();


                            user u=new user(c,latt,longi,x,utype);
                            arrlist.add(u);

                            //                            String in=snapshot.child("i").getValue().toString();


                            Toast.makeText(getApplicationContext(),c+" "+longi+" "+latt+" "+i,Toast.LENGTH_SHORT).show();
                            //  Toast.makeText(getApplicationContext(),"sadjsakdn",Toast.LENGTH_SHORT).show();

                            //        User user = snapshot.getValue(User.class);
                            //System.out.println(user.email);


                        }

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),"errorrrr",Toast.LENGTH_SHORT).show();
                    }
                });


//user u=arrlist.get(1);
        //String c=u.getusertye;
        //if(c==user){
        // u.getlongitude;
        //u.getlattitude;
        //set the longitude and lattitude in maps
        // }


        hello = (Button)MyDialog.findViewById(R.id.hello);
        close = (Button)MyDialog.findViewById(R.id.close);

        hello.setEnabled(true);
        close.setEnabled(true);

        hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //Toast.makeText(getApplicationContext(), "Hello, I'm Custom Alert Dialog", Toast.LENGTH_LONG).show();
                Intent intent;
                intent = new Intent(MapsActivity.this,orderPlaced.class);
                startActivity(intent);
                String nam=arrlist.get(1).getName();
                Toast.makeText(getApplicationContext(),nam,Toast.LENGTH_SHORT).show();

            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog.cancel();
            }
        });

        MyDialog.show();
    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
        }
    }

    public boolean checkLocationPermission()
    {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)  != PackageManager.PERMISSION_GRANTED )
        {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_LOCATION_CODE);
            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_LOCATION_CODE);
            }
            return false;

        }
        else
            return true;
    }





    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
