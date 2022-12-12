package com.example.applause;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<User> users;

    public CustomAdapter (Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = users.get(position);
        int rankingPosition = position + 1;

        holder.ranking_position.setText(String.valueOf(rankingPosition));
        holder.username.setText(user.getUsername());
        holder.speed_txt.setText(user.getSpeed() + "/min");
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView ranking_position, username, speed_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            ranking_position = itemView.findViewById(R.id.ranking_position);
            username = itemView.findViewById(R.id.username);
            speed_txt = itemView.findViewById(R.id.parameter);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
