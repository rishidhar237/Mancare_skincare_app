package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ProductDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "inventory_db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_PRODUCTS = "products";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EXPIRY_DATE = "expiryDate";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_IS_FINISHED = "isFinished";

    public ProductDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_PRODUCTS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_EXPIRY_DATE + " TEXT, " +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_IS_FINISHED + " INTEGER)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    // Add product to the database
    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, product.getName());
        values.put(COLUMN_EXPIRY_DATE, product.getExpiryDate());
        values.put(COLUMN_CATEGORY, product.getCategory());
        values.put(COLUMN_IS_FINISHED, product.isFinished() ? 1 : 0);
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    // Get all products from the database
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Define the columns you want to retrieve
        String[] columns = {COLUMN_ID, COLUMN_NAME, COLUMN_EXPIRY_DATE, COLUMN_CATEGORY, COLUMN_IS_FINISHED};

        // Query to get all products
        Cursor cursor = db.query(TABLE_PRODUCTS, columns, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                // Get column indexes by column names
                int idIndex = cursor.getColumnIndex(COLUMN_ID);
                int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
                int expiryDateIndex = cursor.getColumnIndex(COLUMN_EXPIRY_DATE);
                int categoryIndex = cursor.getColumnIndex(COLUMN_CATEGORY);
                int isFinishedIndex = cursor.getColumnIndex(COLUMN_IS_FINISHED);

                if (idIndex >= 0 && nameIndex >= 0 && expiryDateIndex >= 0 && categoryIndex >= 0 && isFinishedIndex >= 0) {
                    int id = cursor.getInt(idIndex);
                    String name = cursor.getString(nameIndex);
                    String expiryDate = cursor.getString(expiryDateIndex);
                    String category = cursor.getString(categoryIndex);
                    boolean isFinished = cursor.getInt(isFinishedIndex) == 1;

                    productList.add(new Product(id, name, expiryDate, category, isFinished));
                }
            }
            cursor.close();
        }
        db.close();
        return productList;
    }

    // Get only non-finished products
    public List<Product> getNonFinishedProducts() {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_NAME, COLUMN_EXPIRY_DATE, COLUMN_CATEGORY, COLUMN_IS_FINISHED};

        // Query to get only non-finished products
        Cursor cursor = db.query(TABLE_PRODUCTS, columns, COLUMN_IS_FINISHED + " = 0", null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int idIndex = cursor.getColumnIndex(COLUMN_ID);
                int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
                int expiryDateIndex = cursor.getColumnIndex(COLUMN_EXPIRY_DATE);
                int categoryIndex = cursor.getColumnIndex(COLUMN_CATEGORY);
                int isFinishedIndex = cursor.getColumnIndex(COLUMN_IS_FINISHED);

                if (idIndex >= 0 && nameIndex >= 0 && expiryDateIndex >= 0 && categoryIndex >= 0 && isFinishedIndex >= 0) {
                    int id = cursor.getInt(idIndex);
                    String name = cursor.getString(nameIndex);
                    String expiryDate = cursor.getString(expiryDateIndex);
                    String category = cursor.getString(categoryIndex);
                    boolean isFinished = cursor.getInt(isFinishedIndex) == 1;

                    productList.add(new Product(id, name, expiryDate, category, isFinished));
                }
            }
            cursor.close();
        }
        db.close();
        return productList;
    }

    // Get finished products
    public List<Product> getFinishedProducts() {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_NAME, COLUMN_EXPIRY_DATE, COLUMN_CATEGORY, COLUMN_IS_FINISHED};

        // Query to get only finished products
        Cursor cursor = db.query(TABLE_PRODUCTS, columns, COLUMN_IS_FINISHED + " = 1", null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int idIndex = cursor.getColumnIndex(COLUMN_ID);
                int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
                int expiryDateIndex = cursor.getColumnIndex(COLUMN_EXPIRY_DATE);
                int categoryIndex = cursor.getColumnIndex(COLUMN_CATEGORY);
                int isFinishedIndex = cursor.getColumnIndex(COLUMN_IS_FINISHED);

                if (idIndex >= 0 && nameIndex >= 0 && expiryDateIndex >= 0 && categoryIndex >= 0 && isFinishedIndex >= 0) {
                    int id = cursor.getInt(idIndex);
                    String name = cursor.getString(nameIndex);
                    String expiryDate = cursor.getString(expiryDateIndex);
                    String category = cursor.getString(categoryIndex);
                    boolean isFinished = cursor.getInt(isFinishedIndex) == 1;

                    productList.add(new Product(id, name, expiryDate, category, isFinished));
                }
            }
            cursor.close();
        }
        db.close();
        return productList;
    }

    // Delete a product by ID
    public void deleteProduct(int productId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, COLUMN_ID + " = ?", new String[]{String.valueOf(productId)});
        db.close();
    }

    // Update product status to finished
    public void markProductAsFinished(int productId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IS_FINISHED, 1);
        db.update(TABLE_PRODUCTS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(productId)});
        db.close();
    }

    // Get the count of finished products
    public int getFinishedProductCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT COUNT(*) FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_IS_FINISHED + " = 1";
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = 0;
        if (cursor != null) {
            cursor.moveToFirst();
            count = cursor.getInt(0);
            cursor.close();
        }
        db.close();
        return count;
    }

    // Get the total number of products
    public int getTotalProductCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT COUNT(*) FROM " + TABLE_PRODUCTS;
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = 0;
        if (cursor != null) {
            cursor.moveToFirst();
            count = cursor.getInt(0);
            cursor.close();
        }
        db.close();
        return count;
    }
}
