package run.freshr.domain.community;

import static run.freshr.domain.community.entity.QBoard.board;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.request.ParameterDescriptor;
import run.freshr.common.docs.ResponseDocs;
import run.freshr.common.util.PrintUtil;
import run.freshr.domain.community.vo.FCommunitySearch;

@Slf4j
public class BoardDocs {

  public static class Request {
    public static List<FieldDescriptor> createBoard() {
      log.info("BoardDocs.createBoard");

      return PrintUtil
          .builder()

          .prefixDescription("게시글")

          .field(board.title, board.contents)

          .clear()
          .build()
          .getFieldList();
    }

    public static List<ParameterDescriptor> getBoardPage() {
      log.info("BoardDocs.getBoardPage");

      return PrintUtil
          .builder()

          .parameter(FCommunitySearch.page, FCommunitySearch.size)

          .prefixOptional()
          .parameter(FCommunitySearch.startDate, FCommunitySearch.endDate,
              FCommunitySearch.orderType, FCommunitySearch.orderBy)

          .clear()
          .build()
          .getParameterList();
    }

    public static List<ParameterDescriptor> getBoard() {
      log.info("BoardDocs.getBoard");

      return PrintUtil
          .builder()

          .prefixDescription("게시글")

          .parameter(board.id)

          .build()
          .getParameterList();
    }

    public static List<ParameterDescriptor> updateBoardPath() {
      log.info("BoardDocs.updateBoardPath");

      return PrintUtil
          .builder()

          .prefixDescription("게시글")

          .parameter(board.id)

          .build()
          .getParameterList();
    }

    public static List<FieldDescriptor> updateBoard() {
      log.info("BoardDocs.updateBoard");

      return PrintUtil
          .builder()

          .prefixDescription("게시글")

          .field(board.title, board.contents)

          .clear()
          .build()
          .getFieldList();
    }

    public static List<ParameterDescriptor> removeBoard() {
      log.info("BoardDocs.removeBoard");

      return PrintUtil
          .builder()

          .prefixDescription("게시글")

          .parameter(board.id)

          .build()
          .getParameterList();
    }
  }

  public static class Response {
    public static List<FieldDescriptor> createBoard() {
      log.info("BoardDocs.createBoard");

      return ResponseDocs.Response
          .data()

          .prefixDescription("게시글")

          .field(board.id)

          .clear()
          .build()
          .getFieldList();
    }

    public static List<FieldDescriptor> getBoardPage() {
      log.info("BoardDocs.getBoardPage");

      return ResponseDocs.Response
          .page()

          .prefixDescription("게시글")

          .field(board.id, board.title, board.views, board.createAt, board.updateAt)

          .clear()
          .build()
          .getFieldList();
    }

    public static List<FieldDescriptor> getBoard() {
      log.info("BoardDocs.getBoard");

      return ResponseDocs.Response
          .data()

          .prefixDescription("게시글")

          .field(board.id, board.title, board.contents, board.views, board.createAt, board.updateAt)

          .clear()
          .build()
          .getFieldList();
    }
  }

  public static class Docs {
  }

}
