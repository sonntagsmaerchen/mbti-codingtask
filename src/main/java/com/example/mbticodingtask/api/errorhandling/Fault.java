package com.example.mbticodingtask.api.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Fault objects should be passed as response body in handled 4xx and 5xx responses
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fault {

    private String messages;
    private List<String> fields;
}
