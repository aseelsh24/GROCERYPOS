package com.yourcompany.grocerypos.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.yourcompany.grocerypos.R;
import com.yourcompany.grocerypos.models.Product;
import com.yourcompany.grocerypos.viewmodels.ProductViewModel;

public class AddProductActivity extends AppCompatActivity {

    private EditText editTextName, editTextBarcode, editTextPrice, editTextStock;
    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        editTextName = findViewById(R.id.editTextProductName);
        editTextBarcode = findViewById(R.id.editTextBarcode);
        editTextPrice = findViewById(R.id.editTextSellingPrice);
        editTextStock = findViewById(R.id.editTextStockQuantity);

        final Button button = findViewById(R.id.buttonSave);
        button.setOnClickListener(view -> {
            saveProduct();
        });
    }

    private void saveProduct() {
        String name = editTextName.getText().toString().trim();
        String barcode = editTextBarcode.getText().toString().trim();
        String priceStr = editTextPrice.getText().toString().trim();
        String stockStr = editTextStock.getText().toString().trim();

        if (name.isEmpty() || priceStr.isEmpty() || stockStr.isEmpty()) {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Product product = new Product();
        product.name = name;
        product.barcode = barcode;
        product.sellingPrice = Double.parseDouble(priceStr);
        product.stockQuantity = Integer.parseInt(stockStr);

        productViewModel.insert(product);
        Toast.makeText(this, "Product saved", Toast.LENGTH_SHORT).show();
        finish(); // Go back to the main activity
    }
}