package com.company.journalApp.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
public class User {

    @Id
    private ObjectId id;
    @NonNull
    @Indexed(unique = true)             // this unique index is not automatically created in mongoDB, we need to create it manually in
    private String username;            // mongo or using a property in application.properties
    @NonNull
    private String password;
    @DBRef                  // DBRef is used to reference the id of a specific object in mongoDB. Only Id, not complete document
    private List<JournalEntry> journalEntries;


}
