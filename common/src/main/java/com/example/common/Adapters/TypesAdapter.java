package com.example.common.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.Interfaces.CupTypeCallback;
import com.example.common.Models.CupType;
import com.example.common.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class TypesAdapter extends RecyclerView.Adapter<TypesAdapter.TypeViewHolder> {
    private Context context;
    private ArrayList<CupType> cupTypes;
    private CupTypeCallback cupTypeCallback;
    private int singleItem_selection_position = -1;

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
        StringBuffer strCapacity = new StringBuffer();
        strCapacity.append(cupType.getCapacity()).append(cupType.getUnit());
        holder.item_LBL_capacity.setText(strCapacity);
        holder.item_IMG_photo.setImageResource(cupType.getDrawableImg());
        holder.item_CARD_background.setBackground(null);

        if(singleItem_selection_position == position){
            holder.item_CARD_background.setBackgroundResource(R.drawable.border);
            if(cupType.isSelected()) {
                holder.item_CARD_background.setBackgroundResource(R.drawable.border);
            }
            else {
                holder.item_CARD_background.setBackground(null);
            }
        }
        else {
            holder.item_CARD_background.setBackground(null);
        }
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
        private ConstraintLayout item_CARD_background;
        public TypeViewHolder(@NonNull View itemView) {
            super(itemView);
            item_IMG_photo = itemView.findViewById(R.id.item_IMG_photo);
            item_LBL_type = itemView.findViewById(R.id.item_LBL_type);
            item_LBL_capacity = itemView.findViewById(R.id.item_LBL_capacity);
            item_CARD_background = itemView.findViewById(R.id.item_CARD_background);

            itemView.setOnClickListener(v ->
            {
                if (cupTypeCallback != null){
                    setSingleSelection(getAdapterPosition());
                    cupTypeCallback.cupTypeSelected(getItem(getAdapterPosition()),getAdapterPosition());
                }
            });

        }
    }

    private void setSingleSelection(int adapterPosition) {
        if(adapterPosition == RecyclerView.NO_POSITION)
            return;

        CupType type = cupTypes.get(adapterPosition);
        type.setSelected(!type.isSelected());
        if(singleItem_selection_position != -1 && singleItem_selection_position != adapterPosition)
            cupTypes.get(singleItem_selection_position).setSelected(false);
        notifyItemChanged(singleItem_selection_position);
        singleItem_selection_position = adapterPosition;
        notifyItemChanged(singleItem_selection_position);
    }
}
