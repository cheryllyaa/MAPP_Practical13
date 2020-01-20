package edu.sp.p1737721.mapp_practical13;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {
    private JSONObject items;
    LayoutInflater inflater;
    private JSONArray names;
    private Context ctx;


    public ProductListAdapter(Context ctx, JSONObject obj) {
        //store ref to loaded jsonarray from internet
        // items = obj;
        setItems(obj);

        // get inflater for later use
        this.ctx = ctx;
        inflater = LayoutInflater.from(ctx);
    }

    public JSONObject getItems() {
        return items;
    }

    public void setItems(JSONObject items) {
        this.items = items;
        // store names for easy use later
        names = items.names();
    }

    @NonNull
    @Override
    public ProductListAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate xml layout for single use
        View itemView = inflater.inflate(R.layout.recycler_item, parent, false);
        // create new holder and view it
        return new ProductViewHolder(itemView, this);
        // return null
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ProductViewHolder holder, int position) {
        // retrieve data from array
        try {
            JSONObject obj = items.getJSONObject(names.getString(position));
            // display name in textview
            holder.tv.setText(obj.getString("name"));
        } catch (Exception e) {
            Log.e("ProductListAdapter", e.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return items.length();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv;
        ProductListAdapter mAdapter;

        public ProductViewHolder(View itemView, ProductListAdapter adapter) {
            super(itemView);
            // get text view for later
            tv = itemView.findViewById(R.id.word);
            mAdapter = adapter;
            // set click listener
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            try {
                // get json obj at clicked position
                JSONObject obj = items.getJSONObject(names.getString(this.getAdapterPosition()));
                
                // print details from json obj in logcat
     /*           Log.d("ProductViewHolder", "Clicked: " +
                        this.getAdapterPosition() + " - " +
                        obj.getString("name") + " $ " +
                        obj.getString("price"));*/

                String name = obj.getString("name");
                String phonename = name.toString();
                String  price = obj.getString("price");
                String phoneprice = price.toString();

               // use toast to show
                Toast.makeText(ctx,
                        "Phone: " + phonename + " - "
                        + "Price: " + "$" + phoneprice,
                        Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Log.e("ProductViewHolder", e.getMessage());
            }
        }

    }
}
