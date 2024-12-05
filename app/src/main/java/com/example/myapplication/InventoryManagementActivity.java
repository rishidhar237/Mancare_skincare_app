package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class InventoryManagementActivity extends AppCompatActivity {

    private EditText productNameInput, expiryDateInput, categoryInput;
    private Button addButton, removeButton;
    private ListView productListView, expiryDateListView;
    private ProductAdapter productAdapter;
    private ExpiryProductAdapter expiryProductAdapter;
    private List<Product> productList;
    private List<Product> expiryProductList;
    private ProductDatabaseHelper dbHelper;  // Database helper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_management);

        // Initialize views
        productNameInput = findViewById(R.id.product_name_input);
        expiryDateInput = findViewById(R.id.expiry_date_input);
        categoryInput = findViewById(R.id.category_input);
        addButton = findViewById(R.id.add_button);
        removeButton = findViewById(R.id.remove_button);
        productListView = findViewById(R.id.product_list_view);
        expiryDateListView = findViewById(R.id.expiry_date_list_view);

        // Initialize database helper
        dbHelper = new ProductDatabaseHelper(this);

        // Initialize lists and adapters
        productList = dbHelper.getNonFinishedProducts();  // Fetch only non-finished products from the database
        expiryProductList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, productList);
        productListView.setAdapter(productAdapter);

        // Create adapter for expiry date list
        expiryProductAdapter = new ExpiryProductAdapter(this, expiryProductList);
        expiryDateListView.setAdapter(expiryProductAdapter);

        // Add button click listener
        addButton.setOnClickListener(v -> addProduct());

        // Remove button click listener
        removeButton.setOnClickListener(v -> removeProduct());

        // Update the Expiry Date Reminder section
        updateExpiryProductList();
    }

    // Add product to the list and database
    private void addProduct() {
        String name = productNameInput.getText().toString();
        String expiryDate = expiryDateInput.getText().toString();
        String category = categoryInput.getText().toString();

        if (name.isEmpty() || expiryDate.isEmpty() || category.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Product product = new Product(name, expiryDate, category);
        dbHelper.addProduct(product);  // Save to the database
        productList.add(product);  // Add to the list
        productAdapter.notifyDataSetChanged();  // Update the adapter

        // Clear the input fields
        productNameInput.setText("");
        expiryDateInput.setText("");
        categoryInput.setText("");

        // Update the expiry date list
        updateExpiryProductList();
    }

    // Remove the first product from the list and database
    private void removeProduct() {
        if (!productList.isEmpty()) {
            Product productToRemove = productList.get(0);
            dbHelper.deleteProduct(productToRemove.getId());  // Delete from the database
            productList.remove(0);  // Remove from the list
            productAdapter.notifyDataSetChanged();  // Update the adapter
        }

        // Update the expiry date list
        updateExpiryProductList();
    }

    // Mark a product as finished in inventory and remove from the list
    public void markAsFinished(int position) {
        Product product = productList.get(position);
        if (!product.isFinished()) {
            // Mark product as finished in the database (affects inventory list)
            product.setFinished(true);
            dbHelper.markProductAsFinished(product.getId());  // Update the database
            productAdapter.notifyDataSetChanged();  // Refresh the adapter

            // Remove product from the product list after marking as finished
            productList.remove(position);
            productAdapter.notifyDataSetChanged();  // Update the list UI

            // Update expiry product list
            updateExpiryProductList();

            // Provide feedback to the user
            Toast.makeText(this, product.getName() + " marked as finished", Toast.LENGTH_SHORT).show();
        }
    }

    // Update the Expiry Product List based on expiry date
    private void updateExpiryProductList() {
        expiryProductList.clear();

        for (Product product : productList) {
            if (!product.isFinished()) {
                expiryProductList.add(product);  // Add only non-finished products
            }
        }

        expiryProductAdapter.notifyDataSetChanged();  // Refresh the list
    }

    // Navigate back to Home Activity with the total product count
    public void navigateToHome() {
        // Get the total product count
        int totalProductCount = productList.size();

        // Pass the total product count to the MainActivity (Home Activity)
        Intent intent = new Intent(InventoryManagementActivity.this, MainActivity.class);
        intent.putExtra("total_product_count", totalProductCount);  // Pass the count as an extra
        startActivity(intent);
    }
}
