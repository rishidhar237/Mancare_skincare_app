package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import java.util.List;

public class ReminderAdapter extends ArrayAdapter<Product> {

    private final Context context;
    private final List<Product> productList;

    public ReminderAdapter(Context context, List<Product> productList) {
        super(context, R.layout.product_item, productList);
        this.context = context;
        this.productList = productList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView != null ? convertView : LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        Product product = getItem(position);

        TextView productNameText = view.findViewById(R.id.product_name);
        TextView expiryText = view.findViewById(R.id.expiry_date);
        TextView categoryText = view.findViewById(R.id.product_category);
        Button doneButton = view.findViewById(R.id.done_button); // "Done" button

        productNameText.setText(product.getName());
        expiryText.setText("Expires: " + product.getExpiryDate());
        categoryText.setText("Category: " + product.getCategory());

        // If the product is finished, hide the "Done" button, otherwise show it
        if (product.isFinished()) {
            doneButton.setVisibility(View.GONE);
        } else {
            doneButton.setVisibility(View.VISIBLE);
            doneButton.setOnClickListener(v -> {
                // Mark the product as finished
                product.setFinished(true);

                // Hide the button and update progress count
                doneButton.setVisibility(View.GONE);

                // Update the progress count in MainActivity
                if (context instanceof MainActivity) {
                    ((MainActivity) context).incrementTempCount();
                }

                // Notify the adapter to refresh the list view
                notifyDataSetChanged();
            });
        }

        return view;
    }
}
