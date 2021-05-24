package com.addukkan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.addukkan.R;
import com.addukkan.databinding.DuckanCategoryRowBinding;
import com.addukkan.databinding.SideMenuCategoryRowBinding;
import com.addukkan.models.MainCategoryDataModel;
import com.addukkan.uis.activity_home.fragments.FragmenDukkan;

import java.util.List;

import io.paperdb.Paper;

public class DuckanCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String lang;
    private List<MainCategoryDataModel.Data> list;
    private Context context;
    private LayoutInflater inflater;
    private int i=0;
private Fragment fragment;

    //private Fragment_Main fragment_main;
    public DuckanCategoryAdapter(List<MainCategoryDataModel.Data> list, Context context,Fragment fragment) {
        this.list = list;
        this.context = context;
        Paper.init(context);
        lang = Paper.book().read("lang", "ar");
        inflater = LayoutInflater.from(context);
        this.fragment=fragment;
        //  this.fragment_main=fragment_main;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        DuckanCategoryRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.duckan_category_row, parent, false);
        return new MyHolder(binding);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MyHolder myHolder = (MyHolder) holder;
        myHolder.binding.setModel(list.get(position));

myHolder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        i=holder.getLayoutPosition();

        notifyDataSetChanged();
    }
});
if(i==position){
    if(fragment instanceof FragmenDukkan){
        FragmenDukkan fragmenDukkan=(FragmenDukkan)fragment;
        fragmenDukkan.showSub(list.get(position).getSub_departments());
    }
    myHolder.binding.card.setCardBackgroundColor(context.getResources().getColor(R.color.colorAccent));
    ((MyHolder) holder).binding.tvName.setTextColor(context.getResources().getColor(R.color.white));
    ((MyHolder) holder).binding.image.setColorFilter(ContextCompat.getColor(context, R.color.white));
}
else {
    myHolder.binding.card.setCardBackgroundColor(context.getResources().getColor(R.color.white));

    ((MyHolder) holder).binding.tvName.setTextColor(context.getResources().getColor(R.color.black));
    ((MyHolder) holder).binding.image.setColorFilter(ContextCompat.getColor(context, R.color.colorAccent));

}

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public DuckanCategoryRowBinding binding;

        public MyHolder(@NonNull DuckanCategoryRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }


}
