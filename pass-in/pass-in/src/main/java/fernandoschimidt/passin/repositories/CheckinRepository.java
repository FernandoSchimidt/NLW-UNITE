package fernandoschimidt.passin.repositories;

import fernandoschimidt.passin.domain.checkin.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckinRepository extends JpaRepository<Checkin, Integer> {
}
