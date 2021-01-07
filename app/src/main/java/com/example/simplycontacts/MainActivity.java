package com.example.simplycontacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText fullNameField = findViewById(R.id.fullName);
        EditText phoneNumberField = findViewById(R.id.phone);

        //list view
        this.listView = (ListView) findViewById(R.id.list_view);
        listView.setDivider(null);
        listView.setDividerHeight(0);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = (Contact) parent.getAdapter().getItem(position);
                String toast = getString(R.string.toast_text, contact.toStringName());
                Toast.makeText(MainActivity.this, toast, Toast.LENGTH_SHORT).show();
            }
        });


        final ContactViewModel contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        findViewById(R.id.btn_add_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String itemName = fullNameField.getText().toString();
                String phone = phoneNumberField.getText().toString();

                if(contactViewModel.validateName(itemName) && contactViewModel.validatePhoneNumber(phone)) {
                    contactViewModel.addContact(itemName, phone);
                    adapter.notifyDataSetChanged();
                }

            }
        });

        contactViewModel.getContacts().observe(this, contacts -> {

            //build in adapter
            //this.adapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, contacts);

            //custom adapter
            this.adapter = new ContactAdapter(this, contacts);

            this.showContactList();
        });

        //show error messages
        contactViewModel.statusName.observe(this, statusName -> {
            fullNameField.setError(getString(statusName));
        });

        contactViewModel.statusPhone.observe(this, statusPhone -> {
            phoneNumberField.setError(getString(statusPhone));
        });
    }

    private void showContactList() {
        this.listView.setAdapter(this.adapter);
        this.listView.setVisibility(View.VISIBLE);
    }

}
