package org.java.dev.mapper;

import lombok.NoArgsConstructor;
import org.java.dev.dto.TicketDto;
import org.java.dev.entity.TicketEntity;

import java.util.List;

@NoArgsConstructor(staticName = "instance")
public class TicketDtoMapper implements Mapper<TicketDto, TicketEntity> {
    @Override
    public TicketEntity map(TicketDto source) throws RuntimeException {
        TicketEntity target = new TicketEntity();
        target.setId(source.getId());
        target.setCreatedAt(source.getCreatedAt());
        target.setClientEntity(source.getClientEntity());
        target.setFromPlanetEntity(source.getFromPlanetEntity());
        target.setToPlanetEntity(source.getToPlanetEntity());
        return target;
    }

    @Override
    public List<TicketEntity> map(List<TicketDto> source) throws RuntimeException {
        return source.stream().map(this::map).toList();
    }
}