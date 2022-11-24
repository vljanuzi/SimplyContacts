package com.example.simplycontacts;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import java.util.ArrayList;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class ContactViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Contact>> contacts;
    ArrayList<Contact> contactsList= new ArrayList<>();
    public MutableLiveData<Integer> nameStatus;
    public MutableLiveData<Integer> phoneStatus;


    public ContactViewModel() {
        contacts = new MutableLiveData<>();
        nameStatus = new MutableLiveData<>();
        phoneStatus = new MutableLiveData<>();
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

        if(!name.isEmpty()) {

            if (name.matches("^[ A-Za-z]+$")) {
                if (name.length() >= 100) {
                    nameStatus.setValue(R.string.name_status_char);
                    return false;
                }
            } else {
                nameStatus.setValue(R.string.name_status_letter);
                return false;
            }
            return true;
        }
        nameStatus.setValue(R.string.name_status_req);
        return false;
    }

    public boolean validatePhoneNumber(String phone){

        if(!phone.isEmpty()) {

            if(phone.matches("^[0-9\\+]*$")) {

                if((phone.length() > 20)){
                    phoneStatus.setValue(R.string.phone_status_long);
                    return false;
                } else if((phone.length() < 3)){
                    phoneStatus.setValue(R.string.phone_status_short);
                    return false;
                }else
                    return true;

            } else {
                phoneStatus.setValue(R.string.phone_status_valid);
                return false;
            }
        }
        phoneStatus.setValue(R.string.phone_status_req);
        return false;
    }
}
