public class PrioritizedBrackets {
    private int opening, closure;

    public PrioritizedBrackets(int opening, int closure){
        this.opening = opening;
        this.closure = closure;
    }

    public int getOpening() {
        return opening;
    }

    public void setOpening(int opening) {
        this.opening = opening;
    }

    public int getClosure() {
        return closure;
    }

    public void setClosure(int closure) {
        this.closure = closure;
    }
}
