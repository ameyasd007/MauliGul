package ameya.com.mauligul.database;

import android.provider.BaseColumns;

/**
 * Created by Admin on 08-Jul-18.
 */

public final class DBContract {

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String PRIMARY_KEY = " PRIMARY KEY";
    private static final String COMMA_SEP = ",";

    private DBContract() {}

    public static final class Customers implements BaseColumns {
        public static final String TABLE_NAME = "Customers";
        public static final String COLUMN_CUSTOMER_NAME = "CustomerName";
        public static final String COLUMN_CUSTOMER_ID = "CustomerId";
        public static final String COLUMN_SHOP_NAME = "ShopName";
        public static final String COLUMN_ADDRESS = "Address";
        public static final String COLUMN_CONTACT_NUMBER = "ContactNumber";
        public static final String COLUMN_AMOUNT_PENDING = "AmountPending";
        public static final String COLUMN_TOTAL_SALE = "TotalSale";

        public static String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + "(" +
                COLUMN_CUSTOMER_ID + TEXT_TYPE + PRIMARY_KEY + COMMA_SEP +
                COLUMN_CUSTOMER_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_SHOP_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_ADDRESS + TEXT_TYPE + COMMA_SEP +
                COLUMN_CONTACT_NUMBER + TEXT_TYPE + COMMA_SEP +
                COLUMN_AMOUNT_PENDING + INTEGER_TYPE + COMMA_SEP +
                COLUMN_TOTAL_SALE + INTEGER_TYPE +
                ")";
    }

    public static final class Items implements BaseColumns {
        public static final String TABLE_NAME = "Items";
        public static final String COLUMN_ITEM_NAME = "ItemName";
        public static final String COLUMN_STOCK_QUANTITY = "StockQuantity";
        public static final String COLUMN_RATE = "Rate";

        public static String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + "(" +
                COLUMN_ITEM_NAME + TEXT_TYPE + PRIMARY_KEY + COMMA_SEP +
                COLUMN_STOCK_QUANTITY + INTEGER_TYPE + COMMA_SEP +
                COLUMN_RATE + INTEGER_TYPE +
                ")";
    }
}
