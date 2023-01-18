package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Ranking extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner rankingTypeSpinner;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        rankingTypeSpinner = findViewById(R.id.ranking_type);
        recyclerView = findViewById(R.id.recycler_view);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ranking_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rankingTypeSpinner.setAdapter(adapter);
        rankingTypeSpinner.setOnItemSelectedListener(this);

        users = getPublicUsers();
        prepareUsersData();
        //mockUsers();

        sortUsers(0);
        setAdapter(0);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        sortUsers(i);

        ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.native_dark_red));
        ((TextView) adapterView.getChildAt(0)).setTextSize(16);
        ((TextView) adapterView.getChildAt(0)).setTypeface(null, Typeface.BOLD);

        setAdapter(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void setAdapter(int rankingTypeIndex) {
        adapter = new CustomAdapter(this, users, rankingTypeIndex);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private ArrayList<User> getPublicUsers() {
        JSONCommunicator jsonCommunicator = new JSONCommunicator(this);
        return jsonCommunicator.getAllPublicUsers();
    }

    private void prepareUsersData() {
        JSONCommunicator jsonCommunicator = new JSONCommunicator(this);
        for (User user : users) {
            user.setClapsSessions(jsonCommunicator.getAllClapsSessions(user.getUsername()));
            user.calculateStats();
        }
    }

    private void sortUsers(int rankingTypeIndex) {
        switch (rankingTypeIndex) {
            case 0:
                Collections.sort(users, new Comparator<User>() {
                    @Override
                    public int compare(User user1, User user2) {
                        return Double.compare(user2.getSpeedAvg(), user1.getSpeedAvg());
                    }
                });
                break;
            case 1:
                Collections.sort(users, new Comparator<User>() {
                    @Override
                    public int compare(User user1, User user2) {
                        return Double.compare(user2.getSpeedMax(), user1.getSpeedMax());
                    }
                });
                break;
            case 2:
                Collections.sort(users, new Comparator<User>() {
                    @Override
                    public int compare(User user1, User user2) {
                        return Double.compare(user2.getForceAvg(), user1.getForceAvg());
                    }
                });
                break;
            case 3:
                Collections.sort(users, new Comparator<User>() {
                    @Override
                    public int compare(User user1, User user2) {
                        return Double.compare(user2.getForceMax(), user1.getForceMax());
                    }
                });
                break;
            case 4:
                Collections.sort(users, new Comparator<User>() {
                    @Override
                    public int compare(User user1, User user2) {
                        return Double.compare(user2.getQualityAvg(), user1.getQualityAvg());
                    }
                });
                break;
            case 5:
                Collections.sort(users, new Comparator<User>() {
                    @Override
                    public int compare(User user1, User user2) {
                        return Double.compare(user2.getQualityMax(), user1.getQualityMax());
                    }
                });
                break;
            case 6:
                Collections.sort(users, new Comparator<User>() {
                    @Override
                    public int compare(User user1, User user2) {
                        return Double.compare(user2.getQuantityAvg(), user1.getQuantityAvg());
                    }
                });
                break;
            case 7:
                Collections.sort(users, new Comparator<User>() {
                    @Override
                    public int compare(User user1, User user2) {
                        return Double.compare(user2.getQuantityMax(), user1.getQuantityMax());
                    }
                });
                break;
            case 8:
                Collections.sort(users, new Comparator<User>() {
                    @Override
                    public int compare(User user1, User user2) {
                        return Double.compare(user1.getReactionTimeAvg(), user2.getReactionTimeAvg());
                    }
                });
                break;
            case 9:
                Collections.sort(users, new Comparator<User>() {
                    @Override
                    public int compare(User user1, User user2) {
                        return Double.compare(user1.getReactionTimeMax(), user2.getReactionTimeMax());
                    }
                });
                break;
        }
    }
}