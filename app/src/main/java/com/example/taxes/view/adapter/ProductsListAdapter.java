package com.example.taxes.view.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taxes.R;
import com.example.taxes.model.Product;
import com.example.taxes.model.ProductType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductsListAdapter extends RecyclerView.Adapter<ProductsListAdapter.ProductsViewHolder> {
    private List<Product> products = new ArrayList<>();
    private List<Drawable> images = new ArrayList<>();
    private AddButtonClickListener addButtonClickListener;

    public ProductsListAdapter(Context context, AddButtonClickListener addButtonClickListener) {
        this.addButtonClickListener = addButtonClickListener;

        products.add(new Product("Book", ProductType.BOOK, false, BigDecimal.valueOf(12.49)));
        products.add(new Product("Music CD", ProductType.GENERAL, false, BigDecimal.valueOf(14.99)));
        products.add(new Product("Chocolate Bar", ProductType.FOOD, false, BigDecimal.valueOf(0.85)));
        products.add(new Product("Imported Box Chocolates", ProductType.FOOD, true, BigDecimal.valueOf(10.00)));
        products.add(new Product("Local Bottle Perfume", ProductType.GENERAL, false, BigDecimal.valueOf(18.99)));
        products.add(new Product("Imported Bottle Perfume", ProductType.GENERAL, true, BigDecimal.valueOf(47.50)));
        products.add(new Product("Cheap Imported Bottle Perfume", ProductType.GENERAL, true, BigDecimal.valueOf(27.99)));
        products.add(new Product("Packet Headache Pills", ProductType.MEDICAL, false, BigDecimal.valueOf(9.75)));

        images.add(context.getDrawable(R.drawable.book));
        images.add(context.getDrawable(R.drawable.music_cd));
        images.add(context.getDrawable(R.drawable.bar_choc));
        images.add(context.getDrawable(R.drawable.box_choc));
        images.add(context.getDrawable(R.drawable.perfume));
        images.add(context.getDrawable(R.drawable.perfume));
        images.add(context.getDrawable(R.drawable.perfume));
        images.add(context.getDrawable(R.drawable.pills));
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item, viewGroup, false);
        return new ProductsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder productsViewHolder, final int i) {
        productsViewHolder.productName.setText(products.get(i).getName());
        productsViewHolder.productImage.setImageDrawable(images.get(i));
        String displayPrice = "Price: " + products.get(i).getOriginalPrice().toString();
        productsViewHolder.productPrice.setText(displayPrice);
        productsViewHolder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButtonClickListener.addButtonClicked(products.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductsViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productPrice;
        ImageView productImage;
        Button addButton;


        ProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            productImage = itemView.findViewById(R.id.product_image);
            productPrice = itemView.findViewById(R.id.product_price);
            addButton = itemView.findViewById(R.id.add_button);
        }
    }
}
