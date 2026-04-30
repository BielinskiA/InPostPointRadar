package org.example.inpostpointradar.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "points")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String type;

    private String status;

    @Column(name = "line1")
    private String line1;

    @Column(name = "line2")
    private String line2;

    private Double latitude;

    private Double longitude;

    @ElementCollection
    @CollectionTable(name = "point_functions", joinColumns = @JoinColumn(name = "point_id"))
    @Column(name = "function_name")
    private Set<String> functions;

}
