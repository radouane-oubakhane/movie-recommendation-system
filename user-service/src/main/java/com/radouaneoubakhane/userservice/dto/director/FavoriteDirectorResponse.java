package com.radouaneoubakhane.userservice.dto.director;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDirectorResponse {
    private Long id;
    private DirectorResponse director;
}
