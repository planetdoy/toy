package run.freshr.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.freshr.common.dto.response.IdResponse;
import run.freshr.domain.community.dto.request.BoardCreateRequest;
import run.freshr.domain.community.dto.request.BoardUpdateRequest;
import run.freshr.domain.community.dto.response.BoardListResponse;
import run.freshr.domain.community.dto.response.BoardResponse;
import run.freshr.domain.community.unit.BoardUnit;
import run.freshr.domain.community.vo.CommunitySearch;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityServiceImpl implements CommunityService {

  private final BoardUnit boardUnit;

  @Override
  public ResponseEntity<?> createBoard(BoardCreateRequest dto) {
    log.info("CommunityService.createBoard");

    /**
     * Response DTO: {@link IdResponse}
     */
    return null;
  }

  @Override
  public ResponseEntity<?> getBoardPage(CommunitySearch search) {
    log.info("CommunityService.getBoardPage");

    /**
     * Response DTO: {@link BoardListResponse}
     */
    return null;
  }

  @Override
  public ResponseEntity<?> getBoard(Long id) {
    log.info("CommunityService.getBoard");

    /**
     * Response DTO: {@link BoardResponse}
     */
    return null;
  }

  @Override
  public ResponseEntity<?> updateBoard(Long id, BoardUpdateRequest dto) {
    log.info("CommunityService.updateBoard");

    return null;
  }

  @Override
  public ResponseEntity<?> removeBoard(Long id) {
    log.info("CommunityService.removeBoard");

    return null;
  }

}
