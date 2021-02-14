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

import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

import static io.smallrye.mutiny.Multi.createFrom;

@ApplicationScoped
public class TodoProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoConsumer.class);

    @Outgoing("todo-out")
    public Multi<String> send() {
        return createFrom().item("Todo");
    }
}
