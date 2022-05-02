package com.example.mbticodingtask.business.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Interval implements Comparable<Interval>{

    private int start;
    private int end;

    @Override
    public int compareTo(final Interval other) {
        return this.start - other.getStart();
    }
}
