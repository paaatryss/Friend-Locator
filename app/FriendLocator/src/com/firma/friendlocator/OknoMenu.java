package com.firma.friendlocator;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.firma.friendlocator.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class OknoMenu extends MapActivity {
	public static MapView mapView;
	public static int i=1;

	private static final int latitudeE6 = 37985339;
	private static final int longitudeE6 = 23716735;
	double lat=0;
	double lon=0;
	
	double latitude;
	double longitude;
	
	public LocationManager locmgr = null;
	public TextView mytext;
	
	Boolean value1=true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		setContentView(R.layout.activity_okno_menu);
		
		mytext = (TextView) findViewById(R.id.mytext);
        
        //grab the location manager service
        locmgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
  
        mytext.setText("Oczekiwanie na lokalizacje");
        
		ServerConnector test = new ServerConnector("c576526171df6f92db16fc1c2cbf1dc0");
		ArrayList<Friend> friends = new ArrayList<Friend>();
		friends = test.GetFriends();
		Bundle extras = getIntent().getExtras();
	    if (extras == null) {
	    }
	    else{
	    	value1 = extras.getBoolean("Value1");
	    }
		mapView = (MapView) findViewById(R.id.map_view);
		mapView.setBuiltInZoomControls(true);
		
		Drawable drawable = this.getResources().getDrawable(R.drawable.google);
		List mapOverlays = mapView.getOverlays();
		Log.d("ilosc znajomych", Integer.toString(friends.size()));
		GeoPoint point10 = null;
		for(int i=0; i<friends.size(); i++)
		{
			Friend fr = friends.get(i);
			Log.d("latiutude", Integer.toString(fr.getLatitude()));
			CustomItemizedOverlay itemizedOverlay = new CustomItemizedOverlay(
					drawable, this);
			GeoPoint point = new GeoPoint(fr.getLatitude(), fr.getLongitude());
			OverlayItem overlayitem = new OverlayItem(point, fr.getName(),
					fr.getLogin());
			itemizedOverlay.addOverlay(overlayitem);
			mapOverlays.add(itemizedOverlay);
			MapController mapController = mapView.getController();
			mapController.animateTo(point);
			if(i==1){
				Log.d("wlaz³em", Integer.toString(fr.getLatitude()));
				point10 = new GeoPoint(fr.getLatitude(), fr.getLongitude());
			}
		}
		CustomItemizedOverlay itemizedOverlay = new CustomItemizedOverlay(
				drawable, this);
		CustomItemizedOverlay itemizedOverlay2 = new CustomItemizedOverlay(
				drawable, this);

		GeoPoint point = new GeoPoint(latitudeE6, longitudeE6);
		GeoPoint point2 = new GeoPoint(40453046, -3688445);

		OverlayItem overlayitem = new OverlayItem(point, "Hello",
				"I'm in Athens, Greece!");
		OverlayItem overlayitem2 = new OverlayItem(point2, "Hello",
				"Hala Madrid");

		itemizedOverlay.addOverlay(overlayitem);
		itemizedOverlay2.addOverlay(overlayitem2);
		
		mapOverlays.add(itemizedOverlay);
		mapOverlays.add(itemizedOverlay2);

		MapController mapController = mapView.getController();

		mapController.animateTo(point2);
		mapController.setZoom(20);
		//mapView.setSatellite(true);
        mapView.setStreetView(!value1);
        mapView.setSatellite(value1);
        mapView.invalidate();
	}

	protected boolean isRouteDisplayed() {
		return false;
	}
	public void menu(View view) {
	    Intent intent = new Intent(this, OknoMenuGlowne.class);
	    startActivity(intent);
	}
	
	//Start a location listener
    LocationListener onLocationChange=new LocationListener() {
        public void onLocationChanged(Location loc) {
            //sets and displays the lat/long when a location is provided
            String latlong = "Moja Lokacja: " + loc.getLatitude() + ", " + loc.getLongitude();   
            mytext.setText(latlong);
            
            latitude = loc.getLatitude();
            longitude = loc.getLongitude();
        }
         
        public void onProviderDisabled(String provider) {
        // required for interface, not used
        }
         
        public void onProviderEnabled(String provider) {
        // required for interface, not used
        }
         
        public void onStatusChanged(String provider, int status,
        Bundle extras) {
        // required for interface, not used
        }
    };

    //pauses listener while app is inactive
    @Override
    public void onPause() {
        super.onPause();
        locmgr.removeUpdates(onLocationChange);
    }
    
    //reactivates listener when app is resumed
    @Override
    public void onResume() {
        super.onResume();
        locmgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,10000.0f,onLocationChange);
    }
}
