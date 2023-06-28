package org.iqbal.damovie.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Genre {
    @Id
    @Column(name = "genre_name")
    private String genreName;
}
