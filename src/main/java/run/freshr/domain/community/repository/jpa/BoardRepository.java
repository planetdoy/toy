package run.freshr.domain.community.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import run.freshr.domain.community.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {

}
