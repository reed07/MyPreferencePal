package bolts;

public class TaskCompletionSource<TResult> {
    private final Task<TResult> task = new Task<>();
}
