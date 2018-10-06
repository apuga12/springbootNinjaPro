package com.industries.stark.springbootNinja.component;

import org.springframework.stereotype.Component;

import com.industries.stark.springbootNinja.entity.ContactEntity;
import com.industries.stark.springbootNinja.model.ContactModel;

@Component("contactConverter")
public class ContactConverter {
	
	public ContactEntity convertContactModel2ContactEntity(ContactModel contactModel){
		ContactEntity contactEntity = new ContactEntity(
				contactModel.getId(), contactModel.getFirstname(), contactModel.getLastname(),
				contactModel.getTelephone(), contactModel.getCity()
				);
		return contactEntity;
		
	}
	
	public ContactModel convertContactEntity2ContactModel(ContactEntity contactEntity){
		ContactModel contactModel = new ContactModel(
				contactEntity.getId(), contactEntity.getFirstname(), contactEntity.getLastname(),
				contactEntity.getTelephone(), contactEntity.getCity()
				);
		return contactModel;
	}
	
}
