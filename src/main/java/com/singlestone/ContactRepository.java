package com.singlestone;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Manages Contact table database access automatically
 */

public interface ContactRepository extends JpaRepository<Contact,Long> {
}
