package run.freshr.service;

import org.springframework.http.ResponseEntity;
import run.freshr.domain.community.dto.request.BoardCreateRequest;
import run.freshr.domain.community.dto.request.BoardUpdateRequest;
import run.freshr.domain.community.vo.CommunitySearch;

public interface CommunityService {

  ResponseEntity<?> createBoard(BoardCreateRequest dto);

  ResponseEntity<?> getBoardPage(CommunitySearch search);

  ResponseEntity<?> getBoard(Long id);

  ResponseEntity<?> updateBoard(Long id, BoardUpdateRequest dto);

  ResponseEntity<?> removeBoard(Long id);

}
