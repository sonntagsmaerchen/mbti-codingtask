package com.example.mbticodingtask.conversion;

import com.example.mbticodingtask.api.model.IntervalDTO;
import com.example.mbticodingtask.api.model.IntervalRequest;
import com.example.mbticodingtask.business.model.Interval;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * converts API to business objects and vice versa
 * TODO: use built-in Spring Conversion Service or MapStruct if more functionality is needed
 */
@Component
public class IntervalConverter {

    public List<Interval> convertRequestToIntervalList(final IntervalRequest request) {
        return request.getIntervalList()
                      .stream()
                      .map(this::convertIntervalDTOToBusinessInterval)
                      .collect(Collectors.toList());
    }

    public IntervalRequest convertIntervalListToRequest(final List<Interval> list) {
        return new IntervalRequest(list.stream()
                                       .map(this::convertBusinessIntervalToDTO)
                                       .collect(Collectors.toList()));
    }

    private Interval convertIntervalDTOToBusinessInterval(final IntervalDTO dto) {
        return new Interval(dto.getStart(), dto.getEnd());
    }

    private IntervalDTO convertBusinessIntervalToDTO(final Interval interval) {
        return new IntervalDTO(interval.getStart(), interval.getEnd());
    }
}
