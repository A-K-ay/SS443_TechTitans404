package com.example.eveutopia;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.itemViewAdapter> {
    private Context context;
    private List<String[]> locationList;

    private OnItemClickListener mListener;
    private OnButtonClickListener btnListener;

    public itemAdapter(Context context, List<String[]> locationList) {
        this.context = context;
        this.locationList = locationList;
    }

    @NonNull
    @Override
    public itemViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.nearby_ev_stations_item_adapter,parent,false);
        return new itemViewAdapter(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull itemViewAdapter holder, int position) {
        String[] current = locationList.get(position);
        holder.tv_location.setText(current[0]);
        holder.price_station.setText("Price: ₹ "+current[1]+"/kW");
        holder.slots_avail_station.setText("Slots Available: \n4-Wheeler: "+current[2]+"\n2-wheeler: "+current[3]+"\n3-Wheeler: "+current[4]);
        //holder.tv_longitude.setText("Longitude: "+current.getLongitude());
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public class itemViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        public TextView tv_location, price_station, slots_avail_station;
        public Button btn_book_slot;

        public itemViewAdapter(@NonNull View itemView) {
            super(itemView);

            tv_location = itemView.findViewById(R.id.tv_location);
            price_station = itemView.findViewById(R.id.price_station);
            slots_avail_station = itemView.findViewById(R.id.slots_avail_station);
            btn_book_slot = itemView.findViewById(R.id.btn_book_slot);

            //tv_longitude = itemView.findViewById((R.id.tv_longitude));
            //imageView = itemView.findViewById(R.id.list_image_view)

            itemView.setOnClickListener(this);
            btn_book_slot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(btnListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            btnListener.onButtonClick(position);
                        }
                    }
                }
            });
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

    public interface OnButtonClickListener{
        void onButtonClick(int position);
    }

    public void setOnButtonClickListener(OnButtonClickListener listener){
        btnListener = listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
}
