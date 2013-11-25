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

public class CatalogScreen extends Activity {

	private ArrayList<HashMap<String, String>> ItemList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_catalog);
		
		ShowListData();
		
		//back to inventory button
		final Button btn_Invntory = (Button) findViewById(R.id.button2);
		// Perform action on click
		btn_Invntory.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent newActivity = new Intent(CatalogScreen.this,
						InventoryScreen.class);
				
				startActivity(newActivity);
				finish();

			}
		});
		
		//go to cart button
		final Button btn_Cart = (Button) findViewById(R.id.button6);
		// Perform action on click
		btn_Cart.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent newActivity = new Intent(CatalogScreen.this,
						CartScreen.class);
				
				startActivity(newActivity);
				finish();

			}
		});
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.catalog_screen, menu);
		return true;
	}
	
	public void ShowListData() {
		final DBClass myDb = new DBClass(this);
		ItemList = myDb.SelectAllData();

		// listView1
		ListView lisView1 = (ListView) findViewById(R.id.listView1);

		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(CatalogScreen.this, ItemList,
				R.layout.activity_catalogcolumn, new String[] { "ItemID", "Name",
						 "Price" }, new int[] { R.id.ColItemID,
						R.id.ColName, R.id.TotalPrice });
		lisView1.setAdapter(sAdap);
		registerForContextMenu(lisView1);
	}

}


// myDb.InsertData(tItemID.getText().toString(), tName
//.getText().toString(), tQuantity.getText().toString(), tPrice
//.getText().toString());