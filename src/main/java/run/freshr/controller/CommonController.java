package run.freshr.controller;

import static java.lang.System.lineSeparator;
import static java.nio.file.Files.readAllLines;
import static java.util.stream.Collectors.joining;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static run.freshr.common.config.URIConfig.uriCommonAttach;
import static run.freshr.common.config.URIConfig.uriCommonAttachExist;
import static run.freshr.common.config.URIConfig.uriCommonAttachId;
import static run.freshr.common.config.URIConfig.uriCommonAttachIdDownload;
import static run.freshr.common.config.URIConfig.uriCommonEnum;
import static run.freshr.common.config.URIConfig.uriCommonEnumPick;
import static run.freshr.common.config.URIConfig.uriCommonHeartbeat;
import static run.freshr.common.utils.RestUtil.getConfig;
import static run.freshr.common.utils.RestUtil.ok;
import static run.freshr.domain.auth.enumeration.Role.Secured.ALPHA;
import static run.freshr.domain.auth.enumeration.Role.Secured.ANONYMOUS;
import static run.freshr.domain.auth.enumeration.Role.Secured.BETA;
import static run.freshr.domain.auth.enumeration.Role.Secured.DELTA;
import static run.freshr.domain.auth.enumeration.Role.Secured.GAMMA;
import static run.freshr.domain.auth.enumeration.Role.Secured.USER;

import java.io.IOException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import run.freshr.domain.common.dto.request.AttachCreateRequest;
import run.freshr.mappers.EnumMapper;
import run.freshr.service.CommonService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommonController {

  private final EnumMapper enumMapper;
  private final CommonService service;

  @GetMapping(uriCommonHeartbeat)
  public String getHeartBeat() throws IOException {
    log.info("CommonController.getHeartBeat");

    return readAllLines(getConfig().getHeartbeat().getFile().toPath())
        .stream()
        .collect(joining(lineSeparator()));
  }

  //  _______ .__   __.  __    __  .___  ___.
  // |   ____||  \ |  | |  |  |  | |   \/   |
  // |  |__   |   \|  | |  |  |  | |  \  /  |
  // |   __|  |  . `  | |  |  |  | |  |\/|  |
  // |  |____ |  |\   | |  `--'  | |  |  |  |
  // |_______||__| \__|  \______/  |__|  |__|

  @Secured({ALPHA, BETA, GAMMA, DELTA, USER, ANONYMOUS})
  @GetMapping(uriCommonEnum)
  public ResponseEntity<?> getEnumList() {
    log.info("CommonController.getEnumList");

    return ok(enumMapper.getAll());
  }

  @Secured({ALPHA, BETA, GAMMA, DELTA, USER, ANONYMOUS})
  @GetMapping(uriCommonEnumPick)
  public ResponseEntity<?> getEnum(@PathVariable String pick) {
    log.info("CommonController.getEnum");

    return ok(enumMapper.get(pick.toLowerCase()));
  }

  //      ___   .___________.___________.    ___       ______  __    __
  //     /   \  |           |           |   /   \     /      ||  |  |  |
  //    /  ^  \ `---|  |----`---|  |----`  /  ^  \   |  ,----'|  |__|  |
  //   /  /_\  \    |  |        |  |      /  /_\  \  |  |     |   __   |
  //  /  _____  \   |  |        |  |     /  _____  \ |  `----.|  |  |  |
  // /__/     \__\  |__|        |__|    /__/     \__\ \______||__|  |__|

  @Secured({ALPHA, BETA, GAMMA, DELTA, USER})
  @PostMapping(value = uriCommonAttach, consumes = MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> createAttach(@ModelAttribute @Valid AttachCreateRequest dto)
      throws Exception {
    log.info("CommonController.createAttach");

    return service.createAttach(dto);
  }

  @Secured({ALPHA, BETA, GAMMA, DELTA, USER})
  @GetMapping(uriCommonAttachExist)
  public ResponseEntity<?> existAttach(@PathVariable Long id) {
    log.info("CommonController.existAttach");

    return service.existAttach(id);
  }

  @Secured({ALPHA, BETA, GAMMA, DELTA, USER})
  @GetMapping(uriCommonAttachIdDownload)
  public ResponseEntity<?> getAttachDownload(@PathVariable Long id)
      throws Exception {
    log.info("CommonController.getAttachDownload");

    return service.getAttachDownload(id);
  }

  @Secured({ALPHA, BETA, GAMMA, DELTA, USER})
  @GetMapping(uriCommonAttachId)
  public ResponseEntity<?> getAttach(@PathVariable Long id) {
    log.info("CommonController.getAttach");

    return service.getAttach(id);
  }

  @Secured({ALPHA, BETA, GAMMA, DELTA, USER})
  @DeleteMapping(uriCommonAttachId)
  public ResponseEntity<?> removeAttach(@PathVariable Long id) {
    log.info("CommonController.removeAttach");

    return service.deleteAttach(id);
  }

}
