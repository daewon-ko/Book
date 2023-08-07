package org.zerock.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.entity.GuestBook;

import java.util.stream.IntStream;

@SpringBootTest
class JdbcRepositoryImplTests {
    @Autowired
    private JDBCRepository jdbcRepository;

    @Test
    @Transactional
    void save() {
        IntStream.range(101, 200).forEach(
                i -> {
                    GuestBook build = GuestBook.builder()
                            .title("Title" + i)
                            .content("하하하" + i)
                            .writer("작가" + i)
                            .build();
                    jdbcRepository.save(build);
                    System.out.println(build);
                }
        );

    }
}
