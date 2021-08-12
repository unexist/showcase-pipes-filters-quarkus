/**
 * @package Quarkus-Pipes-Filter-Showcase
 *
 * @file Todo Dto deserializer
 * @copyright 2021 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.infrastructure.deserializer;

import dev.unexist.showcase.todo.domain.todo.TodoBase;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class TodoBaseDeserializer extends ObjectMapperDeserializer<TodoBase> {
    public TodoBaseDeserializer() {
        super(TodoBase.class);
    }
}