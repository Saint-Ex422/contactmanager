package com.singlestone;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber,Long> {
    List<PhoneNumber> findByType(String name);
}
