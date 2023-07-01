package org.java.dev.mapper;

import lombok.NoArgsConstructor;
import org.java.dev.dto.PlanetDto;
import org.java.dev.entity.PlanetEntity;

import java.util.List;

@NoArgsConstructor(staticName = "instance")
public class PlanetDtoMapper implements Mapper<PlanetDto, PlanetEntity> {
    @Override
    public PlanetEntity map(PlanetDto source) throws RuntimeException {
        PlanetEntity target = new PlanetEntity();
        target.setId(source.getId());
        target.setName(source.getName());
        return target;
    }

    @Override
    public List<PlanetEntity> map(List<PlanetDto> source) throws RuntimeException {
        return source.stream().map(this::map).toList();
    }
}