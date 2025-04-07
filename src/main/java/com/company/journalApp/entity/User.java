package com.company.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "users")
@NoArgsConstructor
public class User {

    @Id
    private ObjectId id;
    @Indexed(unique = true)             // this unique index is not automatically created in mongoDB, we need to create it manually in
    @NonNull                            // mongo or using a property in application.properties
    private String username;
    @NonNull
    private String password;
    @DBRef                  // DBRef is used to reference the id of a specific object in mongoDB. Only Id, not complete document
    private List<JournalEntry> journalEntries = new ArrayList<>();
    private List<String> roles;
    private String email;
    private boolean sentimentAnalysis;


}
