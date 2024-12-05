package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ExpiryProductAdapter extends ArrayAdapter<Product> {

    private final Context context;
    private final List<Product> expiryProductList;

    public ExpiryProductAdapter(Context context, List<Product> expiryProductList) {
        super(context, R.layout.product_item, expiryProductList);
        this.context = context;
        this.expiryProductList = expiryProductList;
    }

    @Override
    public Product getItem(int position) {
        return expiryProductList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView != null ? convertView : LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        Product product = getItem(position);

        TextView productNameText = view.findViewById(R.id.product_name);
        TextView expiryText = view.findViewById(R.id.expiry_date);
        TextView categoryText = view.findViewById(R.id.product_category);

        productNameText.setText(product.getName());
        expiryText.setText("Expires: " + product.getExpiryDate());
        categoryText.setText("Category: " + product.getCategory());

        return view;
    }
}
