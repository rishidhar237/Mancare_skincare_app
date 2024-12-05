package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productinfoactivity);  // Ensure the layout file is correct

        // Scrollable layout containing images
        ScrollView scrollView = findViewById(R.id.scroll_view);  // Ensure scroll_view exists in the layout
        LinearLayout imageContainer = findViewById(R.id.image_container);  // Image container for dynamic images

        // Add 3 images dynamically to the container
        for (int i = 0; i < 3; i++) {  // Display 3 images
            ImageView imageView = new ImageView(this);

            // Set images based on the index
            if (i == 0) {
                imageView.setImageResource(R.drawable.img_1);  // First product image
            } else if (i == 1) {
                imageView.setImageResource(R.drawable.img);  // Second product image
            } else {
                imageView.setImageResource(R.drawable.img_2);  // Third product image
            }

            imageView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));

            // Set click listener for each image to open the respective product detail activity
            final int productId = i;  // Using the loop index as productId to differentiate
            imageView.setOnClickListener(v -> {
                Intent intent = null;

                // Determine the activity to open based on the clicked image
                if (productId == 0) {
                    intent = new Intent(ProductInfoActivity.this, ProductDetailActivity1.class);
                } else if (productId == 1) {
                    intent = new Intent(ProductInfoActivity.this, ProductDetailActivity2.class);
                } else if (productId == 2) {
                    intent = new Intent(ProductInfoActivity.this, ProductDetailActivity3.class);
                }

                // Start the intent to open the corresponding product detail activity
                startActivity(intent);
            });

            imageContainer.addView(imageView);  // Add the image to the container
        }
    }
}
