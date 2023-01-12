public class Clap {

    private final double zAcceleration;
    private final long time;



    public Clap(double zAcceleration, long time) {
        this.zAcceleration = zAcceleration;
        this.time = time;
    }

    public double getzAcceleration() {
        return zAcceleration;
    }

    public long getTime() {
        return time;
    }
}
