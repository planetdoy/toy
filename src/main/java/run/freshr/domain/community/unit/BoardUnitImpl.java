package run.freshr.domain.community.unit;

import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import run.freshr.annotations.Unit;
import run.freshr.domain.community.entity.Board;
import run.freshr.domain.community.repository.jpa.BoardRepository;
import run.freshr.domain.community.vo.CommunitySearch;

@Slf4j
@Unit
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardUnitImpl implements BoardUnit {

  private final BoardRepository repository;

  @Override
  public Long create(Board entity) {
    log.info("BoardUnit.create");

    return repository.save(entity).getId();
  }

  @Override
  public Boolean exists(Long id) {
    log.info("BoardUnit.exists");

    return repository.existsById(id);
  }

  @Override
  public Board get(Long id) {
    log.info("BoardUnit.get");

    return repository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  @Override
  public Page<Board> getPage(CommunitySearch search) {
    log.info("BoardUnit.getPage");

    return repository.getPage(search);
  }

}
