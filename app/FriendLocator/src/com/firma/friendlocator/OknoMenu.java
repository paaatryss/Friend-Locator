package com.firma.friendlocator;

import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
	
	Boolean value1=false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_okno_menu);
		Bundle extras = getIntent().getExtras();
	    if (extras == null) {
	    }
	    else{
	    	value1 = extras.getBoolean("Value1");
	    }
		mapView = (MapView) findViewById(R.id.map_view);
		mapView.setBuiltInZoomControls(true);

		List mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.google);
		CustomItemizedOverlay itemizedOverlay = new CustomItemizedOverlay(
				drawable, this);
		CustomItemizedOverlay itemizedOverlay2 = new CustomItemizedOverlay(
				drawable, this);

		GeoPoint point = new GeoPoint(latitudeE6, longitudeE6);
		GeoPoint point2 = new GeoPoint(latitudeE6+1000, longitudeE6+1000);
		OverlayItem overlayitem = new OverlayItem(point, "Hello",
				"I'm in Athens, Greece!");
		OverlayItem overlayitem2 = new OverlayItem(point2, "siemanko",
				"2 znajomy");

		itemizedOverlay.addOverlay(overlayitem);
		itemizedOverlay2.addOverlay(overlayitem2);
		mapOverlays.add(itemizedOverlay);
		mapOverlays.add(itemizedOverlay2);

		MapController mapController = mapView.getController();

		mapController.animateTo(point2);
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
