package org.java.dev.mapper;

import lombok.NoArgsConstructor;
import org.java.dev.dto.ClientDto;
import org.java.dev.entity.ClientEntity;

import java.util.List;
@NoArgsConstructor(staticName = "instance")
public class ClientEntityMapper implements Mapper<ClientDto, ClientEntity> {
    @Override
    public ClientEntity map(ClientDto source) throws RuntimeException {
        ClientEntity target = new ClientEntity();
        target.setId(source.getId());
        target.setName(source.getName());
        return target;
    }
    @Override
    public List<ClientEntity> map(List<ClientDto> source) throws RuntimeException {
        return source.stream().map(this::map).toList();
    }
}
