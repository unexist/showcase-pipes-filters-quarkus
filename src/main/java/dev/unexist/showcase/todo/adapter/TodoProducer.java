/**
 * @package Quarkus-Pipes-Filters-Showcase
 *
 * @file Todo producer
 * @copyright 2020-present Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.adapter;

import dev.unexist.showcase.todo.domain.todo.DueDate;
import dev.unexist.showcase.todo.domain.todo.Todo;
import dev.unexist.showcase.todo.domain.todo.TodoBase;
import dev.unexist.showcase.todo.domain.todo.events.TodoSaved;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.Optional;

@ApplicationScoped
public class TodoProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoProducer.class);

    @Channel("todo_out")
    Emitter<TodoBase> emitter;

    public void process(@Observes TodoSaved event) {
        LOGGER.info("Received event={}", event.getClass().getSimpleName());

        Optional<Todo> payload = event.getPayload(Todo.class);

        if (payload.isPresent()) {
            Todo todo = payload.get();

            LOGGER.info("Received event payload={}", todo);

            TodoBase base = new TodoBase();

            base.setTitle(todo.getTitle());
            base.setDescription(todo.getDescription());
            base.setDone(todo.getDone());

            DueDate dueDate = new DueDate();

            dueDate.setStart(todo.getDueDate().getStart());
            dueDate.setDue(todo.getDueDate().getDue());

            base.setDueDate(dueDate);

            this.emitter.send(base);
        }
    }
}