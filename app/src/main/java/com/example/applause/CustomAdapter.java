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
    private int rankingTypeIndex;

    public CustomAdapter (Context context, ArrayList<User> users, int rankingTypeIndex) {
        this.context = context;
        this.users = users;
        this.rankingTypeIndex = rankingTypeIndex;
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
        String[] rankingTypes = context.getResources().getStringArray(R.array.ranking_types);
        String rankingType = rankingTypes[rankingTypeIndex];
        int rankingPosition = position + 1;

        holder.ranking_position.setText(String.valueOf(rankingPosition));
        holder.username.setText(user.getUsername());
        holder.parameterName.setText(rankingType);
        switch (rankingTypeIndex) {
            case 0:
                holder.parameterValue.setText(user.getSpeedAvg() + "/min");
                break;
            case 1:
                holder.parameterValue.setText(user.getSpeedMax() + "/min");
                break;
            case 2:
                holder.parameterValue.setText(user.getForceAvg() + "N");
                break;
            case 3:
                holder.parameterValue.setText(user.getForceMax() + "N");
                break;
            case 4:
                holder.parameterValue.setText(user.getQualityAvg() + "%");
                break;
            case 5:
                holder.parameterValue.setText(user.getQualityMax() + "%");
                break;
            case 6:
                holder.parameterValue.setText(String.valueOf(user.getQuantityAvg()));
                break;
            case 7:
                holder.parameterValue.setText(String.valueOf(user.getQuantityMax()));
                break;
            case 8:
                holder.parameterValue.setText(user.getReactionTimeAvg() + "ms");
                break;
            case 9:
                holder.parameterValue.setText(user.getReactionTimeMax() + "ms");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView ranking_position, username, parameterName, parameterValue;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            ranking_position = itemView.findViewById(R.id.ranking_position);
            username = itemView.findViewById(R.id.username);
            parameterName = itemView.findViewById(R.id.parameter_txt);
            parameterValue = itemView.findViewById(R.id.parameter);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
