
import java.io.IOException;

class ExamException extends Exception {
    ExamException(String message) {
        super(message);
    }

    ExamException(String message, Throwable cause) {
        super(message, cause);
    }
}

class ExamClosedException extends ExamException {
    ExamClosedException(String msg) {
        super(msg);
    }
}

class NotRegisteredException extends ExamException {
    NotRegisteredException(String msg) {
        super(msg);
    }
}

class SubmissionStorageException extends ExamException {
    SubmissionStorageException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

class Storage {
    void save(String fileName, long sizeKb)
            throws SubmissionStorageException {

        try {
            if (sizeKb > 500)
                throw new IOException("Disk quota exceeded");

            System.out.println("Saved " + fileName);

        } catch (IOException e) {
            throw new SubmissionStorageException(
                "Could not store " + fileName, e);
        }
    }
}

class ExamService {
    Storage storage = new Storage();

    void submit(String file, long size, boolean registered,
                boolean examOpen) throws ExamException {

        if (!examOpen)
            throw new ExamClosedException("Exam window closed");

        if (!registered)
            throw new NotRegisteredException(
                "Student is not registered");

        storage.save(file, size);
    }
}

public class StorageDemo {
    public static void main(String[] args) {

        ExamService service = new ExamService();

        try {
            service.submit("answer01.pdf", 120, true, true);
            service.submit("answer02.pdf", 800, true, true);

        } catch (ExamException e) {
            System.out.println("Error: " + e.getMessage());

            if (e.getCause() != null)
                System.out.println("Cause: " +
                    e.getCause().getMessage());
        }
    }
}
