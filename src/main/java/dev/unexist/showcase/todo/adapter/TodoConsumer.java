/**
 * @package Quarkus-Pipes-Filters-Showcase
 *
 * @file Todo consumer
 * @copyright 2020 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.adapter;

import dev.unexist.showcase.todo.domain.todo.TodoDto;
import dev.unexist.showcase.todo.domain.todo.events.TodoCreated;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@ApplicationScoped
public class TodoConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoConsumer.class);

    @Inject
    Event<TodoCreated> event;

    @Incoming("todo-in")
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    public void receive(TodoDto dto) {
        if (null != dto) {
            this.event.fire(new TodoCreated(dto));

            LOGGER.info("Sent event={}", TodoCreated.class.getSimpleName());
        }
    }
}
