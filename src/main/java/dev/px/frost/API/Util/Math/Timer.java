package dev.px.frost.API.Util.Math;

public class Timer {

    private long time;

    public Timer() {
        this.time = -1L;
    }

    public boolean passedS(double seconds) {
        return this.getMs(System.nanoTime() - this.time) >= (long) (seconds * 1000.0);
    }

    public boolean passedM(double miliSeconds) {
        return this.getMs(System.nanoTime() - this.time) >= (long) (miliSeconds * 1000.0 * 60.0);
    }

    public boolean passedDms(double dms) {
        return this.getMs(System.nanoTime() - this.time) >= (long) (dms * 10.0);
    }

    public boolean passedDs(double ds) {
        return this.getMs(System.nanoTime() - this.time) >= (long) (ds * 100.0);
    }

    public boolean passedMs(long ms) {
        return this.getMs(System.nanoTime() - this.time) >= ms;
    }

    public boolean passedNS(long ns) {
        return System.nanoTime() - this.time >= ns;
    }

    public void setMs(long ms) {
        this.time = System.nanoTime() - ms * 1000000L;
    }

    public long getMs(long time) {
        return time / 1000000L;
    }

    public long getPassedTimeMs() {
        return this.getMs(System.nanoTime() - this.time);
    }

    public void reset() {
        this.time = System.nanoTime();
    }

    public boolean passed(final long delay, final boolean reset) {
        if (reset) this.reset();
        return System.currentTimeMillis() - this.time >= delay;
    }

    public long getTimeMs() {
        return getMs(System.nanoTime() - this.time);
    }

    public long getTime() {
        return System.nanoTime() - this.time;
    }

    public void increaseTime(int value) {
        time += value;
    }

    public boolean passed(long delay) {
        return this.getMs(System.nanoTime() - this.time) >= delay;
    }
}
