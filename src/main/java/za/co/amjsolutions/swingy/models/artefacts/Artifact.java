package za.co.amjsolutions.swingy.models.artefacts;

public class Artifact {
    private String type;
    private int points;

    public Artifact(String type, int points) {
        this.type = type;
        this.points = points;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void setPoints(int points) {
        this.points = points;
    }

    public String getType() {
        return type;
    }
    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return type + " (+ " + points + " points)";
    }
}
