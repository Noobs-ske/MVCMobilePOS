package com.example.mvcmobilepos;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddScreen extends Activity {

	private Button btnScan;
	DBCommandConfirm confirm = new DBCommandConfirm();
	private TextView txtResult;
	private EditText tItemID;
	private EditText tName;
	private EditText tQuantity;
	private EditText tPrice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		tItemID = (EditText) findViewById(R.id.txtItemID);
		tName = (EditText) findViewById(R.id.txtName);
		tQuantity = (EditText) findViewById(R.id.txtQuantity);
		tPrice = (EditText) findViewById(R.id.txtPrice);
		 
		// connect btnScan and txtResult to View
		btnScan = (Button) findViewById(R.id.buttonScan);
		txtResult = (EditText) findViewById(R.id.txtItemID);
		
		// Test
		final DBClass myDb = new DBClass(this);
	
		final AlertDialog.Builder adb = new AlertDialog.Builder(this);
		final AlertDialog ad = adb.create();
		
		// btnSave (Save)

		final Button save = (Button) findViewById(R.id.btnSave);
		save.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				String productID = tItemID.getText().toString();
				String productName = tName.getText().toString();
				String productQuan = tQuantity.getText().toString();
				String productPrice = tPrice.getText().toString();
				

			// If Save Complete if (SaveData()) { // Open Form Main Intent
				myDb.InsertData(productID, productName, productQuan, productPrice);
				ad.setMessage("Saved");
				ad.show();
				Intent newActivity = new Intent(AddScreen.this,InventoryScreen.class);
				startActivity(newActivity);
				
//			if (confirm.checkSaveData(productID,productName,productQuan,productPrice))
//				{
//					
//					confirm.SaveData(productID, productName, productQuan, productPrice);
//					ad.setMessage("Saved");
//					ad.show();
//					Intent newActivity2 = new Intent(AddScreen.this,
//							InventoryScreen.class);
//					startActivity(newActivity);
//				}
			}
		}
	);

		// btnCancel (Cancel)
		final Button cancel = (Button) findViewById(R.id.btnCancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Open Form Main
				Intent newActivity = new Intent(AddScreen.this,
						InventoryScreen.class);
				startActivity(newActivity);
				finish();
			}
		});

		// define Event when press btnScan
		btnScan.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					// define intent to call Barcode Scanner
					Intent intent = new Intent(
							"com.google.zxing.client.android.SCAN");
					// send Mode to Scan with Barcode Scanner
					intent.putExtra("SCAN_MODE", "ONE_D_MODE");

					// start Activity from intent which define by requestCode =
					// 0
					startActivityForResult(intent, 0);

				} catch (Exception e) {
					// TODO: handle exception
					// if don't install Barcode Scanner there will push a
					// message Please Install Barcode Scanner
					Toast.makeText(getBaseContext(),
							"Please Install Barcode Scanner",
							Toast.LENGTH_SHORT).show();
				}

			}
		});
	}

	// when complete the scan it will call function onActivityResult
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		// TODO Auto-generated method stub
		if (requestCode == 0) // test requestCode and Barcode Scanner are same
								// with value that we got
		{
			if (resultCode == RESULT_OK) // if Barcode Scanner complete
			{
				// Receive Barcode Scanner that got by scan
				String contents = intent.getStringExtra("SCAN_RESULT");
				// Receieve Barcode Scanner that got each type
				String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
				// show in txtResult
				txtResult.setText("" + contents);
			}
		}

		// btnSave (Save)
		/*
		 * final Button save = (Button) findViewById(R.id.btnSave);
		 * save.setOnClickListener(new View.OnClickListener() { public void
		 * onClick(View v) {
		 * 
		 * // If Save Complete if (SaveData()) { // Open Form Main Intent
		 * newActivity = new Intent(AddScreen.this, InventoryScreen.class);
		 * startActivity(newActivity); } } });
		 */

		// btnCancel (Cancel)
		final Button cancel = (Button) findViewById(R.id.btnCancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Open Form Main
				Intent newActivity = new Intent(AddScreen.this,
						InventoryScreen.class);
				startActivity(newActivity);
				finish();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_screen, menu);
		return true;
	}

}
