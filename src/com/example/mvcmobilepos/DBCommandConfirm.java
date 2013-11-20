package com.example.mvcmobilepos;

import android.app.Activity;
import android.app.AlertDialog;
import android.widget.Toast;

// As teacher suggested, it is recommended that we create a domain purely for confirmation before
// we ask the DB to perform a certain action.
// So this domain is needed and will work like our old AddActivty
// where if SaveData returns true, DB will save the item.
// So this will control if SaveData is valid, item purchase is valid, blah blah is valid before it tells
// the DB to perform actions
// And if it is invalid, it will alert the user.
// Add button -> Send to DBCommandConfirm --> DBCommandConfirm pass it over to DB -> DB adds item

public class DBCommandConfirm extends Activity {

	final DBClass myDb = new DBClass(this);
	
	public void saveData(String productID, String productName, String productQuan, String productPrice)
	{
			myDb.InsertData(productID, productName, productQuan, productPrice);
	}
	//Check for save
	public boolean checkSaveData(String productID, String productName, String productQuan, String productPrice) {

		// txtMemberID, txtName, txtTel
		String id = productID;
		String name = productName;
		String quan = productQuan;
		String price = productPrice;
		// Dialog

		// Check MemberID
		if (id.length() == 0) {
			return false;
		}

		// Check Name
		if (name.length() == 0) {
			return false;
		}

		// Check Quantity
		if (quan.length() == 0) {
			return false;
		}

		// Check Price
		if (price.length() == 0) {
			return false;
		}

		// new Class DB
		final DBClass myDb = new DBClass(this);

		// Check Data (MemberID exists)
		String arrData[] = myDb.SelectData(id);
		if (arrData != null) {
			return false;
		}



		return true;

	}
	
}
