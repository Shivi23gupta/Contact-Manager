package com.cm.services;

import com.cm.entities.Contact;
import com.cm.entities.User;

import java.util.List;

import org.springframework.data.domain.Page;

public interface ContactService {

    // savecontact
    Contact save(Contact contact);

    // update contact
    Contact update(Contact contact);

    // get contacts
    List<Contact> getAll();

    // getcontact by id
    Contact getById(String id);

    // delete contact
    void delete(String id);

    // search contact
    Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order, User user);

    Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order, User user);

    Page<Contact> searchByPhoneNumber(String phoneNumberKeyword, int size, int page, String sortBy, String order,
            User user);

    // get contact by userId
    List<Contact> getByUserId(String userId);

    Page<Contact> getByUser(User user, int page, int size, String sortField, String sortDirection);

}