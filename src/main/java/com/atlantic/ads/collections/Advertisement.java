package com.atlantic.ads.collections;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@Data
@Builder
@ToString
public class Advertisement extends Audit{
    @Id
    private String id;
    private String name;
    private String description;
    @Indexed(unique = true)
    private String adImgUrl;
    private String latitude;
    private String longitude;
    @Indexed(unique = true)
    private String customerId;
    @Indexed(unique = true)
    private String customerEmail;
}
