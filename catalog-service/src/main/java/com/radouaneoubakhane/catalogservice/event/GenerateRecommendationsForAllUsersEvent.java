package com.radouaneoubakhane.catalogservice.event;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenerateRecommendationsForAllUsersEvent {
    private LocalDateTime dateTime;

}
