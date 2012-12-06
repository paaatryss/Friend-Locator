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
	private MapView mapView;

	private static final int latitudeE6 = 37985339;
	private static final int longitudeE6 = 23716735;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_okno_menu);
		mapView = (MapView) findViewById(R.id.map_view);
		mapView.setBuiltInZoomControls(true);

		List mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.google);
		CustomItemizedOverlay itemizedOverlay = new CustomItemizedOverlay(
				drawable, this);

		GeoPoint point = new GeoPoint(latitudeE6, longitudeE6);
		OverlayItem overlayitem = new OverlayItem(point, "Hello",
				"I'm in Athens, Greece!");

		itemizedOverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedOverlay);

		MapController mapController = mapView.getController();

		mapController.animateTo(point);
		mapController.setZoom(10);

	}

	protected boolean isRouteDisplayed() {
		return false;
	}
	public void menu(View view) {
	    Intent intent = new Intent(this, OknoMenuGlowne.class);
	    startActivity(intent);
	}

}
