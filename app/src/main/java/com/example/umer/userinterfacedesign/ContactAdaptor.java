package com.example.umer.userinterfacedesign;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ContactAdaptor  extends RecyclerView.Adapter<ContactAdaptor.ContactViewHolder>{


    private  List<Contact> contacts;
    public ContactAdaptor(List<Contact> contactList)
    {

        this.contacts=contactList;
    }


    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View itemView=layoutInflater.inflate(R.layout.list_item,viewGroup);
        ContactViewHolder contactViewHolder=new ContactViewHolder(itemView);
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, int i) {


        Contact contact=contacts.get(i);
        contactViewHolder.NameTextView.setText(contact.getName());
        contactViewHolder.PhoneNoTextView.setText(contact.getPhoneNo());

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder
    {

        TextView NameTextView,PhoneNoTextView;
        public ContactViewHolder(View itemView)
        {

            super(itemView);

            NameTextView=itemView.findViewById(R.id.name);
            PhoneNoTextView=itemView.findViewById(R.id.phoneno);
        }
    }
}
