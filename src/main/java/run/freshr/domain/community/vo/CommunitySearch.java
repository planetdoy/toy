package run.freshr.domain.community.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import run.freshr.annotation.SearchClass;
import run.freshr.common.extension.request.SearchExtension;

@Data
@SearchClass
@EqualsAndHashCode(callSuper = true)
public class CommunitySearch extends SearchExtension<Long> {

}
