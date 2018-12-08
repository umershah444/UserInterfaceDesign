package com.example.umer.userinterfacedesign;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Contact> contactList=new ArrayList<Contact>();
    private DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences mySharedPref=this.getSharedPreferences("mysp",0);
        SharedPreferences.Editor editor=mySharedPref.edit();
        editor.putBoolean("UserLogedIn",true);
        editor.commit();
        mySharedPref.getBoolean("UserLogedIn",false);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn=findViewById(R.id.mybtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyContacts.class);
                startActivity(intent);
            }
        });
        recyclerView=findViewById(R.id.myrcv);
        getContacts();
        ContactAdaptor contactAdaptor=new ContactAdaptor(contactList);
        recyclerView.setAdapter(contactAdaptor);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        db = new DatabaseHelper(this);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Toast.makeText(getApplicationContext(),"Clicked "+contactList.get(position).getName(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position);
                Toast.makeText(getApplicationContext(),"Long Clicked "+contactList.get(position).getName(),Toast.LENGTH_LONG).show();

            }
        }));
    }

    void getContacts()
    {

        ContentResolver contentResolver=this.getContentResolver();

        Log.d("URI",ContactsContract.CommonDataKinds.Phone.CONTENT_URI.toString());
        Cursor cursor=contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
           String no=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
           String name= cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
           Contact contact=new Contact();
           contact.setName(name);
           contact.setPhoneNo(no);
           contactList.add(contact);
           cursor.moveToNext();
        }


    }
    private void showActionsDialog(final int position) {
        CharSequence options[] = new CharSequence[]{"Add to DB", "Cancle"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose option");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int optionPressed) {
                if (optionPressed == 0) {

                    Contact contact=contactList.get(position);

                    db.insertContact(contact.getPhoneNo(),contact.getName());
                    Toast.makeText(getApplicationContext(),"Inserted",Toast.LENGTH_LONG).show();

                } else {

                }
            }
        });
        builder.show();
    }

}
