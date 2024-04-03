package fernandoschimidt.passin.controlleers;

import fernandoschimidt.passin.domain.event.Event;
import fernandoschimidt.passin.dto.attendee.AttendeeIdDTO;
import fernandoschimidt.passin.dto.attendee.AttendeeRequestDTO;
import fernandoschimidt.passin.dto.attendee.AttendeesListReponseDTO;
import fernandoschimidt.passin.dto.event.EventIDDTO;
import fernandoschimidt.passin.dto.event.EventRequestDTO;
import fernandoschimidt.passin.dto.event.EventResponseDTO;
import fernandoschimidt.passin.services.AttendeeService;
import fernandoschimidt.passin.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final AttendeeService attendeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id) {
        EventResponseDTO event = eventService.getEventDetail(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<EventIDDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder) {
        EventIDDTO eventIDDTO = eventService.createEvent(body);

        var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIDDTO.eventId()).toUri();

        return ResponseEntity.created(uri).body(eventIDDTO);
    }

    @PostMapping("/{eventId}/attendees")
    public ResponseEntity<AttendeeIdDTO> registerParticipant(@PathVariable String eventId, @RequestBody AttendeeRequestDTO body, UriComponentsBuilder uriComponentsBuilder) {
        AttendeeIdDTO attendeeIdDTO = this.eventService.registerAttendeeOnEvent(eventId, body);

        var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeIdDTO.attendeeId()).toUri();

        return ResponseEntity.created(uri).body(attendeeIdDTO);
    }

    @GetMapping("/attendees/{id}")
    public ResponseEntity<AttendeesListReponseDTO> getEventAttendees(@PathVariable String id) {
        AttendeesListReponseDTO attendeesListReponse = attendeeService.getEventsAttendee(id);
        return ResponseEntity.ok(attendeesListReponse);
    }

}
