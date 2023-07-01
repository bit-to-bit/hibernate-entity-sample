package org.java.dev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.java.dev.entity.ClientEntity;
import org.java.dev.entity.PlanetEntity;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class TicketDto {
    private Long id;
    private Timestamp createdAt;
    private ClientEntity clientEntity;
    private PlanetEntity fromPlanetEntity;
    private PlanetEntity toPlanetEntity;
}