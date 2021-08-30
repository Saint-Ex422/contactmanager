package com.singlestone;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
Class used for retrieving phoneNumber data from the phone_number table in the database
 */

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber,Long> {
    List<PhoneNumber> findByType(String name);
}
