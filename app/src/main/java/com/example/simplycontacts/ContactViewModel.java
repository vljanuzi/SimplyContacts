package com.example.simplycontacts;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;

public class ContactViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Contact>> contacts;
    ArrayList<Contact> contactsList= new ArrayList<>();
    public MutableLiveData<Integer> statusName;
    public MutableLiveData<Integer> statusPhone;


    public ContactViewModel() {
        contacts = new MutableLiveData<>();
        statusName = new MutableLiveData<>();
        statusPhone = new MutableLiveData<>();
    }

    public LiveData<ArrayList<Contact>> getContacts() {
        if (contacts == null) {
            contacts = new MutableLiveData<>();
        }
        return contacts;
    }

    public void addContact(String name, String phone) {
            contactsList.add(new Contact(name, phone));
            contacts.setValue(contactsList);
    }

    public boolean validateName(String name) {
        System.out.println(name.length());

        //check if name is not empty
        if(!name.isEmpty()) {

            //name should contain only letters and spaces
            if (name.matches("^[ A-Za-z]+$")) {
                // name should not be longer than 100 characters (no minimum as there are people with names with only one letter)
                // 100 shall be enough for first middle and last name for extreme cases
                if (name.length() > 100) {
                    statusName.setValue(R.string.name_status_char);
                    return false;
                }
            } else {
                statusName.setValue(R.string.name_status_letter);
                return false;
            }
            return true;
        }
        statusName.setValue(R.string.name_status_req);
        return false;
    }

    public boolean validatePhoneNumber(String phone){

        //check if phone is not empty
        if(!phone.isEmpty()) {

            // since the shortest phone number is the world has four digits, and the largest 15, check if the digit number is in this range
            // and check if the input contains only numbers or '+' (for country code)
            if(phone.matches("^[0-9\\+]*$") && (phone.length() <= 15 && phone.length() >= 4)) {
                return true;
            } else {
                statusPhone.setValue(R.string.phone_status_valid);
                return false;
            }
        }
        statusPhone.setValue(R.string.phone_status_req);
        return false;
    }
}
