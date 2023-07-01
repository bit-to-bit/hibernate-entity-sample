package org.java.dev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.java.dev.entity.TicketEntity;

import javax.persistence.OneToMany;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class PlanetDto {
    private String id;
    private String name;
}
