package com.example.ex1.repository;

import com.example.ex1.entity.ToDoEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @DisplayName("")
    @Test
    void test() {
        //given
        ToDoEntity entity = ToDoEntity.builder()
                .title("부트끝내기")
                .writer("userA")
                .dueDate(LocalDateTime.of(2024, 10, 21, 23, 56))
                .build();

        //when
        todoRepository.save(entity);

        //then
        Assertions.assertThat(todoRepository.findAll()).hasSize(1);


    }


}