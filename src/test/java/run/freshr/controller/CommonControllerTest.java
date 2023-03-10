package run.freshr.controller;

import static com.google.common.base.CaseFormat.LOWER_HYPHEN;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParts;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static run.freshr.TestRunner.attachId;
import static run.freshr.common.config.URIConfig.uriCommonAttach;
import static run.freshr.common.config.URIConfig.uriCommonAttachExist;
import static run.freshr.common.config.URIConfig.uriCommonAttachId;
import static run.freshr.common.config.URIConfig.uriCommonAttachIdDownload;
import static run.freshr.common.config.URIConfig.uriCommonEnum;
import static run.freshr.common.config.URIConfig.uriCommonEnumPick;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import run.freshr.annotation.Docs;
import run.freshr.annotation.DocsGroup;
import run.freshr.common.extension.TestExtension;
import run.freshr.domain.auth.enumeration.Privilege;
import run.freshr.domain.common.AttachDocs;
import run.freshr.domain.common.EnumDocs;

@Slf4j
@DocsGroup(name = "common", description = "공통 관리")
class CommonControllerTest extends TestExtension {

  //  _______ .__   __.  __    __  .___  ___.
  // |   ____||  \ |  | |  |  |  | |   \/   |
  // |  |__   |   \|  | |  |  |  | |  \  /  |
  // |   __|  |  . `  | |  |  |  | |  |\/|  |
  // |  |____ |  |\   | |  `--'  | |  |  |  |
  // |_______||__| \__|  \______/  |__|  |__|

  @Test
  @DisplayName("열거형 Data 조회 - All")
  @Docs
  public void getEnumList() throws Exception {
    log.info("CommonControllerTest.getEnumList");

    setAnonymous();

    GET(uriCommonEnum)
        .andDo(print())
        .andDo(docsPopup(EnumDocs.Response.getEnumList(service.getEnumAll())))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("열거형 Data 조회 - One To Many")
  @Docs(existsPathParameters = true)
  public void getEnum() throws Exception {
    log.info("CommonControllerTest.getEnum");

    setAnonymous();

    GET(uriCommonEnumPick,
        UPPER_CAMEL.to(LOWER_HYPHEN, Privilege.class.getSimpleName()).toLowerCase())
        .andDo(print())
        .andDo(docs(pathParameters(EnumDocs.Request.getEnum())))
        .andExpect(status().isOk());
  }

  //      ___   .___________.___________.    ___       ______  __    __
  //     /   \  |           |           |   /   \     /      ||  |  |  |
  //    /  ^  \ `---|  |----`---|  |----`  /  ^  \   |  ,----'|  |__|  |
  //   /  /_\  \    |  |        |  |      /  /_\  \  |  |     |   __   |
  //  /  _____  \   |  |        |  |     /  _____  \ |  `----.|  |  |  |
  // /__/     \__\  |__|        |__|    /__/     \__\ \______||__|  |__|

  @Test
  @DisplayName("파일 업로드")
  @Docs(existsRequestParts = true, existsRequestParameters = true, existsResponseFields = true)
  public void createAttach() throws Exception {
    log.info("CommonControllerTest.createAttach");

    setSignedUser();

    apply();

    POST_MULTIPART(
        uriCommonAttach,
        "temp",
        new MockMultipartFile("files", "test.png", "image/png",
            "FreshR".getBytes())
    ).andDo(print())
        .andDo(docs(
            requestParts(AttachDocs.Request.createAttachFile()),
            requestParameters(AttachDocs.Request.createAttach()),
            responseFields(AttachDocs.Response.createAttach())
        ))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("파일 존재 여부 확인")
  @Docs(existsPathParameters = true, existsResponseFields = true)
  public void existAttach() throws Exception {
    log.info("CommonControllerTest.existAttach");

    setSignedUser();

    apply();

    GET(uriCommonAttachExist, attachId)
        .andDo(print())
        .andDo(docs(
            pathParameters(AttachDocs.Request.existAttach()),
            responseFields(AttachDocs.Response.existAttach())
        ))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("파일 상세 조회")
  @Docs(existsPathParameters = true, existsResponseFields = true)
  public void getAttach() throws Exception {
    log.info("CommonControllerTest.getAttach");

    setSignedUser();

    apply();

    GET(uriCommonAttachId, attachId)
        .andDo(print())
        .andDo(docs(
            pathParameters(AttachDocs.Request.getAttach()),
            responseFields(AttachDocs.Response.getAttach())
        ))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("파일 다운로드")
  @Docs(existsPathParameters = true)
  public void getAttachDownload() throws Exception {
    log.info("CommonControllerTest.getAttachDownload");

    setSignedUser();

    apply();

    GET(uriCommonAttachIdDownload, attachId)
        .andDo(print())
        .andDo(docs(pathParameters(AttachDocs.Request.getAttach())))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("파일 삭제")
  @Docs(existsPathParameters = true)
  public void removeAttach() throws Exception {
    log.info("CommonControllerTest.removeAttach");

    setSignedUser();

    apply();

    DELETE(uriCommonAttachId, attachId)
        .andDo(print())
        .andDo(docs(pathParameters(AttachDocs.Request.removeAttach())))
        .andExpect(status().isOk());
  }

}