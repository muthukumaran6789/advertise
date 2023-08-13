package com.atlantic.ads.repository;

import com.atlantic.ads.collections.Customer;

public interface UserRepositoryCustom {
    Customer findByEmailCustom(String email);
}
