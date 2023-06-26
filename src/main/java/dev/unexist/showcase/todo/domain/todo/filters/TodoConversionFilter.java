/**
 * @package Quarkus-Pipes-Filters-Showcase
 *
 * @file Todo conversion filter
 * @copyright 2020-present Christoph Kappel <christoph@unexist.dev>
 * @version $Id: src/main/java/dev/unexist/showcase/todo/domain/todo/TodoRepository.java,v 7
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo.filters;

import dev.unexist.showcase.todo.domain.todo.Todo;
import dev.unexist.showcase.todo.domain.todo.TodoBase;
import dev.unexist.showcase.todo.domain.todo.events.TodoConverted;
import dev.unexist.showcase.todo.domain.todo.events.TodoCreated;
import dev.unexist.showcase.todo.infrastructure.base.AbstractBaseFilter;

import jakarta.enterprise.event.Observes;
import java.util.Optional;

public class TodoConversionFilter
        extends AbstractBaseFilter<TodoCreated, TodoConverted, Todo> {
    @Override
    public void process(@Observes TodoCreated event) {
        LOGGER.info("Received event={}", event.getClass().getSimpleName());

        Optional<TodoBase> payload = event.getPayload(TodoBase.class);

        if (payload.isPresent()) {
            TodoBase base = payload.get();

            LOGGER.info("Received event payload={}", base);

            Todo todo = new Todo();

            todo.update(base);

            this.send(todo, TodoConverted.class);
        }
    }
}
