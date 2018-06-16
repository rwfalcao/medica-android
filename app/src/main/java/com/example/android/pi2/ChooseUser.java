package com.example.android.pi2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.adapters.ChooseUserAdapter;
import com.example.android.models.User;
import com.example.android.util.InsertionSort;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChooseUser extends AppCompatActivity {

    RecyclerView listUsersView;
    ChooseUserAdapter adapter;

    FirebaseDatabase database;
    FirebaseAuth mAuth;

    String uid;

    List<User> depList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getUid();
        DatabaseReference depenentes = database.getReference("/Users/"+uid+"/Dependentes");
        depenentes.keepSynced(true);

        depList = new ArrayList<>();

        listUsersView = (RecyclerView) findViewById(R.id.rvUsers);
        listUsersView.setHasFixedSize(true);

        listUsersView.setLayoutManager(new LinearLayoutManager(this));

        depenentes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    User user = ds.getValue(User.class);
                    depList.add(user);
                }
                InsertionSort.sort(depList);
                adapter = new ChooseUserAdapter(ChooseUser.this, depList);
                listUsersView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


/*
        depList.add(new User("Rodrigo", "Falcão", "Masculino", "6:30", "23:00"));
        depList.add(new User("Rodrigo", "Falcão", "Masculino", "6:30", "23:00"));
        depList.add(new User("Rodrigo", "Falcão", "Masculino", "6:30", "23:00"));
        depList.add(new User("Rodrigo", "Falcão", "Masculino", "6:30", "23:00"));*/




    }


}
