package driving.school.controller;

import driving.school.services.AvailableService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AutoController {
    private final AvailableService availableService;

    //@hourly
    @Scheduled(cron="0 0 * ? * *")
    @PatchMapping("/data/old/clear")
    private void deleteOldDataAutomatically() {
        availableService.deleteOldAvailableDate();
    }

}
