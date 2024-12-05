package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "UserPrefs"; // SharedPreferences file name
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_PHOTO = "photo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Find views
        ImageView profileImageView = findViewById(R.id.profile_image);
        EditText nameEditText = findViewById(R.id.name);
        EditText emailEditText = findViewById(R.id.email);
        EditText phoneEditText = findViewById(R.id.phone);
        Button saveButton = findViewById(R.id.save_button);
        Button editPhotoButton = findViewById(R.id.edit_photo_button);

        // Load saved user data if available
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        nameEditText.setText(sharedPreferences.getString(KEY_NAME, ""));
        emailEditText.setText(sharedPreferences.getString(KEY_EMAIL, ""));
        phoneEditText.setText(sharedPreferences.getString(KEY_PHONE, ""));

        // Set the saved profile photo (just an example, you'll implement the photo update logic)
        // If you have a URL or URI for the photo, you can use an image loading library like Glide or Picasso.
        // profileImageView.setImageURI(Uri.parse(sharedPreferences.getString(KEY_PHOTO, "")));

        // Handle photo editing
        editPhotoButton.setOnClickListener(v -> {
            // Logic to choose a new photo (e.g., open gallery or take a picture)
            // For now, we'll just show a placeholder message
            Toast.makeText(EditProfileActivity.this, "Choose a new photo", Toast.LENGTH_SHORT).show();
        });

        // Save button to save the information
        saveButton.setOnClickListener(v -> {
            // Get user input
            String name = nameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String phone = phoneEditText.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                Toast.makeText(EditProfileActivity.this, "Please fill out all fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save the information in SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_NAME, name);
            editor.putString(KEY_EMAIL, email);
            editor.putString(KEY_PHONE, phone);
            // Save photo URI (if any) in the future when implemented
            // editor.putString(KEY_PHOTO, "photoUri");

            editor.apply(); // Commit changes

            Toast.makeText(EditProfileActivity.this, "Profile updated successfully!", Toast.LENGTH_SHORT).show();

            // Optionally, return to the previous screen
            finish();
        });
    }
}
