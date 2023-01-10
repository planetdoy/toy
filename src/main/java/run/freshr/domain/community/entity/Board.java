package run.freshr.domain.community.entity;

import static lombok.AccessLevel.PROTECTED;
import static run.freshr.common.config.DefaultColumnConfig.ZERO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import run.freshr.common.annotation.TableComment;
import run.freshr.common.extension.entity.EntityLogicalExtension;
import run.freshr.utils.XssUtil;

@Slf4j
@Entity
@Table(
    name = "TB_COMMUNITY_BOARD",
    indexes = {
        @Index(name = "IDX_COMMUNITY_BOARD_FLAG", columnList = "useFlag, deleteFlag")
    }
)
@SequenceGenerator(
    name = "SEQUENCE_GENERATOR",
    sequenceName = "SEQ_COMMUNITY_BOARD"
)
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = PROTECTED)
@TableComment(value = "커뮤니티 관리 > 게시글 관리")
public class Board extends EntityLogicalExtension {

  @Column(nullable = false, length = 50)
  @Comment("제목")
  private String title;

  @Lob
  @Column(nullable = false)
  @Comment("내용")
  private String contents;

  @ColumnDefault(ZERO)
  @Comment("조회수")
  private Integer views;

  private Board(String title, String contents) {
    this.title = title;
    this.contents = XssUtil.xssBasicIgnoreImg(contents);
  }

  public static Board createEntity(String title, String contents) {
    return new Board(title, contents);
  }

  public void updateEntity(String title, String contents) {
    this.title = title;
    this.contents = XssUtil.xssBasicIgnoreImg(contents);
  }

  public void addViews() {
    this.views++;
  }

  public void removeEntity() {
    remove();
  }

}
