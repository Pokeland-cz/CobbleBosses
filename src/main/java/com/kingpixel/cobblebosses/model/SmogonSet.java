package com.kingpixel.cobblebosses.model;

import java.util.List;
import java.util.Map;

public class SmogonSet {
    private String ability;
    private String item;
    private String nature;
    private Map<String, Integer> evs;
    private Map<String, Integer> ivs;
    private List<String> moves;

    public SmogonSet() {}

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public Map<String, Integer> getEvs() {
        return evs;
    }

    public void setEvs(Map<String, Integer> evs) {
        this.evs = evs;
    }

    public Map<String, Integer> getIvs() {
        return ivs;
    }

    public void setIvs(Map<String, Integer> ivs) {
        this.ivs = ivs;
    }

    public List<String> getMoves() {
        return moves;
    }

    public void setMoves(List<String> moves) {
        this.moves = moves;
    }
}
