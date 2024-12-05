package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity3 extends AppCompatActivity {

    private ImageView productImageView;
    private TextView productDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_3); // Ensure this matches the XML layout name

        // Initialize the views using the correct IDs
        productImageView = findViewById(R.id.product_image_view_3); // Correct ID
        productDescriptionTextView = findViewById(R.id.product_description_text_view_3); // Correct ID

        // Set the content for the third product
        productImageView.setImageResource(R.drawable.img_2); // Ensure img_2 exists in drawable
        productDescriptionTextView.setText(
                "Sunscreen is an essential part of a skincare routine. It can help prevent premature aging "
                        + "and skin damage that may lead to skin cancer. However, no sunscreen blocks UV radiation 100%, "
                        + "so you should still be careful not to stay in the sun for too long.\n\n"
                        + "Here are some tips for using sunscreen:\n\n"
                        + "1. Apply sunscreen generously to all exposed skin 30 minutes before sun exposure.\n"
                        + "2. Reapply sunscreen after swimming, sweating, or drying off with a towel.\n"
                        + "3. Reapply sunscreen every 2 hours if you are outside for long periods.\n"
                        + "4. Avoid contact with the eyes.\n"
                        + "5. Use cautiously or avoid use on irritated skin.\n"
                        + "6. Do not use sunscreen on infants younger than 6 months unless directed by a doctor."
        );
    }
}
