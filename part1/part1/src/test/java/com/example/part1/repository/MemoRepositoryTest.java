package com.example.part1.repository;

import com.example.part1.entity.Memo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemoRepositoryTest {
    @Autowired
    MemoRepository memoRepository;

    @Test
    void testClas() {
        System.out.println(memoRepository.getClass().getName());
    }
    @Test
    public void testInsertDummies() {
        IntStream.rangeClosed(1,100).forEach(i ->
        {
            Memo memo = Memo.builder().memoText("Sample" + i).build();
            memoRepository.save(memo);
        });
    }
    @Test
    public void testSelect() {
        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);
        System.out.println("-------");
        if (result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    @Test
    public void testUpdate() {
        Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();
        System.out.println(memoRepository.save(memo));
    }
    @Test
    public void deleteDateById() {
        Long mno = 100L;

        Optional<Memo> beforeDelete = memoRepository.findById(mno);
        Assertions.assertThat(beforeDelete.isPresent()).isTrue();

        memoRepository.deleteById(mno);

        Optional<Memo> afterDelete = memoRepository.findById(mno);
        Assertions.assertThat(afterDelete.isPresent()).isFalse();
    }
}
