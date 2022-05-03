package com.example.mbticodingtask.business.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Interval implements Comparable<Interval> {

    private int start;
    private int end;

    /**
     * compares the intervals by starting number only
     *
     * @param other the interval to be compared.
     */
    @Override
    public int compareTo(final Interval other) {
        return this.start - other.getStart();
    }
}
