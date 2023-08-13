package com.atlantic.ads.repository;

import com.atlantic.ads.collections.Advertisement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdsRepository extends MongoRepository<Advertisement, String>, AdsRepositoryCustom {

}
