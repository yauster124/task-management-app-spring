package com.dorsetsoftware.store.status;

public class StatusMapper {
    public StatusDto toDto(Status status) {
        if (status == null)
            return null;

        return new StatusDto(
                status.getId(),
                status.getTitle());
    }

    public Status toEntity(StatusDto dto) {
        if (dto == null)
            return null;

        return new Status(
                dto.getTitle());
    }
}
