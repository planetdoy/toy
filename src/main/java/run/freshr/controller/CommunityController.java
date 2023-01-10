package run.freshr.controller;

import static run.freshr.common.config.URIConfig.uriCommunityBoard;
import static run.freshr.common.config.URIConfig.uriCommunityBoardId;
import static run.freshr.common.utils.RestUtil.error;
import static run.freshr.domain.auth.enumeration.Role.Secured.ALPHA;
import static run.freshr.domain.auth.enumeration.Role.Secured.ANONYMOUS;
import static run.freshr.domain.auth.enumeration.Role.Secured.BETA;
import static run.freshr.domain.auth.enumeration.Role.Secured.DELTA;
import static run.freshr.domain.auth.enumeration.Role.Secured.GAMMA;
import static run.freshr.domain.auth.enumeration.Role.Secured.USER;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import run.freshr.domain.community.dto.request.BoardCreateRequest;
import run.freshr.domain.community.dto.request.BoardUpdateRequest;
import run.freshr.domain.community.validator.CommunityValidator;
import run.freshr.domain.community.vo.CommunitySearch;
import run.freshr.service.CommunityService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommunityController {

  private final CommunityService service;
  private final CommunityValidator validator;

  @Secured({ALPHA, BETA, GAMMA, DELTA, USER, ANONYMOUS})
  @PostMapping(uriCommunityBoard)
  public ResponseEntity<?> createBoard(@RequestBody @Valid BoardCreateRequest dto) {
    log.info("CommunityController.createBoard");

    return service.createBoard(dto);
  }

  @Secured({ALPHA, BETA, GAMMA, DELTA, USER, ANONYMOUS})
  @GetMapping(uriCommunityBoard)
  public ResponseEntity<?> getBoardPage(@ModelAttribute @Valid CommunitySearch search,
      Errors errors) {
    log.info("CommunityController.getBoardPage");

    validator.getBoardPage(search, errors);

    if (errors.hasErrors()) {
      return error(errors);
    }

    return service.getBoardPage(search);
  }

  @Secured({ALPHA, BETA, GAMMA, DELTA, USER, ANONYMOUS})
  @GetMapping(uriCommunityBoardId)
  public ResponseEntity<?> getBoard(@PathVariable Long id) {
    log.info("CommunityController.getBoard");

    return service.getBoard(id);
  }

  @Secured({ALPHA, BETA, GAMMA, DELTA, USER, ANONYMOUS})
  @PutMapping(uriCommunityBoardId)
  public ResponseEntity<?> updateBoard(@PathVariable Long id,
      @RequestBody @Valid BoardUpdateRequest dto) {
    log.info("CommunityController.updateBoard");

    return service.updateBoard(id, dto);
  }

  @Secured({ALPHA, BETA, GAMMA, DELTA, USER, ANONYMOUS})
  @DeleteMapping(uriCommunityBoardId)
  public ResponseEntity<?> removeBoard(@PathVariable Long id) {
    log.info("CommunityController.removeBoard");

    return service.removeBoard(id);
  }

}
