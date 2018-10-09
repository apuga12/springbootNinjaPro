package com.industries.stark.springbootNinja.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.industries.stark.springbootNinja.component.ContactConverter;
import com.industries.stark.springbootNinja.entity.ContactEntity;
import com.industries.stark.springbootNinja.model.ContactModel;
import com.industries.stark.springbootNinja.repository.ContactRepository;
import com.industries.stark.springbootNinja.service.ContactService;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;

	@Override
	public ContactModel addContact(ContactModel contactModel) {
		ContactEntity contactEntity = contactRepository.save(contactConverter.convertContactModel2ContactEntity(contactModel));
		return contactConverter.convertContactEntity2ContactModel(contactEntity);
	}

	@Override
	public List<ContactModel> listAllContacts() {		
		List<ContactEntity> contactEntityList = contactRepository.findAll();
		List<ContactModel> contactModelList = new ArrayList<ContactModel>();
		for(ContactEntity contactEntity: contactEntityList){
			contactModelList.add(contactConverter.convertContactEntity2ContactModel(contactEntity));
		}
		return contactModelList;
	}

	@Override
	public ContactEntity findContactById(int id) {
		return contactRepository.findById(id);
	}
	
	public ContactModel findContactByIdModel(int id) {
		return contactConverter.convertContactEntity2ContactModel(contactRepository.findById(id));
	}

	@Override
	public void removeContact(int id) {
		ContactEntity contact = findContactById(id);
		if(contact != null){
			contactRepository.delete(contact);
		}
		
	}

}
