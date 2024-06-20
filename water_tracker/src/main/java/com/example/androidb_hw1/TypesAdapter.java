package com.example.androidb_hw1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class TypesAdapter extends RecyclerView.Adapter<TypesAdapter.TypeViewHolder> {
    private Context context;
    private ArrayList<CupType> cupTypes;
    private CupTypeCallback cupTypeCallback;

    public TypesAdapter(Context context, ArrayList<CupType> cupTypes) {
        this.context = context;
        this.cupTypes = cupTypes;
    }

    public TypesAdapter setTypesCallback(CupTypeCallback cupTypeCallback) {
        this.cupTypeCallback = cupTypeCallback;
        return this;
    }

    @NonNull
    @Override
    public TypesAdapter.TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_item, parent, false);
        return new TypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypesAdapter.TypeViewHolder holder, int position) {
        CupType cupType = getItem(position);
        holder.item_LBL_type.setText(cupType.getType());
        holder.item_LBL_capacity.setText(String.valueOf(cupType.getCapacity()));
        holder.item_IMG_photo.setImageResource(cupType.getDrawableImg());
    }

    @Override
    public int getItemCount() {
        return cupTypes == null ? 0 : cupTypes.size();
    }

    private CupType getItem(int position) {
        return cupTypes.get(position);
    }

    public class TypeViewHolder extends RecyclerView.ViewHolder{

        private ShapeableImageView item_IMG_photo;
        private MaterialTextView item_LBL_type;
        private MaterialTextView item_LBL_capacity;
        public TypeViewHolder(@NonNull View itemView) {
            super(itemView);
            item_IMG_photo = itemView.findViewById(R.id.item_IMG_photo);
            item_LBL_type = itemView.findViewById(R.id.item_LBL_type);
            item_LBL_capacity = itemView.findViewById(R.id.item_LBL_capacity);

            itemView.setOnClickListener(v ->
            {
                if (cupTypeCallback != null){
                    cupTypeCallback.cupTypeSelected(getItem(getAdapterPosition()),getAdapterPosition());
                }
            });
        }
    }
}
