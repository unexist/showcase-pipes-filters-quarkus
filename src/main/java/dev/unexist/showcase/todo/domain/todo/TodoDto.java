/**
 * @package Quarkus-Pipes-Filters-Showcase
 *
 * @file Todo base class
 * @copyright 2020 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.unexist.showcase.todo.infrastructure.serializer.DateSerializer;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class TodoDto {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private Boolean done;

    @JsonSerialize(using = DateSerializer.class)
    private LocalDate start;

    @JsonSerialize(using = DateSerializer.class)
    private LocalDate due;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getDue() {
        return due;
    }

    public void setDue(LocalDate due) {
        this.due = due;
    }
}
