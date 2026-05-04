package org.example.inpostpointradar.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "points")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String type;

    private String status;

    private String line1;

    private String line2;

    private Double latitude;

    private Double longitude;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "point_functions",
            joinColumns = @JoinColumn(name = "point_id")
    )
    @Column(name = "function_name")
    @Builder.Default
    private Set<String> functions = new HashSet<>();

    @Override
    public String toString() {
        return "Point(name=" + name + ", type=" + type + ", status=" + status + ")";
    }
}