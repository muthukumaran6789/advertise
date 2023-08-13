package com.atlantic.ads.service;

import com.atlantic.ads.dto.AdvertisementDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AdsService {
    List<AdvertisementDto> viewAdvertisements(Authentication authentication);
    AdvertisementDto findOneAdvertisement(Authentication authentication, String id);
    String deleteAdvertisement(Authentication authentication, String id);

    String save(Authentication authentication, AdvertisementDto advertisementDto);

    AdvertisementDto updateAd(Authentication authentication, AdvertisementDto advertisementDto);

    AdvertisementDto viewAdvertisement(Authentication authentication, String id);
}
