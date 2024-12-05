package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button productInfoButton, editProfileButton, inventoryButton;
    private TextView progressTextView;
    private ListView reminderListView;
    private ReminderAdapter reminderAdapter;
    private List<Product> reminderProductList;
    private ImageView funImageView;  // ImageView for displaying fun.png

    private ProductDatabaseHelper dbHelper;

    // Variable to hold the total product count and completed product count
    private int totalProductCount = 0;
    private int completedProductCount = 0;

    // Variable to hold the count for "Done" button clicks
    private int temp_count = 0;  // New variable for tracking the count

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        editProfileButton = findViewById(R.id.edit_profile_button);
        inventoryButton = findViewById(R.id.inventory_button);
        productInfoButton = findViewById(R.id.product_info_button); // New button for Product Info
        progressTextView = findViewById(R.id.progress_text_view);
        reminderListView = findViewById(R.id.reminder_list_view);
        funImageView = findViewById(R.id.fun_image_view); // Initialize the ImageView

        // Initialize ProductDatabaseHelper
        dbHelper = new ProductDatabaseHelper(this);

        reminderProductList = new ArrayList<>();

        // Set up the reminder section
        reminderAdapter = new ReminderAdapter(this, reminderProductList);
        reminderListView.setAdapter(reminderAdapter);

        // Receive the total product count from InventoryManagementActivity
        totalProductCount = getIntent().getIntExtra("total_product_count", 0);

        // Fetch only unfinished products and update progress
        fetchUnfinishedProductsFromDatabase();

        // Update the progress text
        updateProgressText();

        // Buttons' click listeners
        editProfileButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);
            startActivity(intent);
        });

        inventoryButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InventoryManagementActivity.class);
            startActivity(intent);
        });

        // New button listener for Product Info
        productInfoButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProductInfoActivity.class);
            startActivity(intent);
        });
    }

    // Fetch only unfinished products from the database
    private void fetchUnfinishedProductsFromDatabase() {
        List<Product> unfinishedProductsFromDb = dbHelper.getNonFinishedProducts(); // Fetch only unfinished products
        reminderProductList.clear();
        reminderProductList.addAll(unfinishedProductsFromDb);

        // Update the count of completed products
        completedProductCount = dbHelper.getFinishedProductCount(); // Fetch the count of finished products

        // Notify the adapter to update the list view
        reminderAdapter.notifyDataSetChanged();
    }

    // Update the progress text based on the number of completed items and total products in the inventory
    private void updateProgressText() {
        // Get the number of unfinished products
        int unfinishedItemCount = reminderProductList.size();

        // Update the progress text to show "temp_count of daily tasks"
        progressTextView.setText(temp_count + " of " + unfinishedItemCount + " daily tasks");

        // Show the image if temp_count matches the unfinished task count
        if (temp_count == unfinishedItemCount) {
            funImageView.setVisibility(ImageView.VISIBLE); // Show the image
        } else {
            funImageView.setVisibility(ImageView.GONE); // Hide the image
        }
    }

    // Method to handle the "Done" button click (increments temp_count)
    public void incrementTempCount() {
        temp_count++;  // Increment the temp_count variable
        // Update the progress text after incrementing temp_count
        updateProgressText();
    }
}
