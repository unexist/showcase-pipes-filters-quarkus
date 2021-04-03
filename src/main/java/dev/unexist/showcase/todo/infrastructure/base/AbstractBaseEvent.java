/**
 * @package Quarkus-Pipes-Filters-Showcase
 *
 * @file Abstract base class for filters
 * @copyright 2020 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv3.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.infrastructure.base;

import io.cloudevents.CloudEvent;
import io.cloudevents.v1.CloudEventBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

public abstract class AbstractBaseEvent<DATA> {
    private CloudEvent cloudEvent;

    public AbstractBaseEvent(final CloudEvent cloudEvent) {
        this.cloudEvent = cloudEvent;
    }

    public AbstractBaseEvent(final DATA data) {
        CloudEventBuilder<DATA> builder = CloudEventBuilder.builder();

        builder.withSource(URI.create("https://unexist.dev"));
        builder.withType(data.getClass().getSimpleName());

        this.cloudEvent = builder
                .withId(UUID.randomUUID().toString())
                .withData(data)
                .build();
    }

    public Optional getPayload() {
        return this.cloudEvent.getData();
    }
}
