package ameya.com.mauligul;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ameya.com.mauligul.database.models.Item;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerItemViewHolder> {
    private ArrayList<Item> myList;
    private Context context;

    int mLastPosition = 0;

    public class RecyclerItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewItemName;
        private final TextView textViewQuantity;
        private TextView textViewRate;
        private TextView textViewAmount;

        public RecyclerItemViewHolder(final View parent) {
            super(parent);
            textViewItemName = (TextView) parent.findViewById(R.id.item_name);
            textViewQuantity = (TextView) parent.findViewById(R.id.quantity);
            textViewRate = (TextView) parent.findViewById(R.id.rate);
            textViewAmount = (TextView) parent.findViewById(R.id.amount);
        }
    }

    public RecyclerAdapter(Context context, ArrayList<Item> myList) {
        this.myList = myList;
        this.context = context;
    }

    public RecyclerItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_row, parent, false);
        RecyclerItemViewHolder holder = new RecyclerItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerItemViewHolder holder, final int position) {


        Log.d("onBindViewHoler ", myList.size() + "");
        holder.textViewItemName.setText(myList.get(position).getItemName());
        holder.textViewQuantity.setText(String.valueOf(myList.get(position).getStockQuantity()));
        holder.textViewRate.setText(String.valueOf(myList.get(position).getRate()));
        holder.textViewAmount.setText(String.valueOf(myList.get(position).getStockQuantity()*myList.get(position).getRate()));
        mLastPosition =position;
    }

    @Override
    public int getItemCount() {
        return(null != myList?myList.size():0);
    }

    public void notifyData(ArrayList<Item> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.myList = myList;
        notifyDataSetChanged();
    }


}
