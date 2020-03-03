package com.example.friendscontract;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Student> studentDatalist;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerViewId);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        studentDatalist=new ArrayList<>();
        studentDatalist.add(new Student("Tusar","Dhaka","01792750826",R.drawable.my_image,R.drawable.ic_call,R.drawable.ic_sms));
        studentDatalist.add(new Student("AkerUzzaman","Dhaka","01996535213",R.drawable.sir_image,R.drawable.ic_call,R.drawable.ic_sms));
        studentDatalist.add(new Student("Abdullah","Dhaka","01648543978",R.drawable.abdullah_image,R.drawable.ic_call,R.drawable.ic_sms));
        studentDatalist.add(new Student("Noman","Dhaka","01792750826",R.drawable.noman_image,R.drawable.ic_call,R.drawable.ic_sms));
        studentDatalist.add(new Student("Robi","Dhaka","01996535213",R.drawable.robi_image,R.drawable.ic_call,R.drawable.ic_sms));
        studentDatalist.add(new Student("Ovijit","Dhaka","01994215664",R.drawable.ovijit_image,R.drawable.ic_call,R.drawable.ic_sms));


        customAdapter=new CustomAdapter(MainActivity.this,studentDatalist);

        recyclerView.setAdapter(customAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                customAdapter.getFilter().filter(newText);
                return true;
            }
        });



        return true;
    }




    // Breackprees Command
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                }).setNegativeButton("No", null).show();
             }
         }
