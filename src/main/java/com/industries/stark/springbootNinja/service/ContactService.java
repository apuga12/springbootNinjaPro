package com.industries.stark.springbootNinja.service;

import java.util.List;

import com.industries.stark.springbootNinja.entity.ContactEntity;
import com.industries.stark.springbootNinja.model.ContactModel;

public interface ContactService {
	
	public ContactModel addContact(ContactModel contactModel);
	public List<ContactModel> listAllContacts();
	public ContactEntity findContactById(int id);
	public ContactModel findContactByIdModel(int id);
	public void removeContact(int id);

}
