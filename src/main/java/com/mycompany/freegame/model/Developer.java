package com.mycompany.freegame.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
@Table(name = "DEVELOPER")
public class Developer {
    @Id
    private Long id;
    private String company;
    private String department;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "developers_id")
    private List<Game> games;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer that = (Developer) o;
        return Objects.equals(id, that.id) && Objects.equals(company, that.company)
                && Objects.equals(department, that.department);
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", department='" + department + '\'' +
                ", games=" + games +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, company, department);
    }

}
