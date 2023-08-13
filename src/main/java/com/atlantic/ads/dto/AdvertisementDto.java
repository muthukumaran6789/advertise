package com.atlantic.ads.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AdvertisementDto {
    private String id;
    private String name;
    private String description;
    private String adImgUrl;
    private String latitude;
    private String longitude;
}
