package com.atlantic.ads.repository;

import com.atlantic.ads.collections.Advertisement;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AdsRepositoryCustom {
    List<Advertisement> findAllAdsPostedByOtherUser(Authentication authentication);
}
