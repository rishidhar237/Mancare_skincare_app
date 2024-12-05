package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity2 extends AppCompatActivity {

    private ImageView productImageView2;  // Updated variable name for product 2
    private TextView productDescriptionTextView2;  // Updated variable name for product 2 description

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_2);

        // Bind the views using the correct IDs for product 2
        productImageView2 = findViewById(R.id.product_image_view_2);
        productDescriptionTextView2 = findViewById(R.id.product_description_text_view_2);

        // Set the content for the second product
        productImageView2.setImageResource(R.drawable.img); // Set the image for product 2
        productDescriptionTextView2.setText(
                "A moisturizer is a topical product designed to hydrate the skin by adding water to its outer layer "
                        + "and creating a barrier to lock it in, preventing dryness. To apply, cleanse your face, then gently "
                        + "massage a small amount of moisturizer into damp skin using your fingertips, ensuring you cover your "
                        + "entire face, including the neck and hairline, with upward strokes for optimal absorption.\n\n"
                        + "Key points about moisturizer application:\n\n"
                        + "1. **Clean skin**: Always apply moisturizer to clean, freshly washed skin.\n"
                        + "2. **Damp skin**: For best results, apply moisturizer to slightly damp skin to trap moisture.\n"
                        + "3. **Small amount**: Use a small amount of product, usually a pea-sized dollop for your face, to avoid feeling greasy.\n"
                        + "4. **Gentle application**: Massage the moisturizer into your skin using light, upward circular motions with your fingertips.\n"
                        + "5. **Targeted areas**: Don't forget to apply moisturizer to your neck, around the eyes, and hairline.\n\n"
                        + "Choosing the right moisturizer:\n\n"
                        + "- **Skin type**: Select a moisturizer formulated specifically for your skin type (dry, oily, combination, sensitive).\n"
                        + "- **Texture**: Consider the texture of the moisturizer, with lighter options for oily skin and richer creams for dry skin."
        );
    }
}
