package com.example.finalassraytos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<DataClass> dataList;

    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataClass dataClass = dataList.get(position);
        holder.recName.setText(dataList.get(position).getDataName());
        holder.recSNumber.setText(dataList.get(position).getDataSNumber());
        holder.recYear.setText(dataList.get(position).getDataYear());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Name", dataList.get(holder.getAdapterPosition()).getDataName());
                intent.putExtra("Student number", dataList.get(holder.getAdapterPosition()).getDataSNumber());
                intent.putExtra("Year", dataList.get(holder.getAdapterPosition()).getDataYear());
                intent.putExtra("Key", dataList.get(holder.getAdapterPosition()).getKey());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public void searchDataList(ArrayList<DataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{
    TextView recName, recSNumber, recYear;
    CardView recCard;

    public MyViewHolder(@NonNull View itemView){
        super(itemView);

        recName = itemView.findViewById(R.id.recName);
        recSNumber = itemView.findViewById(R.id.recSNumber);
        recYear = itemView.findViewById(R.id.recYear);
        recCard = itemView.findViewById(R.id.recCard);

    }
}
