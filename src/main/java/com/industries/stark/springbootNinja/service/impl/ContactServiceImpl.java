package com.industries.stark.springbootNinja.service.impl;

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

}
