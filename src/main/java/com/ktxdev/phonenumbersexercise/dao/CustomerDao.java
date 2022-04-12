package com.ktxdev.phonenumbersexercise.dao;

import com.ktxdev.phonenumbersexercise.models.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer, Integer> {
    @Query(
            value = "SELECT c.*, SUBSTR(c.phone, 2, 3) as countryCode " +
                    "FROM customer c " +
                    "WHERE phone LIKE %:phone% AND countryCode IN ( " +
                    "SELECT SUBSTR(co.code, 2, 3) as code " +
                    "FROM country co " +
                    "WHERE co.name LIKE %:country% AND co.code LIKE %:countryCode%)",
            countQuery = "SELECT COUNT(*), SUBSTR(c.phone, 2, 3) as countryCode " +
                    "FROM customer c " +
                    "WHERE phone LIKE %:phone% AND countryCode IN ( " +
                    "SELECT SUBSTR(co.code, 2, 3) as code " +
                    "FROM country co " +
                    "WHERE co.name LIKE %:country% AND co.code LIKE %:countryCode%)",
            nativeQuery = true)
    List<Customer> findAllCustomers(
            @Param("country") String country,
            @Param("countryCode") String countryCode,
            @Param("phone") String phone);
}
