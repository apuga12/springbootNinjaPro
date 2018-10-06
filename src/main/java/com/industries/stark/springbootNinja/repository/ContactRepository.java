package com.industries.stark.springbootNinja.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.industries.stark.springbootNinja.entity.ContactEntity;

@Repository("contactRepository")
public interface ContactRepository extends JpaRepository<ContactEntity, Serializable>{

}
