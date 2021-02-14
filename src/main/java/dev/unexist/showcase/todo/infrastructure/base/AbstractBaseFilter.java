/**
 * @package Quarkus-Pipes-Filters-Showcase
 *
 * @file Abstract base class for events
 * @copyright 2020 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv2.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.infrastructure.base;

import io.cloudevents.CloudEvent;
import io.cloudevents.v1.CloudEventBuilder;
import io.cloudevents.v1.CloudEventImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Event;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.UUID;

public abstract class AbstractBaseFilter<IN extends AbstractBaseEvent, OUT extends AbstractBaseEvent, DATA> {
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractBaseFilter.class);

    @Inject
    Event<OUT> event;

    protected abstract void process(@ObservesAsync IN event);

    protected void send(DATA data, Class<OUT> outClazz) {
        CloudEventBuilder<DATA> builder = CloudEventBuilder.builder();

        builder.withSource(URI.create("https://unexist.dev"));
        builder.withType(data.getClass().getSimpleName());

        CloudEventImpl<DATA> ceEvent = builder
                .withId(UUID.randomUUID().toString())
                .withData(data)
                .build();

        try {
            Constructor<OUT> constructor = outClazz.getConstructor(CloudEvent.class);

            this.event.fire(constructor.newInstance(ceEvent));

            LOGGER.info("Fired event {}", outClazz.getSimpleName());
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
