package com.mycompany.freegame.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "GAME")
public class Game {
    @Id
    private Long id;
    private String title;
    private String description;
    private String category;
    private String platform;
    private LocalDate releaseDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id) && Objects.equals(title, game.title)
                && Objects.equals(description, game.description) && Objects.equals(category, game.category)
                && Objects.equals(platform, game.platform) && Objects.equals(releaseDate, game.releaseDate);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", platform='" + platform + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, category, platform, releaseDate);
    }
}


