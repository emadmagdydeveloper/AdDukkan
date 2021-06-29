package com.addukkan.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.addukkan.R;
import com.addukkan.databinding.AttrRowBinding;
import com.addukkan.models.ProductDataModel;
import com.addukkan.models.SingleProductModel;
import com.addukkan.uis.activity_product_detials.ProductDetialsActivity;

import java.util.ArrayList;
import java.util.List;


public class ProductAttrAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<ProductDataModel.ParentAttributes> list;

    public ProductAttrAdapter(Context context, List<ProductDataModel.ParentAttributes> list) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
    }

    public ProductAttrAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = new ArrayList<>();

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        AttrRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.attr_row, parent, false);
        return new MyHolder(binding);


    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        myHolder.binding.setTitle(list.get(position).getAttribute_trans_fk().getTitle());
        if (list.get(position).getAttributes()!=null){
            myHolder.binding.recView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
            ChildAdapter adapter = new ChildAdapter(context,list.get(position).getAttributes());
            myHolder.binding.recView.setAdapter(adapter);
        }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        private AttrRowBinding binding;

        public MyHolder(AttrRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }

    }
}
