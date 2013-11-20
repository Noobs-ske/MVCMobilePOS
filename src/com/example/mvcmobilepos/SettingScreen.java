package com.example.mvcmobilepos;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class SettingScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);

		// Button1(BackButton)
		final Button btn_Back = (Button) findViewById(R.id.button1);
		// Perform action on click
		btn_Back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent newActivity = new Intent(SettingScreen.this,
						InventoryScreen.class);

				startActivity(newActivity);
				finish();

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting_screen, menu);
		return true;
	}

}
