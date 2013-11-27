package com.example.mvcmobilepos;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.mvcmobilepos.DBClass;
import com.example.mvcmobilepos.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class CartScreen extends Activity {

	private ArrayList<HashMap<String, String>> ItemList;
	ArrayList<String> SaleList;
	ArrayList<String> CartList = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart);
		Intent intent = getIntent();
		SaleList = intent.getStringArrayListExtra("PurchaseList");

		ShowAllData();
		//back to catalog button
				final Button btn_Catalog = (Button) findViewById(R.id.button2);
				// Perform action on click
				btn_Catalog.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {

						Intent newActivity = new Intent(CartScreen.this,
								CatalogScreen.class);
						newActivity.putStringArrayListExtra("PurchaseList",
								SaleList);
						
						startActivity(newActivity);
						finish();

					}
				});
				
				//checkout button
				final Button btn_Checkout = (Button) findViewById(R.id.button1);
				// Perform action on click
				btn_Checkout.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						Checkout();
						Intent newActivity = new Intent(CartScreen.this,
								InventoryScreen.class);
						
						startActivity(newActivity);
						finish();

					}
				});
				
				
	}

	
	public void Checkout() {
		DBClass myDb = new DBClass(this);

		for (int i = 0; i < SaleList.size(); i++) {
			String arrDataDB[] = myDb.SelectData(SaleList.get(i));
			String arrDataSale[] = myDb.SelectData2(SaleList.get(i));
			myDb.reduceQuantity(arrDataDB[0] ,arrDataDB[1] , Integer.parseInt(arrDataDB[2]) 
				 , Integer.parseInt(arrDataSale[2]), arrDataDB[3]);
			myDb.DeleteData2(SaleList.get(i));
			myDb.DeleteData2(SaleList.get(i));
		}
		SaleList = null;

	}
	

	public void ShowAllData() {
		double total = 0;
		ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map;

		DBClass myDb = new DBClass(this);
		for (int i = 0; i < SaleList.size(); i++) {
			String arrData[] = myDb.SelectData2(SaleList.get(i));
			map = new HashMap<String, String>();
			map.put("ItemID2", arrData[0]);
			map.put("Name", arrData[1]);
			map.put("Quantity", arrData[2]);
			map.put("Price", arrData[3]);
			total += Double.parseDouble(arrData[2])*Double.parseDouble(arrData[3]) ;
			MyArrList.add(map);
		}
		ItemList = MyArrList;
		// listView1
		ListView lisView1 = (ListView) findViewById(R.id.listView1);

		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(CartScreen.this, ItemList,
				R.layout.activity_cartcolumn, new String[] { "ItemID2",
						"Name","Quantity"}, new int[] { R.id.ColItemID, R.id.ColName,
						R.id.ColQuantity});
		lisView1.setAdapter(sAdap);
		registerForContextMenu(lisView1);

		// Show total
		TextView text_TotalPrice = (TextView) findViewById(R.id.TotalPrice);
		text_TotalPrice.setText("" + total);
	}

	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// if (v.getId()==R.id.list) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
		menu.setHeaderTitle("Command for : "
				+ ItemList.get(info.position).get("Name").toString());
		String[] menuItems = getResources().getStringArray(R.array.CmdMenu);
		menu.add(Menu.NONE, 2, 2, menuItems[2]);
		// }
	}

	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		int menuItemIndex = item.getItemId();
		String[] menuItems = getResources().getStringArray(R.array.CmdMenu);
		String CmdName = menuItems[menuItemIndex];
		String MemID = ItemList.get(info.position).get("MemberID").toString();
		String MemName = ItemList.get(info.position).get("Name").toString();
		

		if ("Delete".equals(CmdName)) {

			ItemList.remove(ItemList.get(info.position));
			for (int i = 0; i < SaleList.size(); i++) {
				if (MemID.equals(SaleList.get(i))) {
					SaleList.remove(i);
				}
			}
			// Call Show Data again
			ShowAllData();
		}

		return true;
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cart_screen, menu);
		return true;
	}

}
