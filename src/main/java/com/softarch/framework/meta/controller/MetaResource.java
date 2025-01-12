package com.softarch.framework.meta.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.softarch.framework.meta.dto.EntityDto;
import com.softarch.framework.meta.model.Metadata;
import com.softarch.framework.meta.model.Model;
import com.softarch.framework.meta.model.ModelBuilder;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;


@Path("/")
public class MetaResource {

    public static final Logger LOG = LoggerFactory.getLogger(MetaResource.class);
    @Inject
    Map<UUID, Object> entityMap;

    @GET
    @Path("/metadata")
    @Produces(MediaType.APPLICATION_JSON)
    public Response metadata() {
        Model model = new ModelBuilder().withPackageName("com.softarch.framework.app").build();
        LOG.info("metadataList size: {}", model.getMetadataList().size());
        return Response.ok(model).build();
    }

    @GET
    @Path("/data")
    @Produces(MediaType.APPLICATION_JSON)
    public Response data(@QueryParam("id") String id) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        UUID uuid = UUID.fromString(id);
        Object entity = entityMap.get(uuid);
        try {
            json = objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        EntityDto dto = new EntityDto();
        dto.setId(uuid);
        Class<?> clazz = entity.getClass();
        if (clazz.isAnnotationPresent(com.softarch.framework.meta.model.annotation.Metadata.class)
            && !clazz.getAnnotation(com.softarch.framework.meta.model.annotation.Metadata.class).name().isEmpty()) {
            dto.setMetadataName(clazz.getAnnotation(
                com.softarch.framework.meta.model.annotation.Metadata.class).name());
        } else {
            dto.setMetadataName(clazz.getName());
        }
        dto.setEntity(json);
        return Response.ok(dto).build();
    }

    @GET
    @Path("/dummy-material")
    @Produces(MediaType.TEXT_PLAIN)
    public Response dummyMaterial()  {
        String json = """
            {
              "name": "Dummy Material",
              "quantity": 100,
              "price": 200,
              "orderId": 300,
              "stockId": 400,
              "manufacturerCode": "MFG123",
              "note": "This is a dummy material"
            }
            """;
        ObjectMapper objectMapper = new ObjectMapper();
        Object entity;
        try {
            entity = objectMapper.readValue(json, Class.forName("com.softarch.framework.app.Material"));
        } catch (JsonProcessingException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

      UUID uuid = UUID.randomUUID();
        entityMap.put(uuid, entity);
        return Response.ok(uuid.toString()).build();
    }

    @GET
    @Path("/dummy-order")
    @Produces(MediaType.TEXT_PLAIN)
    public Response dummyOrder()  {
        String json = """
            {
              "order_state": 1,
              "order_time": "2023-03-01T12:00:00Z",
              "total": 1000,
              "customer_id": 456,
              "store_id": 789,
              "note": "This is a dummy order"
            }
            """;
        ObjectMapper objectMapper = new ObjectMapper();
        Object entity;
        try {
            entity = objectMapper.readValue(json, Class.forName("com.softarch.framework.app.Order"));
        } catch (JsonProcessingException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

      UUID uuid = UUID.randomUUID();
        entityMap.put(uuid, entity);
        return Response.ok(uuid.toString()).build();
    }
}
