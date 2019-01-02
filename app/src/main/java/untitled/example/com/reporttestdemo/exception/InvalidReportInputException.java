package untitled.example.com.reporttestdemo.exception;

/**
 * Created by Amber_Chen on 2018/10/1.
 */

public class InvalidReportInputException extends Exception {
    private static final long serialVersionUID = 7526472295622776147L;  // unique id
    public InvalidReportInputException(String message) {
        super(message);
    }

    public InvalidReportInputException() {
        super();
    }
}
