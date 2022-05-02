package com.example.mbticodingtask;

import com.example.mbticodingtask.business.IntervalService;
import com.example.mbticodingtask.business.model.Interval;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IntervalServiceTest {

    private IntervalService intervalService;

    @BeforeEach
    public void setUp() {
        intervalService = new IntervalService();
    }

    @Test
    public void mergeIntervals_correctSeveralMerges() {
        final List<Interval> result = intervalService.mergeIntervals(createTestData());

        Assertions.assertThat(result).usingRecursiveComparison()
                  .isEqualTo(createExpectedResult());
    }

    @Test
    public void mergeIntervals_correctNoMerges() {
        final List<Interval> result = intervalService.mergeIntervals(createTestDataNoMerge());

        Assertions.assertThat(result).usingRecursiveComparison()
                  .isEqualTo(creatExpectedResultNoMerge());
    }

    private List<Interval> createTestData() {
        return List.of(new Interval(9, 12), new Interval(15, 20), new Interval(1, 3),
                new Interval(2, 7), new Interval(16, 18));
    }

    private List<Interval> createTestDataNoMerge() {
        return List.of(new Interval(9, 12), new Interval(15, 20), new Interval(1, 3),
                new Interval(4, 7), new Interval(21, 23));
    }

    private List<Interval> createExpectedResult() {
        return List.of(new Interval(1, 7), new Interval(9, 12), new Interval(15, 20));
    }

    private List<Interval> creatExpectedResultNoMerge() {
        return List.of(new Interval(1, 3), new Interval(4, 7), new Interval(9, 12),
                new Interval(15, 20), new Interval(21, 23));
    }
}
