package run.freshr.domain.community.dto.request;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardCreateRequest {

  @NotEmpty
  private String title;
  @NotEmpty
  private String contents;

}
