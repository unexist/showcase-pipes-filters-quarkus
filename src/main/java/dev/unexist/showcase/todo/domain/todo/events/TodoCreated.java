/**
 * @package Quarkus-Pipes-Filters-Showcase
 *
 * @file Todo created event
 * @copyright 2020 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv3.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo.events;

import dev.unexist.showcase.todo.domain.todo.TodoDto;
import dev.unexist.showcase.todo.infrastructure.base.AbstractBaseEvent;
import io.cloudevents.CloudEvent;

public class TodoCreated extends AbstractBaseEvent<TodoDto> {
    public TodoCreated(CloudEvent cloudEvent) {
        super(cloudEvent);
    }

    public TodoCreated(TodoDto todoDto) {
        super(todoDto);
    }
}
