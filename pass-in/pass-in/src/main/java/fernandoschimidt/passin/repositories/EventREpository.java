package fernandoschimidt.passin.repositories;

import fernandoschimidt.passin.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventREpository extends JpaRepository<Event, String> {
}
