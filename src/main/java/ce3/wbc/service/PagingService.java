package ce3.wbc.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class PagingService {
    public static final int PAGE_LENGTH = 6;// 한페이지 몇개?
    // 하단 페이징 숫자
    public List<Integer> getPageNumbers(int pageNumber, int totalPages) {
        int startPage = Math.max((pageNumber - (PAGE_LENGTH / 2)), 0); // 가장 왼쪽
        int endPage = Math.min((startPage + PAGE_LENGTH), totalPages); // 가장 오른쪽

        return IntStream.range(startPage, endPage).boxed().toList();
    }

    public int getPageLength() {
        return PAGE_LENGTH;
    }

}
