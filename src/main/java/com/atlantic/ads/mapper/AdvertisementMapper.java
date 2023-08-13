package com.atlantic.ads.mapper;

import com.atlantic.ads.collections.Advertisement;
import com.atlantic.ads.dto.AdvertisementDto;

public class AdvertisementMapper {

    public static AdvertisementDto mapToAdvertisementDto(Advertisement advertisement) {
        return AdvertisementDto.builder().id(advertisement.getId())
                .name(advertisement.getName())
                .description(advertisement.getDescription())
                .adImgUrl(advertisement.getAdImgUrl())
                .latitude(advertisement.getLatitude())
                .longitude(advertisement.getLongitude())
                .build();
    }

    public static Advertisement mapToAdvertisement(AdvertisementDto advertisementDto, String customerId, String customerEmail) {
        return Advertisement.builder().name(advertisementDto.getName())
                .description(advertisementDto.getDescription())
                .adImgUrl(advertisementDto.getAdImgUrl())
                .latitude(advertisementDto.getLatitude())
                .longitude(advertisementDto.getLongitude())
                .customerId(customerId)
                .customerEmail(customerEmail)
                .build();
    }
}
