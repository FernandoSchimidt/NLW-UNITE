package fernandoschimidt.passin.services;

import fernandoschimidt.passin.domain.attendee.Attendee;
import fernandoschimidt.passin.domain.checkin.Checkin;
import fernandoschimidt.passin.dto.attendee.AttendeeDetails;
import fernandoschimidt.passin.dto.attendee.AttendeesListReponseDTO;
import fernandoschimidt.passin.repositories.AttendeeRepository;
import fernandoschimidt.passin.repositories.CheckinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private final AttendeeRepository attendeeRepository;
    private final CheckinRepository checkinRepository;

    public List<Attendee> getAllAttendeesFromEvent(String eventId) {
        return attendeeRepository.findByEventId(eventId);
    }

    public AttendeesListReponseDTO getEventsAttendee(String eventId) {
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);

        List<AttendeeDetails> attendeeDetailsList = attendeeList.stream().map(attendee -> {
            Optional<Checkin> checkIn = this.checkinRepository.findByAttendeeId(attendee.getId());
            LocalDateTime checkedInAt = checkIn.<LocalDateTime>map(Checkin::getCreatedAt).orElse(null);
            return  new AttendeeDetails(attendee.getId(),attendee.getName(),attendee.getEmail(),attendee.getCreatedAt(),checkedInAt);
        }).toList();

        return  new AttendeesListReponseDTO(attendeeDetailsList);
    }
}
