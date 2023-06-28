package org.iqbal.damovie.models.requests;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class MovieRequest {
    @NotEmpty(message = "title is required")
    String title;
    @NotEmpty(message = "director is required")
    String director;
    @Length(min = 10, max = 100)
    String summary;
    @NotEmpty(message = "genres is required")
    Set<Long> genresId;
}
