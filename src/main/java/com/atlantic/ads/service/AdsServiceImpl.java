package com.atlantic.ads.service;

import com.atlantic.ads.collections.Advertisement;
import com.atlantic.ads.collections.Customer;
import com.atlantic.ads.dto.AdvertisementDto;
import com.atlantic.ads.exceptions.ResourceNotFoundException;
import com.atlantic.ads.mapper.AdvertisementMapper;
import com.atlantic.ads.repository.AdsRepository;
import com.atlantic.ads.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdsServiceImpl implements AdsService {

    @Autowired
    AdsRepository adsRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<AdvertisementDto> viewAdvertisements(Authentication authentication) {
        List<Advertisement> advertisements = adsRepository.findAllAdsPostedByOtherUser(authentication);
        if(advertisements.isEmpty()) {
            System.out.println("advertisement list is empty");
            throw new ResourceNotFoundException("No Advertisements to show currently");
        }
        List<AdvertisementDto> advertisementDtos = new ArrayList<>();
        for(Advertisement advertisement : advertisements) {
            advertisementDtos.add(AdvertisementMapper.mapToAdvertisementDto(advertisement));
        }
        System.out.println("advertisementDtos: " + advertisementDtos);
        return advertisementDtos;
    }

    @Override
    public AdvertisementDto findOneAdvertisement(Authentication authentication, String id) {
        return null;
    }

    @Override
    public String deleteAdvertisement(Authentication authentication, String id) {
        String email = authentication.getName();
        Optional<Advertisement> advertisement = adsRepository.findById(id);
        if(!advertisement.isPresent()) {
            throw new ResourceNotFoundException("Advertisement not present!");
        }
        if(advertisement.get().getCustomerEmail().equals(email)) {
            adsRepository.deleteById(id);
        }
        return "Ad deleted successfully!";
    }

    @Override
    public String save(Authentication authentication, AdvertisementDto advertisementDto) {
        String email = authentication.getName();
        Optional<Customer> customer = userRepository.findByEmail(email);
        if(!customer.isPresent()) {
            throw new ResourceNotFoundException("user not present");
        }
        Advertisement advertisement = AdvertisementMapper.mapToAdvertisement(advertisementDto, customer.get().getId(), customer.get().getEmail());
        adsRepository.save(advertisement);
        return "Ad published successfully";
    }

    @Override
    public AdvertisementDto updateAd(Authentication authentication, AdvertisementDto advertisementDto) {
        String email = authentication.getName();
        Optional<Customer> customer = userRepository.findByEmail(email);
        if(!customer.isPresent()) {
            throw new ResourceNotFoundException("User not present");
        }
        Advertisement advertisement = AdvertisementMapper.mapToAdvertisement(advertisementDto, customer.get().getId(), customer.get().getEmail());
        Advertisement advertisementSaved = adsRepository.save(advertisement);
        return AdvertisementMapper.mapToAdvertisementDto(advertisementSaved);
    }

    @Override
    public AdvertisementDto viewAdvertisement(Authentication authentication, String id) {
        String email = authentication.getName();
        Optional<Advertisement> advertisement = adsRepository.findById(id);
        if(!advertisement.isPresent()) {
            throw new ResourceNotFoundException("Ad not present");
        }
        AdvertisementDto advertisementDto = AdvertisementMapper.mapToAdvertisementDto(advertisement.get());
        return advertisementDto;
    }
}
