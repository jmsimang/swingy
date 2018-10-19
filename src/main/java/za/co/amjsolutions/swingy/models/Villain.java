package za.co.amjsolutions.swingy.models;

import lombok.Getter;
import za.co.amjsolutions.swingy.models.artefacts.Artifact;

@Getter
public class Villain extends Character {
    private Artifact artifact;

    public Villain(String name, int attack, int defense, int hp, Artifact artifact) {
        super(name, attack, defense, hp);
        this.artifact = artifact;
    }

    public void setArtifact(Artifact artifact) { this.artifact = artifact; }
    public Artifact getArtifact() { return artifact; }
}
