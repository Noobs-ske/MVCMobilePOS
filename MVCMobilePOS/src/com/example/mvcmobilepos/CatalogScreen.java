package com.example.mvcmobilepos;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CatalogScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_catalog);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.catalog_screen, menu);
		return true;
	}

}
