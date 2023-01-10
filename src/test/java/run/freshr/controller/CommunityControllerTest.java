package run.freshr.controller;

import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static run.freshr.TestRunner.boardIdList;
import static run.freshr.common.config.URIConfig.uriCommunityBoard;
import static run.freshr.common.config.URIConfig.uriCommunityBoardId;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import run.freshr.annotation.Docs;
import run.freshr.annotation.DocsGroup;
import run.freshr.common.extension.TestExtension;
import run.freshr.domain.community.BoardDocs;
import run.freshr.domain.community.dto.request.BoardCreateRequest;
import run.freshr.domain.community.dto.request.BoardUpdateRequest;
import run.freshr.domain.community.vo.CommunitySearch;

@Slf4j
@DocsGroup(name = "auth", description = "권한 관리")
class CommunityControllerTest extends TestExtension {

  @Test
  @DisplayName("게시글 등록")
  @Docs()
  public void createBoard() throws Exception {
    setAnonymous();

    apply();

    POST_BODY(uriCommunityBoard,
        BoardCreateRequest
            .builder()
            .title("input title")
            .contents("input contents")
            .build())
        .andDo(print())
        .andDo(docs(requestFields(BoardDocs.Request.createBoard()),
            responseFields(BoardDocs.Response.createBoard())))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("게시글 조회 - Page")
  @Docs()
  public void getBoardPage() throws Exception {
    setAnonymous();

    apply();

    CommunitySearch search = new CommunitySearch();

    search.setPage(2);
    search.setSize(5);

    GET_PARAM(uriCommunityBoard, search)
        .andDo(print())
        .andDo(docs(
            requestParameters(BoardDocs.Request.getBoardPage()),
            responseFields(BoardDocs.Response.getBoardPage())
        ))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("게시글 조회")
  @Docs()
  public void getBoard() throws Exception {
    setAnonymous();

    apply();

    GET(uriCommunityBoardId, boardIdList.get(0))
        .andDo(print())
        .andDo(docs(
            pathParameters(BoardDocs.Request.getBoard()),
            responseFields(BoardDocs.Response.getBoard())
        ))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("게시글 수정")
  @Docs()
  public void updateBoard() throws Exception {
    setAnonymous();

    apply();

    PUT_BODY(uriCommunityBoardId,
        BoardUpdateRequest
            .builder()
            .title("input title")
            .contents("input contents")
            .build(),
        boardIdList.get(0))
        .andDo(print())
        .andDo(docs(pathParameters(BoardDocs.Request.updateBoardPath()),
            requestFields(BoardDocs.Request.updateBoard())))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("게시글 삭제")
  @Docs()
  public void removeBoard() throws Exception {
    setAnonymous();

    apply();

    DELETE(uriCommunityBoardId, boardIdList.get(0))
        .andDo(print())
        .andDo(docs(pathParameters(BoardDocs.Request.removeBoard())))
        .andExpect(status().isOk());
  }
}