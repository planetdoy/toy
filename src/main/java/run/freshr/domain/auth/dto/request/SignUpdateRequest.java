package run.freshr.domain.auth.dto.request;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpdateRequest {

  @NotEmpty
  private String rsa;

  @NotEmpty
  private String name;

}
