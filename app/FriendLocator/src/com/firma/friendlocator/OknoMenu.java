package com.firma.friendlocator;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

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
	
	Boolean value1=false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		setContentView(R.layout.activity_okno_menu);
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
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		 Criteria criteria = new Criteria();
		 String bestProvider = locationManager.getBestProvider(criteria, false);
		// Location location = locationManager.getLastKnownLocation(bestProvider);
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
		CustomItemizedOverlay itemizedOverlay3 = new CustomItemizedOverlay(
				drawable, this);

		GeoPoint point = new GeoPoint(latitudeE6, longitudeE6);
		GeoPoint point2 = new GeoPoint(latitudeE6+1000, longitudeE6+1000);
		GeoPoint point3 = new GeoPoint((int)lat, (int)lon);
		OverlayItem overlayitem = new OverlayItem(point, "Hello",
				"I'm in Athens, Greece!");
		OverlayItem overlayitem2 = new OverlayItem(point2, "siemanko",
				"2 znajomy");
		OverlayItem overlayitem3 = new OverlayItem(point3, "siemanko",
				"3 znajomy");

		itemizedOverlay.addOverlay(overlayitem);
		itemizedOverlay2.addOverlay(overlayitem2);
		itemizedOverlay3.addOverlay(overlayitem3);
		mapOverlays.add(itemizedOverlay);
		mapOverlays.add(itemizedOverlay2);
		mapOverlays.add(itemizedOverlay3);

		MapController mapController = mapView.getController();

		mapController.animateTo(point10);
		mapController.setZoom(10);
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

}
