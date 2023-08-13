package com.atlantic.ads.repository;

import com.atlantic.ads.collections.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<Customer, String>, UserRepositoryCustom {
    Optional<Customer> findByEmail(String email);
}
