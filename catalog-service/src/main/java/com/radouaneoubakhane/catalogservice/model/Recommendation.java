package com.radouaneoubakhane.catalogservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "recommendation")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Recommendation {
    @Id
    private String id;
    private int userId;
    private int movieId;
    private double rating;
    private int timestamp;

}
