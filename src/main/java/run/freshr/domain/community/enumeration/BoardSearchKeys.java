package run.freshr.domain.community.enumeration;

import static java.util.Arrays.stream;
import static java.util.List.of;
import static run.freshr.common.utils.QueryUtil.searchKeyword;
import static run.freshr.domain.community.entity.QBoard.board;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringPath;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import run.freshr.common.extension.enumeration.SearchEnumExtension;

@Slf4j
public enum BoardSearchKeys implements SearchEnumExtension {

  ALL("전체", of(board.title, board.contents)),
  TITLE("아이디", of(board.title)),
  CONTENTS("이름", of(board.contents));

  private final String value;
  private final List<StringPath> paths;

  BoardSearchKeys(String value, List<StringPath> paths) {
    this.value = value;
    this.paths = paths;
  }

  @Override
  public String getKey() {
    return name();
  }

  @Override
  public String getValue() {
    return value;
  }

  public List<StringPath> getPaths() {
    return paths;
  }

  @Override
  public BooleanBuilder search(String word) {
    return searchKeyword(word, paths);
  }

  public static BoardSearchKeys find(String key) {
    log.info("BoardSearchKeys.find");

    return stream(BoardSearchKeys.values())
        .filter(item -> item.getKey().equalsIgnoreCase(key))
        .findAny()
        .orElse(ALL);
  }
}
