package run.freshr.domain.community.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import run.freshr.common.extension.response.ResponseExtension;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BoardResponse extends ResponseExtension<Long> {

  private String title;
  private String contents;
  private Integer views;

}
