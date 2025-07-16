package com.yourcompany.grocerypos.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yourcompany.grocerypos.R;
import com.yourcompany.grocerypos.adapters.ProductAdapter;
import com.yourcompany.grocerypos.viewmodels.ProductViewModel; // We will create this next

public class MainActivity extends AppCompatActivity {

    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewProducts);
        final ProductAdapter adapter = new ProductAdapter();
        recyclerView.setAdapter(adapter);

        // Get a new or existing ViewModel from the ViewModelProvider.
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        // Add an observer on the LiveData returned by getAllProducts.
        productViewModel.getAllProducts().observe(this, products -> {
            // Update the cached copy of the products in the adapter.
            adapter.setProducts(products);
        });

        FloatingActionButton fab = findViewById(R.id.fabAddProduct);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddProductActivity.class);
            startActivity(intent);
        });
    }
}