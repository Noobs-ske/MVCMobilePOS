package com.example.mvcmobilepos;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class CatalogScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_catalog);
		
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

}


// myDb.InsertData(tItemID.getText().toString(), tName
//.getText().toString(), tQuantity.getText().toString(), tPrice
//.getText().toString());