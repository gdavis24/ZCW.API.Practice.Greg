package ZCW.Api.repositories;

import ZCW.Api.models.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface WinLossRepo extends JpaRepository<WinLoss, Long> {
}
