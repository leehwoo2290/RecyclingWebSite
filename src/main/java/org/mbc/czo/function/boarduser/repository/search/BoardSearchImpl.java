package org.mbc.czo.function.boarduser.repository.search;

//1

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.mbc.czo.function.boarduser.domain.Board;
import org.mbc.czo.function.boarduser.domain.QBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {
    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search(Pageable pageable) {

        QBoard board = QBoard.board ;
        JPQLQuery<Board> query = from(board);
        query.where(board.title.contains("1"));

        List<Board> boards = query.fetch(); //board결과를 리스트로 받음
        long count = query.fetchCount(); //count쿼리 실행

        return null;
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {

        QBoard board = QBoard.board ;
        JPQLQuery<Board> query = from(board);

        if( (types != null && types. length >0) && keyword != null ){

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type : types){

                switch(type) {
                    case "제목" :
                        booleanBuilder.or(board.title.contains(keyword));
                        break;

                    case "내용" :
                        booleanBuilder.or(board.content.contains(keyword));
                        break;

                    case "작성자" :
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }

        return null;
    }
}
