package com.example.mvcmobilepos;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class InventoryScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory);

		// Call Show List All Data
		// ShowListData();

		// Button2(CatalogButton)
		final Button btn_Catalog = (Button) findViewById(R.id.button2);
		// Perform action on click
		btn_Catalog.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent newActivity = new Intent(InventoryScreen.this,
						CatalogScreen.class);

				/*
				 * newActivity.putStringArrayListExtra("PurchaseList",
				 * PurchaseList); PurchaseList = new ArrayList<String>();
				 */

				startActivity(newActivity);
				finish();

			}
		});

		// Button3 (AddButton)

		final Button btn_Add = (Button) findViewById(R.id.button3);
		// Perform action on click
		btn_Add.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// Open Add
				Intent newActivity = new Intent(InventoryScreen.this,
						AddScreen.class);
				startActivity(newActivity);

			}
		});

		// Button4(HistoryButton)
		final Button btn_History = (Button) findViewById(R.id.button4);
		// Perform action on click
		btn_History.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// Open History
				Intent newActivity = new Intent(InventoryScreen.this,
						HistoryScreen.class);
				startActivity(newActivity);

			}
		});

		// Button6(HistoryButton)
		final Button btn_Setting = (Button) findViewById(R.id.button6);
		// Perform action on click
		btn_Setting.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// Open Setting
				Intent newActivity = new Intent(InventoryScreen.this,
						SettingScreen.class);
				startActivity(newActivity);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inventory, menu);
		return true;
	}

}
