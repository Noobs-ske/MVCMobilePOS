package com.example.mvcmobilepos;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBClass extends SQLiteOpenHelper {

	// Database Version
	private static final int DATABASE_VERSION = 13;

	// Database Name
	private static final String DATABASE_NAME = "mydatabase";

	// Table Name
	private static final String TABLE_PRODUCT = "Products";
	
	// Table Name
	private static final String TABLE_PURCHASE = "Purchase";

	// Table Name
	private static final String TABLE_HISTORY = "History";
		
	public DBClass(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// Create Table Name
		db.execSQL("CREATE TABLE " + TABLE_PRODUCT
				+ "(ItemID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " Name TEXT(100)," + " Quantity TEXT(100), " 
				+ " Price TEXT(100)," + " Description TEXT(100));");
		db.execSQL("CREATE TABLE " + TABLE_PURCHASE
				+ "(ItemID2 INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " Name TEXT(100)," + " Quantity TEXT(100), " 
				+ " Price TEXT(100)," + " Description TEXT(100));");
		db.execSQL("CREATE TABLE " + TABLE_HISTORY
				+ "(Date TEXT(100)," + " Name TEXT(100)," + " Quantity TEXT(100), " 
				+ " Price TEXT(100))");
		
		

		Log.d("CREATE TABLE", "Create Table Successfully.");
	}

	// Insert Data
	public long InsertData(String strItemID, String strName, String strQuantity, String strPrice, String strDescription) {
		// TODO Auto-generated method stub

		try {
			SQLiteDatabase db;
			db = this.getWritableDatabase(); // Write Data

			/**
			 * for API 11 and above SQLiteStatement insertCmd; String strSQL =
			 * "INSERT INTO " + TABLE_MEMBER +
			 * "(ItemID,Name,Quantity,Price) VALUES (?,?,?)";
			 * 
			 * insertCmd = db.compileStatement(strSQL); insertCmd.bindString(1,
			 * strItemID); insertCmd.bindString(2, strName);
			 * insertCmd.bindString(3, strTel); return
			 * insertCmd.executeInsert();
			 */

			ContentValues Val = new ContentValues();
			Val.put("ItemID", strItemID);
			Val.put("Name", strName);
			Val.put("Quantity", strQuantity);
			Val.put("Price", strPrice);
			Val.put("Description",strDescription);
			long rows = db.insert(TABLE_PRODUCT, null, Val);

			db.close();
			return rows; // return rows inserted.

		} catch (Exception e) {
			return -1;
		}

	}
	
	// Insert Purchase Data
	public long InsertData2(String strItemID, String strName, String strQuantity, String strPrice) {
		// TODO Auto-generated method stub

		try {
			SQLiteDatabase db;
			db = this.getWritableDatabase(); // Write Data

			/**
			 * for API 11 and above SQLiteStatement insertCmd; String strSQL =
			 * "INSERT INTO " + TABLE_MEMBER +
			 * "(ItemID,Name,Quantity,Price) VALUES (?,?,?)";
			 * 
			 * insertCmd = db.compileStatement(strSQL); insertCmd.bindString(1,
			 * strItemID); insertCmd.bindString(2, strName);
			 * insertCmd.bindString(3, strTel); return
			 * insertCmd.executeInsert();
			 */

			ContentValues Val = new ContentValues();
			Val.put("ItemID2", strItemID);
			Val.put("Name", strName);
			Val.put("Quantity", strQuantity);
			Val.put("Price", strPrice);
			//Val.put("Description",strDescription);
			long rows = db.insert(TABLE_PURCHASE, null, Val);

			db.close();
			return rows; // return rows inserted.

		} catch (Exception e) {
			return -1;
		}

	}
	

	
		// Insert History Data	
		public long InsertData3( String strDate, String strName, String strQuantity, String strPrice) {
			// TODO Auto-generated method stub

			try {
				SQLiteDatabase db;
				db = this.getWritableDatabase(); // Write Data

				ContentValues Val = new ContentValues();
				
				Val.put("Date",strDate);
			//	Val.put("ItemID", strItemID);
				Val.put("Name", strName);
				Val.put("Quantity", strQuantity);
				Val.put("Price", strPrice);
				
				long rows = db.insert(TABLE_HISTORY, null, Val);

				db.close();
				return rows; // return rows inserted.

			} catch (Exception e) {
				return -1;
			}

		}
		

	// Select Data
	@SuppressLint("NewApi")
	public String[] SelectData(String strItemID) {
		// TODO Auto-generated method stub

		try {
			String arrData[] = null;

			SQLiteDatabase db;
			db = this.getReadableDatabase(); // Read Data

			Cursor cursor = db.query(false, TABLE_PRODUCT, new String[] { "*" },
					"ItemID=?", new String[] { String.valueOf(strItemID) },
					null, null, null, null,null);

			if (cursor != null) {
				if (cursor.moveToFirst()) {
					arrData = new String[cursor.getColumnCount()];
					/***
					 * 0 = ItemID , 1 = Name , 2 = Quantity , 3 = Price , 4 = Description
					 */
					arrData[0] = cursor.getString(0);
					arrData[1] = cursor.getString(1);
					arrData[2] = cursor.getString(2);
					arrData[3] = cursor.getString(3);
					arrData[4] = cursor.getString(4);
				}
			}
			cursor.close();
			db.close();
			return arrData;

		} catch (Exception e) {
			return null;
		}

	}
	
	
	@SuppressLint("NewApi")
	public String[] SelectData2(String strItemID) {
		// TODO Auto-generated method stub

		try {
			String arrData[] = null;

			SQLiteDatabase db;
			db = this.getReadableDatabase(); // Read Data

			Cursor cursor = db.query(false, TABLE_PURCHASE, new String[] { "*" },
					"ItemID2=?", new String[] { String.valueOf(strItemID) },
					null, null, null, null,null);

			if (cursor != null) {
				if (cursor.moveToFirst()) {
					arrData = new String[cursor.getColumnCount()];
					/***
					 * 0 = ItemID , 1 = Name , 2 = Quantity , 3 = Price , 4 = Description
					 */
					arrData[0] = cursor.getString(0);
					arrData[1] = cursor.getString(1);
					arrData[2] = cursor.getString(2);
					arrData[3] = cursor.getString(3);
					arrData[4] = cursor.getString(4);
				}
			}
			cursor.close();
			db.close();
			return arrData;

		} catch (Exception e) {
			return null;
		}

	}

	
	@SuppressLint("NewApi")
	public String[] SelectData3(String strName) {
		// TODO Auto-generated method stub

		try {
			String arrData[] = null;

			SQLiteDatabase db;
			db = this.getReadableDatabase(); // Read Data

			Cursor cursor = db.query(false, TABLE_HISTORY, new String[] { "*" },
					"ItemName3=?", new String[] { String.valueOf(strName) },
					null, null, null, null,null);

			if (cursor != null) {
				if (cursor.moveToFirst()) {
					arrData = new String[cursor.getColumnCount()];
					/***
					 * 0 = Date, 1 = Name , 2 = Quantity , 3 = Price
					 */
					arrData[0] = cursor.getString(0);
					arrData[1] = cursor.getString(1);
					arrData[2] = cursor.getString(2);
					arrData[3] = cursor.getString(3);
//					arrData[4] = cursor.getString(4);
				}
			}
			cursor.close();
			db.close();
			return arrData;

		} catch (Exception e) {
			return null;
		}

	}

	
	// Delete Data
	public long DeleteData(String strItemID) {
		// TODO Auto-generated method stub

		try {

			SQLiteDatabase db;
			db = this.getWritableDatabase(); // Write Data

			/**
			 * for API 11 and above SQLiteStatement insertCmd; String strSQL =
			 * "DELETE FROM " + TABLE_PRODUCT + " WHERE ItemID = ? ";
			 * 
			 * insertCmd = db.compileStatement(strSQL); insertCmd.bindString(1,
			 * strItemID);
			 * 
			 * return insertCmd.executeUpdateDelete();
			 * 
			 */

			long rows = db.delete(TABLE_PRODUCT, "ItemID = ?",
					new String[] { String.valueOf(strItemID) });

			db.close();
			return rows; // return rows deleted.

		} catch (Exception e) {
			return -1;
		}

	}
	
	
	// Delete Data
		public long DeleteData2(String strItemID) {
			// TODO Auto-generated method stub

			try {

				SQLiteDatabase db;
				db = this.getWritableDatabase(); // Write Data

				/**
				 * for API 11 and above SQLiteStatement insertCmd; String strSQL =
				 * "DELETE FROM " + TABLE_PRODUCT + " WHERE ItemID = ? ";
				 * 
				 * insertCmd = db.compileStatement(strSQL); insertCmd.bindString(1,
				 * strItemID);
				 * 
				 * return insertCmd.executeUpdateDelete();
				 * 
				 */

				long rows = db.delete(TABLE_PURCHASE, "ItemID2 = ?",
						new String[] { String.valueOf(strItemID) });

				db.close();
				return rows; // return rows deleted.

			} catch (Exception e) {
				return -1;
			}

		}

		
		// Delete Data
				public long DeleteData3(String strItemID) {
					// TODO Auto-generated method stub

					try {

						SQLiteDatabase db;
						db = this.getWritableDatabase(); // Write Data

						/**
						 * for API 11 and above SQLiteStatement insertCmd; String strSQL =
						 * "DELETE FROM " + TABLE_PRODUCT + " WHERE ItemID = ? ";
						 * 
						 * insertCmd = db.compileStatement(strSQL); insertCmd.bindString(1,
						 * strItemID);
						 * 
						 * return insertCmd.executeUpdateDelete();
						 * 
						 */

						long rows = db.delete(TABLE_HISTORY, "ItemID3 = ?",
								new String[] { String.valueOf(strItemID) });

						db.close();
						return rows; // return rows deleted.

					} catch (Exception e) {
						return -1;
					}

				}
		
	// Show All Data
	public ArrayList<HashMap<String, String>> SelectAllData() {
		// TODO Auto-generated method stub

		try {

			ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> map;

			SQLiteDatabase db;
			db = this.getReadableDatabase(); // Read Data

			String strSQL = "SELECT  * FROM " + TABLE_PRODUCT;
			Cursor cursor = db.rawQuery(strSQL, null);

			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						map = new HashMap<String, String>();
						map.put("ItemID", cursor.getString(0));
						map.put("Name", cursor.getString(1));
						map.put("Quantity", cursor.getString(2));
						map.put("Price", cursor.getString(3));
						MyArrList.add(map);
					} while (cursor.moveToNext());
				}
			}
			cursor.close();
			db.close();
			return MyArrList;

		} catch (Exception e) {
			return null;
		}

	}
	
	// Show All Data
		public ArrayList<HashMap<String, String>> SelectAllData2() {
			// TODO Auto-generated method stub

			try {

				ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
				HashMap<String, String> map;

				SQLiteDatabase db;
				db = this.getReadableDatabase(); // Read Data

				String strSQL = "SELECT  * FROM " + TABLE_PURCHASE;
				Cursor cursor = db.rawQuery(strSQL, null);

				if (cursor != null) {
					if (cursor.moveToFirst()) {
						do {
							map = new HashMap<String, String>();
							map.put("ItemID2", cursor.getString(0));
							map.put("Name", cursor.getString(1));
							map.put("Quantity", cursor.getString(2));
							map.put("Price", cursor.getString(3));
							MyArrList.add(map);
						} while (cursor.moveToNext());
					}
				}
				cursor.close();
				db.close();
				return MyArrList;

			} catch (Exception e) {
				return null;
			}

		}
		
		
		
		// Show All Data
				public ArrayList<HashMap<String, String>> SelectAllData3() {
					// TODO Auto-generated method stub

					try {

						ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
						HashMap<String, String> map;

						SQLiteDatabase db;
						db = this.getReadableDatabase(); // Read Data

						String strSQL = "SELECT  * FROM " + TABLE_HISTORY;
						Cursor cursor = db.rawQuery(strSQL, null);

						if (cursor != null) 
						{
							if (cursor.moveToFirst()) {
								do {
									map = new HashMap<String, String>();
									map.put("Date", cursor.getString(0));
									map.put("ItemID3", cursor.getString(1));
									map.put("Name", cursor.getString(2));
									map.put("Quantity", cursor.getString(3));
									map.put("Price", cursor.getString(4));
									MyArrList.add(map);
								} while (cursor.moveToNext());
							}
						}
						cursor.close();
						db.close();
						return MyArrList;

					} catch (Exception e) {
						return null;
					}

				}

	// Update Data
	public long UpdateData(String strItemID, String strName, String strQuantity, String strPrice) {
		// TODO Auto-generated method stub

		try {

			SQLiteDatabase db;
			db = this.getWritableDatabase(); // Write Data

			/**
			 * for API 11 and above SQLiteStatement insertCmd; String strSQL =
			 * "UPDATE " + TABLE_MEMBER + " SET Name = ? " + " , Tel = ? " +
			 * " WHERE ItemID = ? ";
			 * 
			 * insertCmd = db.compileStatement(strSQL); insertCmd.bindString(1,
			 * strName); insertCmd.bindString(2, strTel);
			 * insertCmd.bindString(3, strItemID);
			 * 
			 * return insertCmd.executeUpdateDelete();
			 * 
			 */
			ContentValues Val = new ContentValues();
			Val.put("Name", strName);
			Val.put("Quantity", strQuantity);
			Val.put("Price", strPrice);

			long rows = db.update(TABLE_PRODUCT, Val, " ItemID = ?",
					new String[] { String.valueOf(strItemID) });

			db.close();
			return rows; // return rows updated.

		} catch (Exception e) {
			return -1;
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PURCHASE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);

		// Re Create on method onCreate
		onCreate(db);
	}

	public void reduceQuantity(String ID ,String name , int current , int purchase, String price)
	{
		int currentquan = current;
		int purchasequan = purchase;
		int left = currentquan - purchasequan;
		String left2 = left+"";
		UpdateData(ID, name, left2, price);
	}

//	public void increaseQuantity(String ID ,String name , int current , int purchase, String price)
//	{
//		int currentquan = current;
//		int purchasequan = purchase;
//		int newquan = currentquan + purchasequan;
//		String newquan2 = newquan+"";
//		UpdateData(ID, name, newquan2, price);
//	}
	
}