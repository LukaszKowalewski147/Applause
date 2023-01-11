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
        mockUsers();

        setAdapter(0);
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

    private void mockUsers() {
        User user1 = new User("Lukasz123", 275);
        User user2 = new User("AFGVaerg", 270);
        User user3 = new User("bbbdfger", 265);
        User user4 = new User("tt%%%54", 251);
        User user5 = new User("uiuiuerqwe", 248);
        User user6 = new User("zxcv", 247);
        User user7 = new User("1234", 240);
        User user8 = new User("765i44444", 233);
        User user9 = new User("BBBgggbBBBBaa", 232);
        User user10 = new User("afsdf", 230);
        User user11 = new User("%%$%#^caggbBBBBaa", 212);
        User user12 = new User("6178298asd@", 198);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);
        users.add(user10);
        users.add(user11);
        users.add(user12);
    }

    private void setAdapter(int rankingTypeIndex) {
        adapter = new CustomAdapter(this, users, rankingTypeIndex);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.native_dark_red));
        ((TextView) adapterView.getChildAt(0)).setTextSize(16);
        ((TextView) adapterView.getChildAt(0)).setTypeface(null, Typeface.BOLD);
        setAdapter(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}