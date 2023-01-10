package run.freshr.domain.community.repository.jpa;

import org.springframework.data.domain.Page;
import run.freshr.domain.community.entity.Board;
import run.freshr.domain.community.vo.CommunitySearch;

public interface BoardRepositoryCustom {

  Page<Board> getPage(CommunitySearch search);

}
