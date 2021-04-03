/**
 * @package Quarkus-Pipes-Filters-Showcase
 *
 * @file Todo resource
 * @copyright 2020 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv3.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.adapters;

import dev.unexist.showcase.todo.domain.todo.TodoDto;
import dev.unexist.showcase.todo.domain.todo.events.TodoCreated;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/todo")
public class TodoResource {
    protected static final Logger LOGGER = LoggerFactory.getLogger(TodoResource.class);

    @Inject
    Event<TodoCreated> event;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create new todo")
    @Tag(name = "Todo")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Todo created"),
            @APIResponse(responseCode = "406", description = "Bad data"),
            @APIResponse(responseCode = "500", description = "Server error")
    })
    public Response create(@Valid TodoDto dto) {
        this.event.fire(new TodoCreated(dto));

        LOGGER.info("Sent event={}", TodoCreated.class.getSimpleName());

        return Response.ok().build();
    }
}
