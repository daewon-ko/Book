//package org.zerock.guestbook.repository;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.zerock.guestbook.entity.GuestBook;
//
//import java.util.Optional;
//import java.util.stream.IntStream;
//
//@SpringBootTest
//class GuestbookRepositoryTest {
//
//    @Autowired
//    private JDBCRepository jdbcRepository;
//
//    @Test
//    public void insertDummies() {
//        IntStream.rangeClosed(1, 300).forEach(
//                i -> {
//                    GuestBook guestb = GuestBook.builder()
//                            .title("Title " + i)
//                            .content("Content" + i)
//                            .writer("user" + (i % 10))
//                            .build();
//                    System.out.println(jdbcRepository.save(guestb));
//                }
//        );
//    }
//
//    @Test
//    public void updateTest() {
//        Optional<GuestBook> result = jdbcRepository.findById(300L);
//
//        if (result.isPresent()) {
//
//            GuestBook guestBook = result.get();
//
//            guestBook.changeContent("Changed Content");
//            guestBook.changeTitle("Changed Title");
//
//            jdbcRepository.save(guestBook);
//        }
//    }
//
//    @Test
//    @DisplayName("단일 항목 검색 테스트")
//    public void testQuery1() {
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
//        QGuestBook qGuestBook = QGuestBook.guestBook;
//        String keyword = "1";
//        BooleanBuilder builder = new BooleanBuilder();
//        BooleanExpression expression = qGuestBook.title.contains(keyword);
//        builder.and(expression);
//        Page<GuestBook> result = jdbcRepository.findAll(builder, pageable);
//        result.stream().forEach(guestBook -> {
//            System.out.println(guestBook);
//        });
//
//    }
//
//    @Test
//    @DisplayName("다중 항목 검색 테스트")
//    public void testQuery2() {
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
//        QGuestBook qGuestBook = QGuestBook.guestBook;
//        String keyword = "1";
//        BooleanBuilder builder = new BooleanBuilder();
//        BooleanExpression exTitle = qGuestBook.title.contains(keyword);
//        BooleanExpression exContent = qGuestBook.content.contains(keyword);
//        BooleanExpression exAll = exTitle.or(exContent);
//        builder.and(exAll);
//        builder.and(qGuestBook.gno.gt(0L));
//
//        Page<GuestBook> result = jdbcRepository.findAll(builder, pageable);
//
//        result.stream().forEach(guestBook -> {
//            System.out.println(guestBook);
//        });
//
//    }
//}
