package run.freshr.domain.community.repository.jpa;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static org.springframework.util.StringUtils.hasLength;
import static run.freshr.domain.community.entity.QBoard.board;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import run.freshr.common.utils.QueryUtil;
import run.freshr.domain.community.entity.Board;
import run.freshr.domain.community.enumeration.BoardSearchKeys;
import run.freshr.domain.community.vo.CommunitySearch;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public Page<Board> getPage(CommunitySearch search) {
    JPAQuery<Board> query = queryFactory.selectFrom(board)
        .where(board.deleteFlag.isFalse(),
            board.useFlag.isTrue());

    String word = search.getWord();

    if (hasLength(word)) {
      query.where(QueryUtil.searchEnum(word, BoardSearchKeys.find(search.getKey())));
    }

    LocalDate startDate = search.getStartDate();
    LocalDate endDate = search.getEndDate();

    if (!isNull(startDate)) {
      query.where(board.createAt.goe(startDate.atStartOfDay()));
    }

    if (!isNull(endDate)) {
      query.where(board.createAt.loe(endDate.atTime(LocalTime.MAX)));
    }

    String orderType = ofNullable(search.getOrderType()).orElse("");
    String orderBy = ofNullable(search.getOrderBy()).orElse(Order.DESC.name());

    if (orderType.equalsIgnoreCase("title")) {
      query.orderBy(new OrderSpecifier<>(orderBy.equalsIgnoreCase("desc")
          ? Order.DESC : Order.ASC, board.title));
    }

    if (orderType.equalsIgnoreCase("create")) {
      query.orderBy(new OrderSpecifier<>(orderBy.equalsIgnoreCase("desc")
          ? Order.DESC : Order.ASC, board.createAt));
    }

    query.orderBy(board.id.desc());

    return QueryUtil.paging(query, board, search);
  }

}
