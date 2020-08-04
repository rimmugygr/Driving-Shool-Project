package driving.school.mapper;

import driving.school.dto.AvailableDateDto;
import driving.school.model.AvailableDate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AvailableMapper {
    AvailableDate map(AvailableDateDto availableDateDto);
    AvailableDateDto map(AvailableDate availableDate);
}
