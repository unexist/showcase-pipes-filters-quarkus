/**
 * @package Quarkus-Pipes-Filters-Showcase
 *
 * @file Abstract base class for events
 * @copyright 2020 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.infrastructure.base;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.core.data.PojoCloudEventData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.UUID;

public abstract class
    AbstractBaseFilter<IN extends AbstractBaseEvent, OUT extends AbstractBaseEvent, DATA>
        extends AbstractBaseMapper
{
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractBaseFilter.class);

    @Inject
    Event<OUT> event;

    /**
     * Process event
     *
     * @param  event  A {@link Event} to process
     **/

    protected abstract void process(IN event);

    /**
     * Send event
     *
     * @param  data      Event data
     * @param  outClazz  Class type to send
     **/

    protected void send(DATA data, Class<OUT> outClazz) {
        CloudEventBuilder template = CloudEventBuilder.v1()
            .withSource(URI.create("https://unexist.dev"))
            .withType(data.getClass().getSimpleName());

        PojoCloudEventData<DATA> cloudEventData = PojoCloudEventData.wrap(data,
                this.mapper::writeValueAsBytes);

        CloudEvent cloudEvent = template.newBuilder()
                .withId(UUID.randomUUID().toString())
                .withData(cloudEventData)
                .build();

        try {
            Constructor<OUT> constructor = outClazz.getConstructor(CloudEvent.class);

            this.event.fire(constructor.newInstance(cloudEvent));

            LOGGER.info("Fired event {}", outClazz.getSimpleName());
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            LOGGER.error("Couldn't fire event", e);
        }
    }
}
