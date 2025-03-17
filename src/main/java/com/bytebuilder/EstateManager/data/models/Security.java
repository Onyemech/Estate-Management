package com.bytebuilder.EstateManager.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Security")
@Getter
@Setter
public class Security {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String password;
}
