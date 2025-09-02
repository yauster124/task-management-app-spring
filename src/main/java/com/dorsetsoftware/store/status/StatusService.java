package com.dorsetsoftware.store.status;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class StatusService {
    private final StatusRepository statusRepository;
    private final StatusMapper statusMapper;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
        this.statusMapper = new StatusMapper();
    }

     public List<StatusDto> getAllStatuses() {
        return statusRepository.findAll()
            .stream()
            .map(statusMapper::toDto)
            .collect(Collectors.toList());
    }
}
