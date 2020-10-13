package driving.school.services;

import driving.school.dto.AvailableDateDto;
import driving.school.mapper.AvailableMapper;
import driving.school.model.AvailableDate;
import driving.school.repository.AvailableDateRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AvailableService {
    private final AvailableDateRepo availableDateRepo;
    private final AvailableMapper availableMapper;

    public AvailableDateDto addAvailableDate(AvailableDateDto availableDateDto) {
        AvailableDate availableDate = availableDateRepo.save(availableMapper.map(availableDateDto));
        return availableMapper.map(availableDate);
    }

    public List<AvailableDateDto> addAvailableDate(List<AvailableDateDto> availableDateDtos) {
        List<AvailableDate> availableDate = availableDateDtos.stream()
                .map(availableMapper::map)
                .collect(Collectors.toList());
        availableDateRepo.saveAll(availableDate);
        return availableDate.stream()
                .map(availableMapper::map)
                .collect(Collectors.toList());
    }

    public AvailableDateDto deleteAvailableDateByTeacher(long availableId) {
        AvailableDate availableDate = availableDateRepo.getOne(availableId);
        availableDateRepo.deleteById(availableId);
        return availableMapper.map(availableDate);
    }

    public List<AvailableDateDto> deleteAvailableDateByTeacher(List<Long> availableId) {
        List<AvailableDate> availableDates = availableId.stream()
                .map(availableDateRepo::getOne)
                .collect(Collectors.toList());
        availableId.forEach(availableDateRepo::deleteById);
        return availableDates.stream()
                .map(availableMapper::map)
                .collect(Collectors.toList());
    }

    public List<AvailableDateDto> deleteAllAvailableDateByTeacherId(long teacherId) {
        List<AvailableDate> availableDates = availableDateRepo.findAllByTeacherId(teacherId);
        availableDateRepo.deleteAvailableDatesByTeacherId(teacherId);
        return availableDates.stream()
                .map(availableMapper::map)
                .collect(Collectors.toList());
    }

    public List<AvailableDateDto> getAllAvailableDateByTeacher(long teacherId) {
        return availableDateRepo.findAllByTeacherIdAndReservedIsFalse(teacherId).stream()
                .map(availableMapper::map)
                .collect(Collectors.toList());
    }

    public List<AvailableDateDto> getAllAvailableDate() {
        return availableDateRepo.findAllByReservedIsFalse().stream()
                .map(availableMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteOldAvailableDate() {
        availableDateRepo.deleteAllByDateIsBefore(LocalDateTime.now());
    }
}
