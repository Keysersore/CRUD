package com.example.crud;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class BeginJourney extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_LOCATION = 1;
    Button button;
    TextView textView;
    LocationManager locationManager;
    String lattitude, longitude;
   static  GeocodingLocation locationAddress;
    private Button buttonz, btnErase,b4;
    private TextView textViewz;
    private EditText editText;
    static String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin_journey);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        Intent intent = getIntent();
        username   = intent.getStringExtra("Username");

       // Toast.makeText(this,     username , Toast.LENGTH_SHORT).show();


        textView = (TextView) findViewById(R.id.text_location);
        button = (Button) findViewById(R.id.button_location);
       b4 = (Button) findViewById(R.id.button4);
        button.setOnClickListener(this);


        textViewz = (TextView) findViewById(R.id.tv);
        btnErase = findViewById(R.id.btnErase);
        editText = (EditText) findViewById(R.id.etAdd);

        btnErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });

        buttonz = (Button) findViewById(R.id.btn);
        buttonz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {


                String address = editText.getText().toString();

             locationAddress = new GeocodingLocation();

                locationAddress.getAddressFromLocation(address,
                        getApplicationContext(), new GeocoderHandler());

            }
        });
       b4.setEnabled(false);
      b4.setVisibility(View.INVISIBLE);
       button.setVisibility(View.INVISIBLE);
        btnErase.setVisibility(View.INVISIBLE);
      textViewz.setVisibility(View.INVISIBLE);
    }
    public void go(View view) {
        try{
            int comma = locationAddress.co().toString().indexOf(",");
            int last =locationAddress.co().toString().length();
           /*Intent intent = new Intent(this,Main2Activity.class);
           intent.putExtra("latitude",locationAddress.co().substring(0,comma));
           intent.putExtra("longitude",locationAddress.co().substring(comma+1,last));
            intent.putExtra("Username",username);
            intent.putExtra("destination",editText.getText().toString());
            startActivity(intent);*/
           // Toast.makeText(this, "comaP"+String.valueOf(comma)+" LAST"+String.valueOf(last), Toast.LENGTH_SHORT).show();
          //  Toast.makeText(this, "Sucessc", Toast.LENGTH_SHORT).show();
        }
        catch(Exception a){
            Toast.makeText(this, a.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    void h(){
        try{
            int comma = locationAddress.co().toString().indexOf(",");
            int last =locationAddress.co().toString().length();
            /*Intent intent = new Intent(this,Main2Activity.class);
            intent.putExtra("latitude",locationAddress.co().substring(0,comma));
            intent.putExtra("longitude",locationAddress.co().substring(comma+1,last));
           intent.putExtra("Username",username);
            intent.putExtra("destination",editText.getText().toString());
            startActivity(intent);*/
            // Toast.makeText(this, "comaP"+String.valueOf(comma)+" LAST"+String.valueOf(last), Toast.LENGTH_SHORT).show();
          //  Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }
        catch(Exception a){
            Toast.makeText(this, a.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    public class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    Log.d("latttt",locationAddress);
                    b4.setEnabled(true);
                    h();
                    break;
                default:
                    locationAddress = null;
                    b4.setEnabled(false);
            }
            textViewz.setText(locationAddress);
         //   Toast.makeText(BeginJourney.this, , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        }
    }
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(BeginJourney.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (BeginJourney.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(BeginJourney.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Location location2 = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            b4.setEnabled(false);
            if (location != null) {
                double latti = location.getLatitude();
                double longi = location.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                textViewz.setText("Your current location is" + "\n" + "Lattitude = " + lattitude
                        + "\n" + "Longitude = " + longitude);

                b4.setEnabled(false);

            } else if (location1 != null) {
                double latti = location1.getLatitude();
                double longi = location1.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                textViewz.setText("Your current location is" + "\n" + "Lattitude = " + lattitude
                        + "\n" + "Longitude = " + longitude);
                b4.setEnabled(false);


            } else if (location2 != null) {
                double latti = location2.getLatitude();
                double longi = location2.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                textViewz.setText("Your current location is" + "\n" + "Lattitude = " + lattitude
                        + "\n" + "Longitude = " + longitude);
                b4.setEnabled(false);

            } else {

                Toast.makeText(this, "Unble to Trace your location", Toast.LENGTH_SHORT).show();

            }
        }
    }

    protected void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
}