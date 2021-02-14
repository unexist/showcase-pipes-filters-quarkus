/**
 * @package Quarkus-Pipes-Filters-Showcase
 *
 * @file Todo savedd event
 * @copyright 2020 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv2.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo.events;

import dev.unexist.showcase.todo.domain.todo.Todo;
import dev.unexist.showcase.todo.infrastructure.base.AbstractBaseEvent;
import io.cloudevents.CloudEvent;

public class TodoSaved extends AbstractBaseEvent<Todo> {
    public TodoSaved(CloudEvent cloudEvent) {
        super(cloudEvent);
    }

    public TodoSaved(Todo todo) {
        super(todo);
    }
}
