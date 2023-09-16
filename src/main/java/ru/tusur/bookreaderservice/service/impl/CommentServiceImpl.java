package ru.tusur.bookreaderservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.tusur.bookreaderservice.entity.Comment;
import ru.tusur.bookreaderservice.entity.User;
import ru.tusur.bookreaderservice.repository.CommentRepository;
import ru.tusur.bookreaderservice.repository.UserRepository;
import ru.tusur.bookreaderservice.service.CommentService;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(UserRepository userRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public Comment addNewComment(Comment comment) {
        log.info("Got for create comment:'{}'", comment);

        User user = userRepository.findByEmail(comment.getAuthorName()).orElseThrow(
                () -> new UsernameNotFoundException("User not found by email like:" + comment.getAuthorName())
        );
        String userNickname = user.getNickname();
        comment.setAuthorName(userNickname);
        comment.setCreatedAt(LocalDate.now());
        comment.setAvatar(user.getAvatarImageUrl());
        Comment createdComment = commentRepository.save(comment);
        log.info("Created new comment: {}", createdComment);
        return createdComment;
    }

    @Override
    public List<Comment> getCommentsByEventId(Long eventId) {
        List<Comment> resultFromRepo = commentRepository.findAllByEventId(eventId);
        log.info("From Repo we got: {}", resultFromRepo);
        return resultFromRepo;
    }

    @Override
    public Long getCommentCounterByEventId(Long eventId) {
        return commentRepository.getCommentCounterByEventId(eventId);
    }
}
