package com.atlantic.ads.controller;

import com.atlantic.ads.dto.AdvertisementDto;
import com.atlantic.ads.service.AdsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ads")
@RequiredArgsConstructor
public class AdsController {

    @Autowired
    AdsService adsService;

    @GetMapping("/view")
    public ResponseEntity<List<AdvertisementDto>> getAds(Authentication authentication) {
        List<AdvertisementDto> ads = adsService.viewAdvertisements(authentication);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<AdvertisementDto> getAd(Authentication authentication, @PathVariable String id) {
        AdvertisementDto ad = adsService.viewAdvertisement(authentication, id);
        return new ResponseEntity<>(ad, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> publishAds(Authentication authentication, @RequestBody AdvertisementDto advertisementDto) {
        String result = adsService.save(authentication, advertisementDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteAd(Authentication authentication, @RequestBody String id) {
        String result = adsService.deleteAdvertisement(authentication, id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity<AdvertisementDto> updateAd(Authentication authentication, @RequestBody AdvertisementDto advertisementDto) {
        AdvertisementDto ad = adsService.updateAd(authentication, advertisementDto);
        return new ResponseEntity<>(ad, HttpStatus.OK);
    }
}
