package com.example.mvcmobilepos;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.mvcmobilepos.InventoryScreen;
import com.example.mvcmobilepos.DBClass;
import com.example.mvcmobilepos.R;
import com.example.mvcmobilepos.DBCommandConfirm;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class InventoryScreen extends Activity {
	ArrayList<HashMap<String, String>> ItemList;
	ArrayList<String> PurchaseList = new ArrayList<String>();
	private int PurchaseQuantity = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory);
		
		// Call Show List All Data
		ShowListData();	
		
		
		
		
		// Button2(CatalogButton)
		final Button btn_Catalog = (Button) findViewById(R.id.button2);
		// Perform action on click
		btn_Catalog.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent newActivity = new Intent(InventoryScreen.this,
						CatalogScreen.class);

			
				  newActivity.putStringArrayListExtra("PurchaseList",
				  PurchaseList); PurchaseList = new ArrayList<String>();
				 

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
	
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
		menu.setHeaderTitle("Command for : "
				+ ItemList.get(info.position).get("Name").toString());
		String[] menuItems = getResources().getStringArray(R.array.CmdMenu);
		for (int i = 0; i < menuItems.length; i++) {
			menu.add(Menu.NONE, i, i, menuItems[i]);
		}

	}
	
	public boolean onContextItemSelected(MenuItem item) {
		final DBClass myDb = new DBClass(this);
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		int menuItemIndex = item.getItemId();
		String[] menuItems = getResources().getStringArray(R.array.CmdMenu);
		String CmdName = menuItems[menuItemIndex];
		final String MemID = ItemList.get(info.position).get("ItemID").toString();
		final String MemName = ItemList.get(info.position).get("Name").toString();
		final String MemQuantity = ItemList.get(info.position).get("Quantity").toString();
		final String MemPrice = ItemList.get(info.position).get("Price").toString();
		// Check Event Command
		

		 if ("Edit".equals(CmdName)) {

			// Show on new activity
			Intent newActivity = new Intent(InventoryScreen.this,
					DBCommandConfirm.class);
			newActivity.putExtra("MemID",
					ItemList.get(info.position).get("ItemID").toString());
			startActivity(newActivity);

			// for Delete Command
		} else if ("Delete".equals(CmdName)) {

	//		DBClass myDb = new DBClass(this);

			long flg = myDb.DeleteData(MemID);
			if (flg > 0) {
				Toast.makeText(InventoryScreen.this,
						"Delete Data Successfully", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(InventoryScreen.this, "Delete Data Failed.",
						Toast.LENGTH_LONG).show();
			}

			// Call Show Data again
			ShowListData();
		}

		return true;
	
	}

	public void ShowListData() {
		final DBClass myDb = new DBClass(this);
		ItemList = myDb.SelectAllData();

		// listView1
		ListView lisView1 = (ListView) findViewById(R.id.listView1);

		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(InventoryScreen.this, ItemList,
				R.layout.activity_column, new String[] { "ItemID", "Name",
						"Quantity", "Price" }, new int[] { R.id.ColItemID,
						R.id.ColName, R.id.ColQuantity, R.id.TotalPrice });
		lisView1.setAdapter(sAdap);
		registerForContextMenu(lisView1);
	}
	
}
