package com.ticketsystem.models.mappers;

import com.ticketsystem.models.dto.CommentDto;
import com.ticketsystem.models.entities.Comment;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CommentMapper implements Function<Comment, CommentDto> {

    @Override
    public CommentDto apply(Comment comment) {
        return null;
    }
}
