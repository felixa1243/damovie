package org.iqbal.damovie.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "movies")
@Getter
@Setter
@ToString
public class Movie {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "movie_id")
    private String id;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 100)
    private String director;
    @Column(nullable = false, length = 100)
    private String summary;
    @Column(name = "is_active")
    private boolean isActive = true;
    @ManyToMany
    @JoinTable(
            name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "genre_name", nullable = false)
    )
    private Set<Genre> movieGenres;
}
