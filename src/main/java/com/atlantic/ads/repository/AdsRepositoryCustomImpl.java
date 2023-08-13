package com.atlantic.ads.repository;

import com.atlantic.ads.collections.Advertisement;
import com.atlantic.ads.dto.AdvertisementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdsRepositoryCustomImpl implements AdsRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Advertisement> findAllAdsPostedByOtherUser(Authentication authentication) {

        String email = authentication.getName();
        System.out.println("findAllAdsPostedByOtherUser " + email);
        Query query = new Query();
        query.addCriteria(Criteria.where("customerEmail").ne(email));
        return mongoTemplate.find(query, Advertisement.class);
    }
}
