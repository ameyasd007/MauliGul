package ameya.com.mauligul.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ameya.com.mauligul.database.DBContract.Customers;
import ameya.com.mauligul.database.DBContract.Items;
import ameya.com.mauligul.database.models.Customer;
import ameya.com.mauligul.database.models.Item;

/**
 * Created by Admin on 08-Jul-18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;

    // Logcat tag
    private static final String TAG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "BillingDatabase.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.e(TAG, "onCreate: "+Customers.CREATE_TABLE);
        sqLiteDatabase.execSQL(Customers.CREATE_TABLE);
        sqLiteDatabase.execSQL(Items.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    void closeDB() {
        SQLiteDatabase db_ = this.getReadableDatabase();
        if (db_ != null && db_.isOpen())
            db_.close();

        db_ = this.getWritableDatabase();
        if (db_ != null && db_.isOpen())
            db_.close();
    }

    public long insertCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(Customers.COLUMN_CUSTOMER_ID,customer.getCustomerID());
            values.put(Customers.COLUMN_CUSTOMER_NAME,customer.getCustomerName());
            values.put(Customers.COLUMN_SHOP_NAME,customer.getShopName());
            values.put(Customers.COLUMN_ADDRESS,customer.getAddress());
            values.put(Customers.COLUMN_CONTACT_NUMBER,customer.getContactNumber());
            values.put(Customers.COLUMN_AMOUNT_PENDING,customer.getAmountPending());
            values.put(Customers.COLUMN_TOTAL_SALE,customer.getTotalSale());

            return db.insert(Customers.TABLE_NAME,null,values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeDB();
        }
    }

    public List<Customer> getAllCustomer() {
        List<Customer> customerList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + Customers.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(selectQuery,null);
            if (cursor.moveToFirst()) {
                do {
                    Customer customer = new Customer(
                            cursor.getString((cursor.getColumnIndex(Customers.COLUMN_CUSTOMER_NAME))),
                            cursor.getString(cursor.getColumnIndex(Customers.COLUMN_SHOP_NAME)),
                            cursor.getString(cursor.getColumnIndex(Customers.COLUMN_ADDRESS)),
                            cursor.getString(cursor.getColumnIndex(Customers.COLUMN_CONTACT_NUMBER)),
                            cursor.getInt(cursor.getColumnIndex(Customers.COLUMN_AMOUNT_PENDING))
                    );

                    customerList.add(customer);
                } while (cursor.moveToNext());
                cursor.close();
            }
            return customerList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeDB();
        }
    }

    public long insertItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(Items.COLUMN_ITEM_NAME, item.getItemName());
            values.put(Items.COLUMN_STOCK_QUANTITY, item.getStockQuantity());
            values.put(Items.COLUMN_RATE, item.getRate());

            return db.insert(Items.TABLE_NAME,null,values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            Log.e(TAG, "insertItem: "+"insidefinallly");
            closeDB();
        }
    }

    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + Items.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(selectQuery,null);
            if (cursor.moveToFirst()) {
                do {
                    Item item = new Item(
                            cursor.getString((cursor.getColumnIndex(Items.COLUMN_ITEM_NAME))),
                            cursor.getInt(cursor.getColumnIndex(Items.COLUMN_STOCK_QUANTITY)),
                            cursor.getInt(cursor.getColumnIndex(Items.COLUMN_RATE))
                    );

                    itemList.add(item);
                } while (cursor.moveToNext());
                cursor.close();
            }
            return itemList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeDB();
        }
    }
}
