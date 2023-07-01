package org.java.dev.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
@Entity
@Table(name = "Planet")
public class PlanetEntity {
    @Id
    @Column(name = "id", length = 50)
    @Pattern(regexp = "[A-Z]*", message = "Incorrect value: Planet.id must be in format [A-Z]* !!!")
    private String id;
    @Column(name = "name", length = 2, nullable = false)
    private String name;
    @OneToMany(mappedBy="fromPlanetEntity")
    private Set<TicketEntity> ticketEntitiesFrom;
    @OneToMany(mappedBy="toPlanetEntity")
    private Set<TicketEntity> ticketEntitiesTo;
}
