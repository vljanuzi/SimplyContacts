package com.example.simplycontacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends BaseAdapter {

    private Context context;
    private List<Contact> contactsList;

    public ContactAdapter(Context context, List<Contact> items) {
        this.context = context;
        this.contactsList = items;
    }

    @Override
    public int getCount() {
        return this.contactsList.size();
    }

    public Object getItem(int position) {
        return this.contactsList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.item, parent, false);
        }

        Contact contact = (Contact) getItem(position);

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView phoneNumber = (TextView) convertView.findViewById(R.id.number);

        name.setText(contact.getName());
        phoneNumber.setText(String.valueOf(contact.getPhoneNumber()));

        return convertView;
    }
}

