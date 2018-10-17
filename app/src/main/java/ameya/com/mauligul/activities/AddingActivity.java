package ameya.com.mauligul.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import ameya.com.mauligul.R;
import ameya.com.mauligul.database.DatabaseHelper;
import ameya.com.mauligul.database.models.Customer;
import ameya.com.mauligul.database.models.Item;

import static java.lang.Integer.parseInt;

public class AddingActivity extends AppCompatActivity {

    private static final String TAG = "AddingActivity";
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        String activityName = extras.getString("activity");

        switch (activityName){
            case "customer" :
                setContentView(R.layout.activity_add_customer);
                break;
            case "item" :
                setContentView(R.layout.activity_add_item);
                break;
        }
        databaseHelper = new DatabaseHelper(getApplicationContext());
    }

    public void saveCustomer(View view) {
        ArrayList<String> values = new ArrayList<String>();
        int[] ids = new int[]{R.id.customer_name,R.id.shop_name,R.id.address,R.id.contact_number,R.id.amount_pending};
        for(int id : ids) values.add(((EditText)findViewById(id)).getText().toString());
        Customer customer = new Customer(values.get(0),values.get(1),values.get(2),values.get(3), parseInt(values.get(4)));
        Long result = databaseHelper.insertCustomer(customer);
        Toast.makeText(this,(result>0)?"Customer Added Successfully":"Failed to add Customer",Toast.LENGTH_SHORT).show();
        Log.e(TAG, "saveCustomer: "+ result);
        if (result>0)
            finish();
    }


    public void saveItem(View view) {
        ArrayList<String> values = new ArrayList<String>();
        int[] ids = new int[]{R.id.item_name,R.id.quantity_stock,R.id.rate_initial};
        for(int id : ids) values.add(((EditText)findViewById(id)).getText().toString());
        Item item = new Item(values.get(0),parseInt(values.get(1)),parseInt(values.get(2)));
        Long result = databaseHelper.insertItem(item);
        Toast.makeText(this,(result>0)?"Item Added Successfully":"Failed to add Item",Toast.LENGTH_SHORT).show();
        Log.e(TAG, "saveItem: "+ result);
        if (result>0)
            finish();
    }
}
