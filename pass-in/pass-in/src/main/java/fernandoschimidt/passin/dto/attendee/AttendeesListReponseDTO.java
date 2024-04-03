package fernandoschimidt.passin.dto.attendee;

import lombok.Getter;

import java.util.List;

public record AttendeesListReponseDTO(List<AttendeeDetails> attendees) {
}
