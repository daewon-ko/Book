package com.example.part4.repository;

import com.example.part4.entity.Member;
import com.example.part4.entity.Movie;
import com.example.part4.entity.Review;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMovieReviews() {
        IntStream.rangeClosed(1,200).forEach(
                i -> {
                    Long mno = ((long) (Math.random() * 100) + 1);
                    Long mid = ((long) (Math.random() * 99) + 1);

                    Member member = Member.builder()
                            .mid(mid)
                            .build();

                    log.info("member:{} ", member);

                    Movie movie = Movie.builder()
                            .mmo(mno)
                            .build();

                    log.info("movie:{} ", movie);
                    Review review = Review.builder()
                            .member(member)
                            .movie(movie)
                            .text("리뷰입니다." + i)
                            .grade((int) (Math.random() * 5) + 1)
                            .build();

                    reviewRepository.save(review);

                }
        );
    }

}
