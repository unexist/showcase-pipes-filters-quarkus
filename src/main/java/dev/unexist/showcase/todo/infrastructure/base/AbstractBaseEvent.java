/**
 * @package Quarkus-Pipes-Filters-Showcase
 *
 * @file Abstract base class for filters
 * @copyright 2020-present Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.infrastructure.base;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.CloudEventUtils;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.core.data.PojoCloudEventData;
import io.cloudevents.jackson.PojoCloudEventDataMapper;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

public abstract class AbstractBaseEvent<DATA> extends AbstractBaseMapper {
    private final CloudEvent cloudEvent;

    /**
     * Constructor
     *
     * @param  cloudEvent  A {@link CloudEvent}
     **/

    public AbstractBaseEvent(final CloudEvent cloudEvent) {
        this.cloudEvent = cloudEvent;
    }

    /**
     * Constructor
     *
     * @param  data  A {@link DATA}
     **/

    public AbstractBaseEvent(final DATA data) {
        CloudEventBuilder template = CloudEventBuilder.v1()
            .withSource(URI.create("https://unexist.dev"))
            .withType(data.getClass().getSimpleName());

        PojoCloudEventData<DATA> cloudEventData = PojoCloudEventData.wrap(data,
                this.mapper::writeValueAsBytes);

        this.cloudEvent = template.newBuilder()
                .withId(UUID.randomUUID().toString())
                .withData(cloudEventData)
                .build();
    }

    /**
     * Get payload
     *
     * @return Payload of this event
     **/

    public Optional<DATA> getPayload(Class<DATA> dataClazz) {
        DATA data = null;

        PojoCloudEventData<DATA> cloudEventData = CloudEventUtils.mapData(this.cloudEvent,
            PojoCloudEventDataMapper.from(this.mapper, dataClazz));

        return Optional.ofNullable(cloudEventData.getValue());
    }
}
