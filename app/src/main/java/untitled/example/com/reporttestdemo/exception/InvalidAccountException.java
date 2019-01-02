package untitled.example.com.reporttestdemo.exception;

/**
 * Created by Amber_Chen on 2018/10/1.
 */

public class InvalidAccountException extends Exception {
    private static final long serialVersionUID = 7526472295622776147L;  // unique id
    public InvalidAccountException(String message) {
        super(message);
    }

    public InvalidAccountException() {
        super();
    }
}
