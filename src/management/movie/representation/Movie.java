package management.movie.representation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Movie {
    private String title;
    private String released;
    private String genre;
    private String plot;
    private String imdbRating;
}
