package com.example.umer.userinterfacedesign;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Contact> contactList=new ArrayList<Contact>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.myrcv);

    }

    void getContacts()
    {

        ContentResolver contentResolver=this.getContentResolver();
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

}
