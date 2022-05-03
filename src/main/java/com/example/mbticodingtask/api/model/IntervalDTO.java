package com.example.mbticodingtask.api.model;

import com.example.mbticodingtask.api.validation.IntervalConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@IntervalConstraint
public class IntervalDTO {

    private int start;
    private int end;
}
