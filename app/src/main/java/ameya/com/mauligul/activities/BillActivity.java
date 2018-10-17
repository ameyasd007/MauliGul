package ameya.com.mauligul.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.DateFormatSymbols;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ameya.com.mauligul.R;
import ameya.com.mauligul.RecyclerAdapter;
import ameya.com.mauligul.database.DatabaseHelper;
import ameya.com.mauligul.database.models.Customer;
import ameya.com.mauligul.database.models.Item;

public class BillActivity extends AppCompatActivity {
//    First Change
    private static final String TAG = "BillActivity";
    private DatabaseHelper databaseHelper;
    EditText editText;
    Context context;
    Calendar date;
    Item item;
    boolean isItemSet;
    List<Item> itemList;
    String dateTime="";
    private int mYear, mMonth, mDay, mHour, mMinute;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Item> myList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        context = this;
        editText = (EditText) findViewById(R.id.bill_date_time);
        editText.setText(DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.SHORT).format(new Date()));

        myList = new ArrayList<>();

        databaseHelper = new DatabaseHelper(context);

        List<String> customerNames = new ArrayList<>();
        List<Customer> customerList=databaseHelper.getAllCustomer();
        for (Customer customer:customerList)
            customerNames.add(customer.getCustomerName());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, customerNames);
        AutoCompleteTextView autoCompleteCustomerList = (AutoCompleteTextView) findViewById(R.id.auto_complete_tv_customer_name);
        autoCompleteCustomerList.setAdapter(adapter);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_bill_items);
        mRecyclerAdapter = new RecyclerAdapter(this, myList);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRecyclerAdapter);

}

    public void openDatePicker(View view) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateTime=new DateFormatSymbols().getMonths()[monthOfYear].substring(0,3) + " " + dayOfMonth + ", " + year;
//                        editText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        String am_pm = (hourOfDay<12)?"AM":"PM";
                                        dateTime=dateTime + " " + hourOfDay + ":" + minute + " " + am_pm;
                                        editText.setText(dateTime);
                                    }
                                }, mHour, mMinute, false);
                        timePickerDialog.show();
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

//        final Calendar currentDate = Calendar.getInstance();
//        date = Calendar.getInstance();
//        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                date.set(year, monthOfYear, dayOfMonth);
//                new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                        date.set(Calendar.MINUTE, minute);
//                        editText.setText(date.getTime().toString());
//                        Log.v(TAG, "The choosen one " + date.getTime());
//                    }
//                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
//            }
//        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

    public void addItemDialog(View view) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog_add_item);

        isItemSet = false;

        final EditText editTextRate = (EditText) dialog.findViewById(R.id.edit_text_rate);
        final EditText editTextQuantity = (EditText) dialog.findViewById(R.id.edit_text_quantity);
        Button buttonAddItem = (Button) dialog.findViewById(R.id.btn_add_item_confirm);

        List<String> itemNames = new ArrayList<>();
        itemList=databaseHelper.getAllItems();
        for (Item item1:itemList)
            itemNames.add(item1.getItemName());



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, itemNames);

        final AutoCompleteTextView autoCompleteTextViewItemList = (AutoCompleteTextView) dialog.findViewById(R.id.auto_complete_items);
        autoCompleteTextViewItemList.setAdapter(adapter);

        autoCompleteTextViewItemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (Item itemNew:itemList) {
                    if (itemNew.getItemName()==parent.getItemAtPosition(position))
                        item = itemNew;
                }
                editTextRate.setText(String.valueOf(item.getRate()));
                isItemSet = true;
            }
        });


        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isItemSet&&!editTextQuantity.getText().toString().isEmpty()&&!editTextRate.getText().toString().isEmpty())
                {
                    item.setStockQuantity(Integer.parseInt(editTextQuantity.getText().toString()));
                    myList.add(item);
                    mRecyclerAdapter.notifyData(myList);
                    dialog.dismiss();
                }
                else
                    Toast.makeText(BillActivity.this,"Please fill all the details", Toast.LENGTH_SHORT).show();
            }
        });


        dialog.show();


    }
}
