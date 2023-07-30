package com.radouaneoubakhane.notificationservice.event;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewMovieAddedEvent {
    // Kafka event
    private Long id;
    private String title;
    private String description;
    private String poster;
    private LocalDate releaseDate;
    private Integer duration;
    private String language;
}
