package com.example.eveutopia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.itemViewAdapter> {
    private Context context;
    private List<String> locationList;

    private OnItemClickListener mListener;

    public itemAdapter(Context context, List<String> locationList) {
        this.context = context;
        this.locationList = locationList;
    }

    @NonNull
    @Override
    public itemViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.nearby_ev_stations_item_adapter,parent,false);
        return new itemViewAdapter(v);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewAdapter holder, int position) {
        String current = locationList.get(position);
        holder.tv_location.setText(current);
        //holder.tv_longitude.setText("Longitude: "+current.getLongitude());
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public class itemViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        public TextView tv_location;

        public itemViewAdapter(@NonNull View itemView) {
            super(itemView);

            tv_location = itemView.findViewById(R.id.tv_location);
            //tv_longitude = itemView.findViewById((R.id.tv_longitude));
            //imageView = itemView.findViewById(R.id.list_image_view)

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    mListener.onItemClick(position);
                }
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
}
