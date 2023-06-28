package org.iqbal.damovie.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    @Id
    @Column(name = "genre_name", nullable = false)
    private String genreName;
}
