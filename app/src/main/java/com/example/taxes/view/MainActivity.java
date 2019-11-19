package com.example.taxes.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taxes.R;
import com.example.taxes.model.Product;
import com.example.taxes.model.PurchaseViewModel;
import com.example.taxes.view.adapter.AddButtonClickListener;
import com.example.taxes.view.adapter.ProductsListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddButtonClickListener {

    public static final String PRODUCTS_PARCEL = "products_parcel";

    private List<Product> products;
    private PurchaseViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.shopping_list));
        viewModel = new PurchaseViewModel();
        products = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ProductsListAdapter productsListAdapter = new ProductsListAdapter(this, this);
        recyclerView.setAdapter(productsListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        products = new ArrayList<>();
    }

    @Override
    public void addButtonClicked(Product product) {
        String toastMessage = product.getName() + " Added";
        products.add(viewModel.createTaxedProduct(product));
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.check_out_menu_item) {
            if (products.isEmpty()) {
                Toast.makeText(this, getString(R.string.products_empty), Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, CheckOutActivity.class);
                intent.putParcelableArrayListExtra(PRODUCTS_PARCEL, new ArrayList<>(products));
                startActivity(intent);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
