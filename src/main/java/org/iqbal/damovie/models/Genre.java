package org.iqbal.damovie.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Genre {
    @Id
    @Column(name = "genre_name")
    private String genreName;
}
