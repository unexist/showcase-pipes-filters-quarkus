/**
 * @package Quarkus-Pipes-Filters-Showcase
 * @file Todo persistence filter
 * @copyright 2020 Christoph Kappel <christoph@unexist.dev>
 * @version $Id: src/main/java/dev/unexist/showcase/todo/domain/todo/TodoRepository.java,v 7
 *
 * This program can be distributed under the terms of the GNU GPLv2.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo.filters;

import dev.unexist.showcase.todo.domain.todo.DueDate;
import dev.unexist.showcase.todo.domain.todo.Todo;
import dev.unexist.showcase.todo.domain.todo.TodoDto;
import dev.unexist.showcase.todo.domain.todo.events.TodoConverted;
import dev.unexist.showcase.todo.domain.todo.events.TodoCreated;
import dev.unexist.showcase.todo.infrastructure.base.AbstractBaseFilter;

import java.util.Optional;

public class TodoConversionFilter
        extends AbstractBaseFilter<TodoCreated, TodoConverted, Todo>
{

    @Override
    public void process(TodoCreated event) {
        Todo todo = new Todo();

        Optional<TodoDto> payload = event.getPayload();

        if (payload.isPresent()) {
            TodoDto dto = (TodoDto) payload.get();

            todo.setTitle(dto.getTitle());
            todo.setDescription(dto.getDescription());
            todo.setDone(dto.getDone());

            DueDate dueDate = new DueDate();

            dueDate.setStart(dto.getStart());
            dueDate.setDue(dto.getDue());

            todo.setDueDate(dueDate);

            LOGGER.info("Received event payload={}", dto);
        }

        this.send(todo, TodoConverted.class);
    }
}
