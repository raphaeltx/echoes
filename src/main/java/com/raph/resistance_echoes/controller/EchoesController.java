package com.raph.resistance_echoes.controller;

import com.raph.resistance_echoes.exception.SaveEchoesException;
import com.raph.resistance_echoes.model.dto.ApiResponse;
import com.raph.resistance_echoes.model.dto.SaveEchoesRequest;
import com.raph.resistance_echoes.model.dto.EchoesResponse;
import com.raph.resistance_echoes.model.interfaces.IEchoesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/echoes")
@RequiredArgsConstructor
public class EchoesController {

    private final IEchoesService echoesService;

    @PostMapping
    public ApiResponse<EchoesResponse> saveEchoes(@RequestBody @Valid SaveEchoesRequest saveEchoesRequest) throws SaveEchoesException {
        EchoesResponse savedEchoes = echoesService.saveEchoes(saveEchoesRequest);
        return ApiResponse.<EchoesResponse>builder()
                .success(true)
                .message("Echoes saved successfully")
                .data(savedEchoes)
                .build();
    }

    @GetMapping
    public ApiResponse<List<EchoesResponse>> getEchoes() {
        return ApiResponse.<List<EchoesResponse>>builder()
                .success(true)
                .message("Echoes retrieved successfully")
                .data(echoesService.findEchoes())
                .build();
    }
}
