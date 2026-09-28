package com.nishi.artgallerymanagementsystem.service;

import com.nishi.artgallerymanagementsystem.entity.Contact;
import java.util.List;

public interface ContactService {

    void saveContact(Contact contact);

    List<Contact> getAllContacts();

    void deleteContact(Long id);

    long getTotalMessages();
}