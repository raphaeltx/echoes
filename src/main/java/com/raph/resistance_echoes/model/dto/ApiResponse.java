package com.raph.resistance_echoes.model.dto;

import lombok.*;

/**
 * A generic API response wrapper that encapsulates the success status, message, and data of an API response.
 *
 * @param <T> the type of the data included in the response
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    /**
     * Indicates whether the API request was successful.
     */
    private boolean success;

    /**
     * A message providing additional information about the API response.
     */
    private String message;

    /**
     * The data included in the API response.
     */
    private T data;
}
