package com.bytebuilder.EstateManager.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "Visitor")
public class VisitorsPass {
    @Id
    private String id;
    private String visitorName;
    private String residentPhone;
    private String status;
    private String phoneNumber;
    private long entryTime;
    private long expiryTime;
}