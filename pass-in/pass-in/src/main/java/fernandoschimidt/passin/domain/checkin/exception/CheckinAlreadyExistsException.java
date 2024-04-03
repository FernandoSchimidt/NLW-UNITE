package fernandoschimidt.passin.domain.checkin.exception;

public class CheckinAlreadyExistsException extends RuntimeException {
    public CheckinAlreadyExistsException(String message) {
        super(message);
    }
}
