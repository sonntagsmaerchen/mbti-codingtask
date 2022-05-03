package com.example.mbticodingtask.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntervalRequest {

    @NotNull
    @NotEmpty
    private List<@Valid IntervalDTO> intervalList;
}
