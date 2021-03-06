package com.example.mvcmobilepos;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class HistoryScreen extends Activity {
//	ArrayList<String> CartList = new ArrayList<String>();
	private ArrayList<HashMap<String, String>> ItemList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		
		ShowAllData();
		// Button1(BackButton)
		final Button btn_Back = (Button) findViewById(R.id.button1);
		// Perform action on click
		btn_Back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent newActivity = new Intent(HistoryScreen.this,
						InventoryScreen.class);

				startActivity(newActivity);
				finish();

			}
		});
	}

	public void ShowAllData() {
		final HistoryDB reportDB = new HistoryDB(this);
		final DBClass db = new DBClass(this);
		ItemList = db.SelectAllData();
		
		// listView1
		ListView lisView1 = (ListView) findViewById(R.id.listView2);

		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(HistoryScreen.this, ItemList,
				R.layout.activity_historycolumn, new String[] { "ProductSaleDate", "ProductName",
						"ProductSoldQuantity", "ProductLinePrice" }, new int[] { R.id.ColDate,
						R.id.ColName, R.id.ColQuantity, R.id.TotalPrice });
		
		lisView1.setAdapter(sAdap);
		registerForContextMenu(lisView1);

		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.history_screen, menu);
		return true;
	}

}
