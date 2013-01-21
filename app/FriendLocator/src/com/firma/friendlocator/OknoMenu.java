package com.firma.friendlocator;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firma.friendlocator.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class OknoMenu extends MapActivity /*implements AdapterView.OnItemSelectedListener*/ {
	
/*	public TextView selection;
	public ListView items;
	public ArrayList<String> listItems = new ArrayList<String>();
	public Spinner spin;
	public ArrayAdapter aa;
	public String[] items1; */
	
	static ArrayList<Friend> friends;
	public static MapView mapView;
	public static int i=1;
	public static int ch=0;
	public static int u=0;
	public static int ur=0;

	private static final int latitudeE6 = 37985339;
	private static final int longitudeE6 = 23716735;
	double lat=0;
	double lon=0;
	
	static double latitude,latitudeold;
	static double longitude,longitudeold;
	
    static public Context re=null;
    List list= null;
    static Drawable drawablere=null;
    static Drawable drawablerem=null;
    static CustomItemizedOverlay itemizedOverlayre=null;	
	
	public LocationManager locmgr = null;
	//public TextView mytext;
	
	static Boolean value1=false;
	public static GeoPoint pointme=null;
	ArrayAdapter<String> dataAdapter=null;
	
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	//Start a location listener
    LocationListener onLocationChange=new LocationListener() {
        public void onLocationChanged(Location loc) {
            //sets and displays the lat/long when a location is provided  
            latitudeold=latitude;
            longitudeold=longitude;
            latitude = loc.getLatitude();
            longitude = loc.getLongitude();
            dataupdate.test.SendMyLocation((int) (latitude*1e6),(int) (longitude*1e6));
            Log.d("onLocationChanged", "przed upd na serv, token:"+OknoLogowania.getToken());
            
            update();
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
        //locmgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,1000.0f,onLocationChange);
        locmgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,1000.0f,onLocationChange);
    }
    static CustomItemizedOverlay itemizedOverlayrem=null;
    static CustomItemizedOverlay meold=null;
    public void update(){
    	MapController mapController = mapView.getController();
    	List mapOverlays = mapView.getOverlays();
    	if(meold!=null){
    		mapOverlays.remove(meold);
    		itemizedOverlayrem.remove();
    	}
    	Drawable drawable = this.getResources().getDrawable(R.drawable.map_point);
    	CustomItemizedOverlay me = new CustomItemizedOverlay(
				drawable, this);
    	 pointme = new GeoPoint((int) (latitude*1e6), (int) (longitude*1e6));
		OverlayItem mee = new OverlayItem(pointme, "Me",
				"I'm hear");
		me.addOverlay(mee);
		mapOverlays.add(me);
		if(ch==0){
			ch=1;
		mapController.animateTo(pointme);
		}
		meold=me;
		mapView.invalidate();	
    }
    
    
    public static void change(){
    	
    		
        mapView.setStreetView(!value1);
        mapView.setSatellite(value1);
        mapView.invalidate();
    }
    
    
    public static void draw(){
    	while(re==null){
    		
    	}
        drawablere = re.getResources().getDrawable(R.drawable.google);
        drawablerem = re.getResources().getDrawable(R.drawable.map_point);
    	itemizedOverlayre = new CustomItemizedOverlay(drawablere, re);
    	itemizedOverlayrem = new CustomItemizedOverlay(drawablerem, re);
		List mapOverlays = mapView.getOverlays();
		Log.d("ilosc znajomych update!!!!!!!!!!!!!!", Integer.toString(dataupdate.friends.size()));
		if(mapOverlays==null || itemizedOverlayre==null){
		}
		else{
		mapOverlays.clear();
		itemizedOverlayre.remove();
		itemizedOverlayrem.remove();
		for(int i=0; i<dataupdate.friends.size(); i++)
		{
			Friend fr = dataupdate.friends.get(i);
			Log.d("latiutude", Integer.toString(fr.getLatitude()));
			GeoPoint point = new GeoPoint(fr.getLatitude(), fr.getLongitude());
			OverlayItem overlayitem = new OverlayItem(point, fr.getName(),
					fr.getLogin());
			itemizedOverlayre.addOverlay(overlayitem);
			mapOverlays.add(itemizedOverlayre);
			MapController mapController = mapView.getController();
			mapView.postInvalidate();
		}
		if(pointme!=null){
		OverlayItem overlayitem = new OverlayItem(pointme, "Me","I'm hear");
		itemizedOverlayrem.addOverlay(overlayitem);
		mapOverlays.add(itemizedOverlayrem);
		}

		}
    }
    public Handler handler=new Handler();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		setContentView(R.layout.activity_okno_menu);
		re=(Context)this;
/*		Log.d("onCreate", "0");
		spin = (Spinner) findViewById(R.id.spinner1);
		Log.d("onCreate", "1");
		spin.setOnItemSelectedListener(this);
		Log.d("onCreate", "2");
		this.aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, items1);
		Log.d("onCreate", "3");
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Log.d("onCreate", "4");
		spin.setAdapter(aa);
		Log.d("onCreate", "5"); */
		
		//mytext = (TextView) findViewById(R.id.mytext);
        
        //grab the location manager service
		dataupdate.run2();
        locmgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //mytext.setText("Oczekiwanie na lokalizacje");
        
		ServerConnector test = new ServerConnector(OknoLogowania.getToken());
		//friends = new ArrayList<Friend>();
		friends = test.GetFriends();
		
		mapView = (MapView) findViewById(R.id.map_view);
		mapView.setBuiltInZoomControls(true);
		
		Drawable drawable = this.getResources().getDrawable(R.drawable.google); /**/
		List mapOverlays = mapView.getOverlays();
		Log.d("ilosc znajomych", Integer.toString(friends.size()));
		for(int i=0; i<friends.size(); i++)
		{
			Friend fr = friends.get(i);
			Log.d("latiutude", Integer.toString(fr.getLatitude()));
			/*items1[i] = (fr.getName() + ", " + fr.getLogin());*/
			CustomItemizedOverlay itemizedOverlay = new CustomItemizedOverlay(
					drawable, this);
			GeoPoint point = new GeoPoint(fr.getLatitude(), fr.getLongitude());
			OverlayItem overlayitem = new OverlayItem(point, fr.getName(),
					fr.getLogin());
			itemizedOverlay.addOverlay(overlayitem);
			mapOverlays.add(itemizedOverlay);
		}
		Spinner spinner2= (Spinner) findViewById(R.id.spinner1);
    	list = new ArrayList<String>();
    	for(int i=0; i<friends.size(); i++){
    		Friend fr = friends.get(i);
        	list.add(fr.getName());
    	}
    	dataAdapter = new ArrayAdapter<String>(this,
    		android.R.layout.simple_spinner_item, list);
    	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spinner2.setAdapter(dataAdapter); 
    	
    	spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    	    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
    	    	Friend fr = dataupdate.friends.get(pos);
	        	MapController mapController = OknoMenu.mapView.getController();
	        	GeoPoint point2 = new GeoPoint(fr.getLatitude(), fr.getLongitude());
	        	mapController.animateTo(point2);
    	    }
    	    public void onNothingSelected(AdapterView<?> parent) {
    	    }
    	});
		CustomItemizedOverlay itemizedOverlay = new CustomItemizedOverlay(
				drawable, this);
		CustomItemizedOverlay itemizedOverlay2 = new CustomItemizedOverlay(
				drawable, this);
		Log.d("moja lokalizacja", Double.toString(longitude));
		GeoPoint point = new GeoPoint(latitudeE6, longitudeE6);
		GeoPoint point2 = new GeoPoint(40453046, -3688445);
		
		if(pointme!=null){
			if(meold!=null){
	    		mapOverlays.remove(meold);
	    	}
			Drawable drawableme = this.getResources().getDrawable(R.drawable.map_point);
	    	CustomItemizedOverlay me = new CustomItemizedOverlay(
					drawableme, this);
	    	 pointme = new GeoPoint((int) (latitude*1e6), (int) (longitude*1e6));
			OverlayItem mee = new OverlayItem(pointme, "Me",
					"I'm hear");
			me.addOverlay(mee);
			mapOverlays.add(me);
			meold=me;
			
		}

		OverlayItem overlayitem2 = new OverlayItem(point2, "Hello",
				"Hala Madrid");

		itemizedOverlay2.addOverlay(overlayitem2);
		
		mapOverlays.add(itemizedOverlay2);

		MapController mapController = mapView.getController();
		if(latitude==0.0 && longitude==0.0 && ch==0){
			mapController.animateTo(point2);
			}
			else{
				if(pointme==null)
					Log.d("gówno", Integer.toString(20));
				mapController.animateTo(pointme);	
			}
		mapController.setZoom(15);
        mapView.setStreetView(!value1);
        mapView.setSatellite(value1);
        mapView.invalidate();
	}
	
	public void menu(View view) {
	    Intent intent = new Intent(this, OknoMenuGlowne.class);
	    startActivity(intent);
	}
	   Context context;
	    public void center(View view){
	    	if(pointme!=null){
	    	MapController mapController = mapView.getController();
	    	mapController.animateTo(pointme);
	    	}
	    	else{
	    		context = getApplicationContext();
	    		Toast.makeText(context, "czekam na znalezienie twojej pozycji", Toast.LENGTH_LONG).show();
	    	}
	    }
	 public boolean onKeyDown(int keyCode, KeyEvent event) {
	      Log.d(null,"In on Key Down");
	      if (keyCode == KeyEvent.KEYCODE_BACK) {
	    	  AlertDialog.Builder builder = new AlertDialog.Builder(this);
	      	builder
	      	.setTitle("Chcesz wyjœæ z aplikacji")
	      	.setMessage("Na pewno?")
	      	.setIcon(android.R.drawable.ic_dialog_alert)
	      	.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
	      		@SuppressLint("NewApi")
				public void onClick(DialogInterface dialog, int which) {			      	
	      	    	//Yes button clicked, do something
	      			dataupdate.updateTimer.cancel();
	      			dataupdate.updateTimer.purge();
	      			dataupdate.w=30000;
	      	    	finishAffinity();
	      	    }
	      	})
	      	.setNegativeButton("Nie", null)						//Do nothing on no
	      	.show();
	      }
	      return super.onKeyDown(keyCode, event);
	  }
	
/*	public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
		selection.setText(items1[position]);
	}

	public void onNothingSelected(AdapterView<?> parent) {
		selection.setText("Znajomi");
	} */
}
