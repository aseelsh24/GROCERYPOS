package com.yourcompany.grocerypos.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class Product {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String barcode;
    public double sellingPrice;
    public int stockQuantity;

    // You can add other fields here: costPrice, category, expiryDate, etc.
}
