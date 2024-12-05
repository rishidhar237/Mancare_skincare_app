package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity1 extends AppCompatActivity {

    private ImageView productImageView1;  // Updated to use product_image_view_1
    private TextView productDescriptionTextView1;  // Updated to use product_description_text_view_1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_1);

        // Bind the views using the correct IDs from XML
        productImageView1 = findViewById(R.id.product_image_view_1);
        productDescriptionTextView1 = findViewById(R.id.product_description_text_view_1);

        // Set the content for the first product
        productImageView1.setImageResource(R.drawable.img_1); // Set the image for product 1
        productDescriptionTextView1.setText(
                "A face wash is a specially formulated cleanser designed to gently remove dirt, oil, makeup, "
                        + "and impurities from your face. Typically, you apply a small amount to wet skin, massage it in gently, "
                        + "and then rinse thoroughly with lukewarm water. It's important to choose a face wash suitable for your skin "
                        + "type to avoid dryness or irritation.\n\n"
                        + "How to use a face wash:\n\n"
                        + "1. **Clean hands**: Always start with clean hands.\n"
                        + "2. **Wet your face**: Use lukewarm water to dampen your face.\n"
                        + "3. **Apply a small amount**: Dispense a small amount of face wash onto your palms.\n"
                        + "4. **Massage gently**: Work the face wash into a lather and massage it onto your face, including your forehead, "
                        + "nose, and chin, using circular motions.\n"
                        + "5. **Avoid eyes and mouth**: Be careful not to get the face wash in your eyes or mouth.\n"
                        + "6. **Rinse thoroughly**: Rinse your face with lukewarm water until all traces of the cleanser are removed.\n"
                        + "7. **Pat dry**: Pat your face dry with a clean, soft towel."
        );
    }
}
