package com.example.part4.repository;

import com.example.part4.entity.Movie;
import com.example.part4.entity.MovieImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieImageRepository imageRepository;


    @Test
    @Commit
    @Transactional
    public void insertMovies() {
        IntStream.rangeClosed(1, 100).forEach(
                i -> {
                    Movie movie = Movie.builder().title("Movie" + i).build();

                    System.out.println("============");

                    movieRepository.save(movie);

                    int count = (int) (Math.random() * 5 + 1);

                    for (int j = 0; j < count; j++) {
                        MovieImage movieImage = MovieImage.builder()
                                .uuid(UUID.randomUUID().toString())
                                .movie(movie)
                                .imgName("test" + j + "jpg")
                                .build();
                        imageRepository.save(movieImage);
                    }

                    System.out.println("===============");

                }

        );
    }
    
    @Test
    public void testListPage() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mmo"));
        Page<Object[]> result = movieRepository.getListPage(pageRequest);
        for (Object[] objects : result.getContent()) {
            System.out.println(Arrays.toString(objects));
        }
    }
}
