package com.raph.resistance_echoes.service;

import com.raph.resistance_echoes.exception.SaveEchoesException;
import com.raph.resistance_echoes.exception.ScheduleEchoesException;
import com.raph.resistance_echoes.model.dto.SaveEchoesRequest;
import com.raph.resistance_echoes.model.dto.EchoesResponse;
import com.raph.resistance_echoes.model.dto.ScheduleEchoesDto;
import com.raph.resistance_echoes.model.entity.EchoesEntity;
import com.raph.resistance_echoes.model.interfaces.IEchoesService;
import com.raph.resistance_echoes.repository.EchoesRepository;
import com.raph.resistance_echoes.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EchoesService implements IEchoesService {

    private final EchoesRepository echoesRepository;
    private final EchoesSchedulerService echoesSchedulerService;

    @Transactional(rollbackOn = ScheduleEchoesException.class)
    @Override
    public EchoesResponse saveEchoes(SaveEchoesRequest saveEchoesRequest) throws SaveEchoesException {
        try {
            EchoesEntity entity = mapToEntity(saveEchoesRequest);
            EchoesEntity savedEntity = echoesRepository.save(entity);
            echoesSchedulerService.scheduleEchoes(ScheduleEchoesDto.builder()
                    .id(savedEntity.getId())
                    .payload(savedEntity.getMessage())
                    .cronExpression(saveEchoesRequest.getScheduleRecurrenceEnum())
                    .build());

            return mapToDto(savedEntity);
        } catch (Exception e) {
            throw ExceptionUtil.buildAndLogError("save echoes", e, SaveEchoesException::new);
        }
    }

    @Override
    public List<EchoesResponse> findEchoes() {
        return echoesRepository.findAll().stream()
                .map(this::mapToDto).toList();
    }

    /**
     * Maps a SaveEchoesRequest DTO to an EchoesEntity.
     *
     * @param request The SaveEchoesRequest DTO to be mapped
     * @return An EchoesEntity containing the mapped data
     */
    private EchoesEntity mapToEntity(SaveEchoesRequest request) {
        return EchoesEntity.builder()
                .message(request.getMessage())
                .build();
    }

    /**
     * Maps an EchoesEntity to a SaveEchoesResponse DTO.
     *
     * @param entity The EchoesEntity to be mapped
     * @return A SaveEchoesResponse DTO containing the mapped data
     */
    private EchoesResponse mapToDto(EchoesEntity entity) {
        return EchoesResponse.builder()
                .id(entity.getId())
                .message(entity.getMessage())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
