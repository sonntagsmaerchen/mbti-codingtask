package com.example.mbticodingtask.api.validation;

import com.example.mbticodingtask.api.model.IntervalDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * an Interval is valid if it starts before or at the same number as it ends
 */
public class IntervalConstraintValidator implements ConstraintValidator<IntervalConstraint, IntervalDTO> {

    @Override
    public void initialize(final IntervalConstraint constraint) {
    }

    @Override
    public boolean isValid(final IntervalDTO intervalDTO, final ConstraintValidatorContext context) {
        return intervalDTO.getStart() <= intervalDTO.getEnd();
    }
}
