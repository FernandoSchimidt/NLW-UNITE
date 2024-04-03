package fernandoschimidt.passin.services;

import fernandoschimidt.passin.domain.attendee.Attendee;
import fernandoschimidt.passin.domain.checkin.Checkin;
import fernandoschimidt.passin.domain.checkin.exception.CheckinAlreadyExistsException;
import fernandoschimidt.passin.repositories.CheckinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckInService {

    private final CheckinRepository checkinRepository;

    public void registerCheckIn(Attendee attendee) {
        this.verifyCheckInExists(attendee.getId());
        Checkin newCheckin = new Checkin();
        newCheckin.setAttendee(attendee);
        newCheckin.setCreatedAt(LocalDateTime.now());
        this.checkinRepository.save(newCheckin);
    }

    private void verifyCheckInExists(String attendeeId) {
        Optional<Checkin> isCheckedIn = this.getCheckIn(attendeeId);
        if (isCheckedIn.isPresent()) throw new CheckinAlreadyExistsException("Attendee already checked in");
    }

    public Optional<Checkin> getCheckIn(String attendeeId) {
        return this.checkinRepository.findByAttendeeId(attendeeId);
    }
}
