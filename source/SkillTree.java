// Part of Escala.
// Written by Tiger Sachse.

package escala;

import escala.*;
import java.util.*;

public class SkillTree {
    private String treeID;
    private HashMap<Integer, Node> nodes;

    public SkillTree(String treeID) {
        this.treeID = treeID;
        nodes = new HashMap<>();
    }

    public void addSkill(Skill skill, HashSet<Integer> children) {
        nodes.put(skill.getID(), new Node(skill, children));
    }

    public void print() {
        for (Map.Entry<Integer, Node> entry : nodes.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}

class Node {
    private Skill skill;
    private int parents;
    private boolean purchased;
    private HashSet<Integer> children;

    public Node(Skill skill, HashSet<Integer> children) {
        this.skill = skill;
        this.children = children;

        parents = 0;
        purchased = false;
    }

    public void incrementParents() {
        parents++;
    }
}
