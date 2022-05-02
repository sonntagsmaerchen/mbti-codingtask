package com.example.mbticodingtask.business;

import com.example.mbticodingtask.business.model.Interval;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IntervalService {

    public List<Interval> mergeIntervals(final List<Interval> inputList) {
        //TODO: If inputList Empty & if Intervals not valid
        final List<Interval> mergedList = new ArrayList<>();
        final List<Interval> sortedList = new ArrayList<>(inputList);
        sortedList.sort(Interval::compareTo);

        Interval currentElement = sortedList.get(0);
        sortedList.remove(currentElement);

        for (final Interval interval : sortedList) {
            if (currentElement.getEnd() >= interval.getStart()) {
                currentElement.setEnd(Math.max(currentElement.getEnd(), interval.getEnd()));
            } else {
                mergedList.add(currentElement);
                currentElement = interval;
            }
        }
        mergedList.add(currentElement);

        return mergedList;
    }
}
