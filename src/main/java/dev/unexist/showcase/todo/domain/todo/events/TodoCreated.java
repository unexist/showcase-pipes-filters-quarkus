/**
 * @package Quarkus-Pipes-Filters-Showcase
 *
 * @file Todo created event
 * @copyright 2020 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo.events;

import dev.unexist.showcase.todo.domain.todo.TodoBase;
import dev.unexist.showcase.todo.infrastructure.base.AbstractBaseEvent;
import io.cloudevents.CloudEvent;

public class TodoCreated extends AbstractBaseEvent<TodoBase> {

    /**
     * Constructor
     *
     * @param  cloudEvent  A {@link CloudEvent}
     **/

    public TodoCreated(CloudEvent cloudEvent) {
        super(cloudEvent);
    }

    /**
     * Constructor
     *
     * @param  base  A {@link TodoBase}
     **/

    public TodoCreated(TodoBase base) {
        super(base);
    }
}
