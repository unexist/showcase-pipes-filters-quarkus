/**
 * @package Quarkus-Pipes-Filters-Showcase
 *
 * @file Todo persistence filter
 * @copyright 2020 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv2.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo.filters;

import dev.unexist.showcase.todo.domain.todo.Todo;
import dev.unexist.showcase.todo.domain.todo.TodoDto;
import dev.unexist.showcase.todo.domain.todo.TodoRepository;
import dev.unexist.showcase.todo.domain.todo.events.TodoConverted;
import dev.unexist.showcase.todo.domain.todo.events.TodoSaved;
import dev.unexist.showcase.todo.infrastructure.base.AbstractBaseFilter;

import javax.inject.Inject;
import java.util.Optional;

public class TodoPersistenceFilter
        extends AbstractBaseFilter<TodoConverted, TodoSaved, Todo>
{
    @Inject
    TodoRepository repository;

    @Override
    public void process(TodoConverted event) {

        Optional<TodoDto> payload = event.getPayload();

        if (payload.isPresent()) {
            Todo todo = (Todo) payload.get();

            this.repository.add(todo);

            this.send(todo, TodoSaved.class);
        }
    }
}