package com.example.umer.userinterfacedesign;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyContacts extends Activity {


    private RecyclerView recyclerView;
    private List<Contact> contactList=new ArrayList<Contact>();
    private DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contacts);

        recyclerView = findViewById(R.id.mycontactsrcv);
        db = new DatabaseHelper(this);
        getContacts();
        ContactAdaptor contactAdaptor = new ContactAdaptor(contactList);
        recyclerView.setAdapter(contactAdaptor);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);


    }

    public void getContacts()
    {
contactList=db.getAllContacts();

    }
}
