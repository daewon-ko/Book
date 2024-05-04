package org.zerock.guestbook.common;

import lombok.Getter;
import org.zerock.guestbook.dto.PageRequestDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class Pagination <EN> {
    private int totalRecordCount;
    private int totalPageCount;
    private int startPage;
    private int endPage;
    private int limitStart;
    private boolean isExistPrev;
    private boolean isExistNext;

    private List<Integer> pageList;

    public Pagination(int totalRecordCount, PageRequestDTO param) {
        if (totalRecordCount > 0) {
            this.totalRecordCount = totalRecordCount;

        }
        caculation(param);
    }

    private void caculation(PageRequestDTO param) {

        // 전체 페이지 수 계산
        totalPageCount = ((totalRecordCount) - 1) / param.getRecordSize() + 1;

        // 현재 페이지 번호가 전체 페이지 수보다 큰 경우, 현재 페이지 번호에 전체 페이지 수 저장
        if (param.getPage() > totalPageCount) {
            param.setPage(totalPageCount);
        }

        // 첫 페이지 번호 계산
        startPage = ((param.getPage() - 1) / param.getPageSize()) * param.getPageSize() + 1;

        // 끝 페이지 번호 계산
        endPage = startPage + param.getPageSize() - 1;

        // 끝 페이지가 전체 페이지 수 보다 큰 경우, 끝 페이지 전체 페이지 수 저장
        if (endPage > totalPageCount) {
            endPage = totalPageCount;
        }

        // Limit 시작 위치 계산
        limitStart = (param.getPage() - 1) * param.getRecordSize();

        // 이전 페이지 존재 여부 확
        isExistPrev =  param.getPage() > 1;

        // 다음 페이지 존재 여부 확인

        isExistNext = (endPage * param.getRecordSize()) < totalRecordCount;

        pageList = IntStream.rangeClosed(startPage, endPage).boxed().collect(Collectors.toList());
    }
}
