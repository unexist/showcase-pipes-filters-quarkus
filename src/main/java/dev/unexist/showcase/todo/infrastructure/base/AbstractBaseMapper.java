/**
 * @package Showcase
 *
 * @file Base class for objectmapper
 * @copyright 2021-present Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/
 
package dev.unexist.showcase.todo.infrastructure.base;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public abstract class AbstractBaseMapper {
    protected final ObjectMapper mapper;

    /**
     * Constructor
     **/

    AbstractBaseMapper() {
        this.mapper = new ObjectMapper();

        mapper.registerModule(new JavaTimeModule())
                .registerModule(io.cloudevents.jackson.JsonFormat.getCloudEventJacksonModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }
}
