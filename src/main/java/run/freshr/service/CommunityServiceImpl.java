package run.freshr.service;

import static run.freshr.common.utils.RestUtil.buildId;
import static run.freshr.common.utils.RestUtil.error;
import static run.freshr.common.utils.RestUtil.getExceptions;
import static run.freshr.common.utils.RestUtil.ok;
import static run.freshr.utils.MapperUtil.map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.freshr.domain.community.dto.request.BoardCreateRequest;
import run.freshr.domain.community.dto.request.BoardUpdateRequest;
import run.freshr.domain.community.dto.response.BoardListResponse;
import run.freshr.domain.community.dto.response.BoardResponse;
import run.freshr.domain.community.entity.Board;
import run.freshr.domain.community.unit.BoardUnit;
import run.freshr.domain.community.vo.CommunitySearch;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityServiceImpl implements CommunityService {

  private final BoardUnit boardUnit;

  @Override
  @Transactional
  public ResponseEntity<?> createBoard(BoardCreateRequest dto) {
    log.info("CommunityService.createBoard");

    Board entity = Board.createEntity(dto.getTitle(), dto.getContents());
    Long id = boardUnit.create(entity);

    return ok(buildId(id));
  }

  @Override
  public ResponseEntity<?> getBoardPage(CommunitySearch search) {
    log.info("CommunityService.getBoardPage");

    Page<Board> entityPage = boardUnit.getPage(search);
    Page<BoardListResponse> page = entityPage.map(item -> map(item, BoardListResponse.class));

    return ok(page);
  }

  @Override
  @Transactional
  public ResponseEntity<?> getBoard(Long id) {
    log.info("CommunityService.getBoard");

    if (!boardUnit.exists(id)) {
      return error(getExceptions().getEntityNotFound());
    }

    Board entity = boardUnit.get(id);

    if (entity.getDeleteFlag()) {
      return error(getExceptions().getEntityNotFound());
    }

    if (!entity.getUseFlag()) {
      return error(getExceptions().getEntityNotFound());
    }

    entity.addViews();

    BoardResponse data = map(entity, BoardResponse.class);

    return ok(data);
  }

  @Override
  @Transactional
  public ResponseEntity<?> updateBoard(Long id, BoardUpdateRequest dto) {
    log.info("CommunityService.updateBoard");

    if (!boardUnit.exists(id)) {
      return error(getExceptions().getEntityNotFound());
    }

    Board entity = boardUnit.get(id);

    if (entity.getDeleteFlag()) {
      return error(getExceptions().getEntityNotFound());
    }

    if (!entity.getUseFlag()) {
      return error(getExceptions().getEntityNotFound());
    }

    entity.updateEntity(dto.getTitle(), dto.getContents());

    return ok();
  }

  @Override
  @Transactional
  public ResponseEntity<?> removeBoard(Long id) {
    log.info("CommunityService.removeBoard");

    if (!boardUnit.exists(id)) {
      return error(getExceptions().getEntityNotFound());
    }

    Board entity = boardUnit.get(id);

    if (entity.getDeleteFlag()) {
      return error(getExceptions().getEntityNotFound());
    }

    if (!entity.getUseFlag()) {
      return error(getExceptions().getEntityNotFound());
    }

    entity.removeEntity();

    return ok();
  }

}
