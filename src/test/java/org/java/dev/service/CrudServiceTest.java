package org.java.dev.service;

import org.java.dev.configuration.AppProperties;
import org.java.dev.configuration.LoggingConfiguration;
import org.java.dev.configuration.hibernate.Datasource;
import org.java.dev.dto.ClientDto;
import org.java.dev.dto.PlanetDto;
import org.java.dev.dto.TicketDto;
import org.java.dev.entity.ClientEntity;
import org.java.dev.entity.PlanetEntity;
import org.java.dev.entity.TicketEntity;
import org.java.dev.mapper.ClientDtoMapper;
import org.java.dev.mapper.ClientEntityMapper;
import org.java.dev.mapper.PlanetDtoMapper;
import org.java.dev.mapper.TicketEntityMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

class CrudServiceTest {
    protected AppProperties appProperties;
    protected ClientCrudService clientCrudService;
    protected PlanetCrudService planetCrudService;
    protected TicketCrudService ticketCrudService;

    @BeforeEach
    public void setup() {
        appProperties = AppProperties.load();
        new LoggingConfiguration();
        clientCrudService = new ClientCrudService(new Datasource());
        planetCrudService = new PlanetCrudService(new Datasource());
        ticketCrudService = new TicketCrudService(new Datasource());
    }

    @Test
    void ticketCrudServiceCreate() {
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        ClientEntity clientEntity = clientCrudService.getById(1L);
        PlanetEntity fromPlanetEntity = planetCrudService.getById("SATURN");
        PlanetEntity toPlanetEntity = planetCrudService.getById("MARS");
        TicketDto ticketDto = TicketDto.of(null, createdAt, clientEntity, fromPlanetEntity, toPlanetEntity);
        TicketEntity ticketEntity = TicketEntityMapper.instance().map(ticketDto);
        Assertions.assertDoesNotThrow(() -> ticketCrudService.create(ticketEntity));
    }

    @Test
    void ticketCrudServiceCreateClientNotExists() {
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        ClientEntity clientEntity = ClientEntityMapper.instance().map(ClientDto.of(-1L, "TestClient"));
        PlanetEntity fromPlanetEntity = planetCrudService.getById("SATURN");
        PlanetEntity toPlanetEntity = planetCrudService.getById("MARS");
        TicketDto ticketDto = TicketDto.of(null, createdAt, clientEntity, fromPlanetEntity, toPlanetEntity);
        TicketEntity ticketEntity = TicketEntityMapper.instance().map(ticketDto);
        Assertions.assertThrows(Exception.class, () -> ticketCrudService.create(ticketEntity));
    }

    @Test
    void ticketCrudServiceCreateClientIsNull() {
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        ClientEntity clientEntity = new ClientEntity();
        PlanetEntity fromPlanetEntity = planetCrudService.getById("SATURN");
        PlanetEntity toPlanetEntity = planetCrudService.getById("MARS");
        TicketDto ticketDto = TicketDto.of(null, createdAt, clientEntity, fromPlanetEntity, toPlanetEntity);
        TicketEntity ticketEntity = TicketEntityMapper.instance().map(ticketDto);
        Assertions.assertThrows(Exception.class, () -> ticketCrudService.create(ticketEntity));
    }

    @Test
    void ticketCrudServiceCreatePlanetNotExists() {
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        ClientEntity clientEntity = clientCrudService.getById(1L);
        PlanetEntity fromPlanetEntity = planetCrudService.getById("NOTEXISTS");
        PlanetEntity toPlanetEntity = planetCrudService.getById("MARS");
        TicketDto ticketDto = TicketDto.of(null, createdAt, clientEntity, fromPlanetEntity, toPlanetEntity);
        TicketEntity ticketEntity = TicketEntityMapper.instance().map(ticketDto);
        Assertions.assertThrows(Exception.class, () -> ticketCrudService.create(ticketEntity));
    }

    @Test
    void ticketCrudServiceCreatePlanetIsNull() {
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        ClientEntity clientEntity = new ClientEntity();
        PlanetEntity fromPlanetEntity = planetCrudService.getById("SATURN");
        PlanetEntity toPlanetEntity = new PlanetEntity();
        TicketDto ticketDto = TicketDto.of(null, createdAt, clientEntity, fromPlanetEntity, toPlanetEntity);
        TicketEntity ticketEntity = TicketEntityMapper.instance().map(ticketDto);
        Assertions.assertThrows(Exception.class, () -> ticketCrudService.create(ticketEntity));
    }

