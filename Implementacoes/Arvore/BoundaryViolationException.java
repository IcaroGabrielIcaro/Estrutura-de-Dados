package Arvore;

public class BoundaryViolationException extends RuntimeException {
    public BoundaryViolationException (String err) {
        super(err);
    }
}
