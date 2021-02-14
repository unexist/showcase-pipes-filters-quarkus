/**
 * @package Quarkus-Testing-Showcase
 *
 * @file Todo Dto deserializer
 * @copyright 2021 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv2.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.infrastructure.deserializer;

import dev.unexist.showcase.todo.domain.todo.TodoDto;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class TodoDtoDeserializer extends ObjectMapperDeserializer<TodoDto> {
    public TodoDtoDeserializer() {
        super(TodoDto.class);
    }
}