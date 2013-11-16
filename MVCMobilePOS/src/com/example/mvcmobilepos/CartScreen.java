package com.example.mvcmobilepos;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class CartScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart);
		
		//back to catalog button
				final Button btn_Catalog = (Button) findViewById(R.id.button2);
				// Perform action on click
				btn_Catalog.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {

						Intent newActivity = new Intent(CartScreen.this,
								CatalogScreen.class);
						
						startActivity(newActivity);
						finish();

					}
				});
				
				//checkout button
				final Button btn_Checkout = (Button) findViewById(R.id.button1);
				// Perform action on click
				btn_Checkout.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {

						Intent newActivity = new Intent(CartScreen.this,
								InventoryScreen.class);
						
						startActivity(newActivity);
						finish();

					}
				});
				
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cart_screen, menu);
		return true;
	}

}
