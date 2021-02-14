/**
 * @package Quarkus-Pipes-Filters-Showcase
 *
 * @file Todo producer
 * @copyright 2020 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv2.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.adapters;

import dev.unexist.showcase.todo.domain.todo.Todo;
import dev.unexist.showcase.todo.domain.todo.TodoDto;
import dev.unexist.showcase.todo.domain.todo.TodoDtoAssembler;
import dev.unexist.showcase.todo.domain.todo.events.TodoSaved;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.Optional;

@ApplicationScoped
public class TodoProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoConsumer.class);

    private TodoDto dto;

    public void process(@Observes TodoSaved event) {
        LOGGER.info("Received event={}", event.getClass().getSimpleName());

        Optional<Todo> payload = event.getPayload();

        if (payload.isPresent()) {
            Todo todo = payload.get();

            LOGGER.info("Received event payload={}", todo);

            this.dto = TodoDtoAssembler.fromTodoToDto(todo);

            this.send();
        }
    }

    @Outgoing("todo-out")
    @Broadcast
    public TodoDto send() {
        return this.dto;
    }
}