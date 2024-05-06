package com.example.part1.repository;

import com.example.part1.entity.Memo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Test
    public void testPageDefault() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Memo> result = memoRepository.findAll(pageable);
        System.out.println(result);
        System.out.println();
        System.out.println(result.getTotalPages());
        System.out.println(result.getTotalElements());
        System.out.println(result.getSize());
    }
    @Test
    public void testSort() {
        Sort sort1 = Sort.by("mno").descending();
        Pageable pageable = PageRequest.of(0, 10, sort1);
        Page<Memo> result = memoRepository.findAll(pageable);
        result.get().forEach(
                memo -> System.out.println(memo)
        );
    }

    @Test
    public void testQueryMethods() {
        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);
        for (Memo memo : list) {
            System.out.println(memo);
        }
    }

    @Test
    public void testQueryMethodWithPageable() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
        Page<Memo> result = memoRepository.findByMnoBetween(10L, 50L, pageable);
        result.get().forEach(
                memo -> System.out.println(memo)
        );

    }
    @Test
    @Transactional
    @Commit
    public void testDeleteQueryMethods() {
        memoRepository.deleteByMnoLessThan(80L);

    }
}
