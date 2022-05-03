package com.example.mbticodingtask.api;

import com.example.mbticodingtask.api.model.IntervalRequest;
import com.example.mbticodingtask.business.IntervalService;
import com.example.mbticodingtask.business.model.Interval;
import com.example.mbticodingtask.conversion.IntervalConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
public class IntervalController {

    private final IntervalService intervalService;
    private final IntervalConverter converter;

    @PostMapping("/interval-merge")
    public ResponseEntity<IntervalRequest> postIntervalMerge(@RequestBody @Valid final IntervalRequest request) {
        final List<Interval> result = intervalService.mergeIntervals(
                converter.convertRequestToIntervalList(request));

        return ResponseEntity.ok(converter.convertIntervalListToRequest(result));
    }
}
