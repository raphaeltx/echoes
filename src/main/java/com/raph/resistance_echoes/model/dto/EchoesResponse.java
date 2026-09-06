package com.raph.resistance_echoes.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * DTO for Echoes response.
 */
@Getter
@Setter
@Builder
public class EchoesResponse {

    /**
     * The unique identifier of the Echoes response.
     */
    private Integer id;

    /**
     * The message of the Echoes response.
     */
    private String message;

    /**
     * The creation timestamp of the Echoes response.
     */
    private LocalDateTime createdAt;

    /**
     * The last update timestamp of the Echoes response.
     */
    private LocalDateTime updatedAt;
}
