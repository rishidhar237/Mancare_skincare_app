package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    private final Context context;
    private final List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        super(context, R.layout.product_item, productList);
        this.context = context;
        this.productList = productList;
    }

    @Override
    public Product getItem(int position) {
        return productList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView != null ? convertView : LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        Product product = getItem(position);

        TextView productNameText = view.findViewById(R.id.product_name);
        TextView expiryText = view.findViewById(R.id.expiry_date);
        TextView categoryText = view.findViewById(R.id.product_category);
        Button markAsFinishedButton = view.findViewById(R.id.mark_as_finished_button);

        productNameText.setText(product.getName());
        expiryText.setText("Expires: " + product.getExpiryDate());
        categoryText.setText("Category: " + product.getCategory());

        // Show "Mark as Finished" button only for products that are not finished
        markAsFinishedButton.setVisibility(product.isFinished() ? View.GONE : View.VISIBLE);
        markAsFinishedButton.setOnClickListener(v -> {
            // Call the activity method to mark the product as finished
            if (context instanceof InventoryManagementActivity) {
                ((InventoryManagementActivity) context).markAsFinished(position);  // Activity handles the logic
            }
            markAsFinishedButton.setVisibility(View.GONE);  // Hide the button after marking as finished
        });

        return view;
    }
}
