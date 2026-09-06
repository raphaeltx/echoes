package com.raph.resistance_echoes.model.interfaces;

import com.raph.resistance_echoes.exception.SaveEchoesException;
import com.raph.resistance_echoes.model.dto.SaveEchoesRequest;
import com.raph.resistance_echoes.model.dto.EchoesResponse;

import java.util.List;

/**
 * Interface for EchoesService.
 */
public interface IEchoesService {

    /**
     * Saves the echoes message to the database.
     * @param saveEchoesRequest The SaveEchoesRequest DTO containing the message to be saved
     * @return SaveEchoesResponse DTO with the saved message
     * @throws SaveEchoesException if the echo cannot be saved
     */
    EchoesResponse saveEchoes(SaveEchoesRequest saveEchoesRequest) throws SaveEchoesException;

    /**
     * Retrieves all echoes messages from the database.
     * @return List of EchoesResponse DTOs containing all saved messages
     */
    List<EchoesResponse> findEchoes();
}
