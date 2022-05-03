package com.example.mbticodingtask.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntervalRequest {

    @NotEmpty(message = "Interval List must not be empty")
    private List<@Valid IntervalDTO> intervalList;
}
