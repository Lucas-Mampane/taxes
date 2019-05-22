package com.example.taxes.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taxes.R;
import com.example.taxes.model.Product;

import java.util.List;

public class CheckOutListAdapter extends RecyclerView.Adapter<CheckOutListAdapter.CheckOutViewHolder> {

    private List<Product> products;

    public CheckOutListAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public CheckOutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.check_out_item, viewGroup, false);
        return new CheckOutViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckOutViewHolder checkOutViewHolder, int i) {
        checkOutViewHolder.productName.setText(products.get(i).getName());
        if (products.get(i).getTaxedPrice() != null) {
            checkOutViewHolder.productTaxedPrice.setText(products.get(i).getTaxedPrice().toString());
        } else {
            checkOutViewHolder.productTaxedPrice.setText(products.get(i).getOriginalPrice().toString());
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class CheckOutViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productTaxedPrice;

        public CheckOutViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            productTaxedPrice = itemView.findViewById(R.id.product_taxed_price);
        }
    }
}