    @Test
    void ticketCrudServiceGetById() {
        Assertions.assertDoesNotThrow(() -> ticketCrudService.getById(1L));
    }

    @Test
    void ticketCrudServiceFindAll() {
        Assertions.assertTrue(ticketCrudService.findAll().size() > 0);
    }

    @Test
    void ticketCrudServiceCreateDelete() {
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        ClientEntity clientEntity = clientCrudService.getById(1L);
        PlanetEntity fromPlanetEntity = planetCrudService.getById("SATURN");
        PlanetEntity toPlanetEntity = planetCrudService.getById("MARS");
        TicketDto ticketDto = TicketDto.of(null, createdAt, clientEntity, fromPlanetEntity, toPlanetEntity);
        TicketEntity ticketEntity = TicketEntityMapper.instance().map(ticketDto);
        Long planetId = ticketCrudService.create(ticketEntity).getId();
        Long actual = (long) ticketCrudService.findAll().size();
        ticketCrudService.delete(planetId);
        Long expected = (long) ticketCrudService.findAll().size() + 1;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void ticketCrudServiceUpdate() {
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        ClientEntity clientEntity = clientCrudService.getById(1L);
        PlanetEntity fromPlanetEntity = planetCrudService.getById("SATURN");
        PlanetEntity toPlanetEntity = planetCrudService.getById("MARS");
        TicketDto ticketDto = TicketDto.of(null, createdAt, clientEntity, fromPlanetEntity, toPlanetEntity);
        TicketEntity ticketEntity = TicketEntityMapper.instance().map(ticketDto);
        Timestamp actual = ticketCrudService.create(ticketEntity).getCreatedAt();
        Assertions.assertEquals(createdAt, actual);
    }

    @Test
    void clientCrudServiceGetById() {
        String actual = clientCrudService.getById(1L).getName();
        String expected = "Bill";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void clientCrudServiceCreate() {
        ClientDto clientDto = ClientDto.of(null, "BigBob");
        ClientEntity clientEntity = ClientDtoMapper.instance().map(clientDto);
        ClientEntity actual = clientCrudService.create(clientEntity);
        Assertions.assertNotNull(actual);
    }

    @Test
    void clientCrudServiceDelete() {
        List<ClientEntity> clientEntityExpected = clientCrudService.findAll();
        int expected = clientEntityExpected.size() - 1;
        Long id = clientEntityExpected.stream().map(e -> e.getId()).max(Long::compare).get();
        clientCrudService.delete(id);
        List<ClientEntity> clientEntityActual = clientCrudService.findAll();
        int actual = clientEntityActual.size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void clientCrudServiceUpdate() {
        String expected = "Elon " + LocalDateTime.now();
        ClientDto clientDto = ClientDto.of(2L, expected);
        ClientEntity clientEntity = ClientDtoMapper.instance().map(clientDto);
        clientCrudService.update(clientEntity);
        String actual = clientCrudService.getById(2L).getName();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void planetCrudServiceGetById() {
        String actual = planetCrudService.getById("MARS").getName();
        String expected = "Mars";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void planetCrudServiceCreateDelete() {
        String planetID = "TESTCREATEDELETER";
        PlanetDto planetDto = PlanetDto.of(planetID, "CapEx 999");//planetID);
        PlanetEntity planetEntity = PlanetDtoMapper.instance().map(planetDto);
        System.out.println(" ===>>> planetDto = " + planetDto);
        planetCrudService.create(planetEntity);
        List<PlanetEntity> planetEntityExpected = planetCrudService.findAll();
        int expected = planetEntityExpected.size() - 1;
        planetCrudService.delete(planetID);
        List<PlanetEntity> planetEntityActual = planetCrudService.findAll();
        int actual = planetEntityActual.size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void planetCrudServiceInsertExpectedException() {
        PlanetEntity planetEntity = new PlanetEntity();
        planetEntity.setId("planet777");
        planetEntity.setName("Planet 777");
        Assertions.assertThrows(Exception.class, () -> planetCrudService.create(planetEntity));
    }

    @Test
    void planetCrudServiceUpdate() {
        String expected = "Venus " + LocalDateTime.now();
        PlanetDto planetDto = PlanetDto.of("VENUS", expected);
        PlanetEntity planetEntity = PlanetDtoMapper.instance().map(planetDto);
        planetCrudService.update(planetEntity);
        String actual = planetCrudService.getById("VENUS").getName();
        Assertions.assertEquals(expected, actual);
    }
}