/**
 * @package Quarkus-Pipes-Filters-Showcase
 *
 * @file Todo smallrye consumer
 * @copyright 2020 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv2.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.adapters;

import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TodoConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoConsumer.class);

    @Incoming("todo-in")
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    public void receive(String value) {
        LOGGER.info("Value: {}", value);
    }
}
