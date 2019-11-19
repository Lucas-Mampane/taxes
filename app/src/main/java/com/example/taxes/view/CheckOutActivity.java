package com.example.taxes.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taxes.R;
import com.example.taxes.model.CheckOutViewModel;
import com.example.taxes.model.Product;
import com.example.taxes.view.adapter.CheckOutListAdapter;

import java.util.List;

import static com.example.taxes.view.MainActivity.PRODUCTS_PARCEL;

public class CheckOutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.check_out));

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Product> products = getIntent().getParcelableArrayListExtra(PRODUCTS_PARCEL);
        CheckOutListAdapter adapter = new CheckOutListAdapter(products);
        recyclerView.setAdapter(adapter);

        CheckOutViewModel viewModel = new CheckOutViewModel(products);
        TextView taxPrice = findViewById(R.id.sales_tax_value);
        TextView totalValue = findViewById(R.id.total_value);
        taxPrice.setText(viewModel.getTotalTaxValue().toString());
        totalValue.setText(viewModel.getTotalPurchaseValue().toString());
    }
}
