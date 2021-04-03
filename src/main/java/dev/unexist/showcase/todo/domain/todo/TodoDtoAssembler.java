/**
 * @package Quarkus-Pipes-Filters-Showcase
 *
 * @file Todo Dto assembler class
 * @copyright 2020 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv3.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

public class TodoDtoAssembler {
    public static TodoDto fromTodoToDto(final Todo todo) {
        TodoDto dto = new TodoDto();

        dto.setTitle(todo.getTitle());
        dto.setDescription(todo.getDescription());
        dto.setDone(todo.getDone());
        dto.setStart(todo.getDueDate().getStart());
        dto.setDue(todo.getDueDate().getDue());

        return dto;
    }

    public static Todo fromDtoToTodo(final TodoDto dto) {
        Todo todo = new Todo();

        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setDone(dto.getDone());

        DueDate dueDate = new DueDate();

        dueDate.setStart(dto.getStart());
        dueDate.setDue(dto.getDue());

        todo.setDueDate(dueDate);

        return todo;
    }
}