package fernandoschimidt.passin.dto.event;

import fernandoschimidt.passin.domain.event.Event;
import lombok.Getter;

@Getter
public class EventResponseDTO {
    EventDetailDTO event;

    public EventResponseDTO(Event event, Integer numberOfAteendees) {
        this.event = new EventDetailDTO(event.getId(), event.getTitle(),
                event.getDetails(), event.getSlug(), event.getMaximumAttendees(), numberOfAteendees);
    }
}
