package driving.school.controller;

import driving.school.services.AvailableService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AutoController {
    AvailableService availableService;

    public AutoController(AvailableService availableService) {
        this.availableService = availableService;
    }

    //@hourly
    @Scheduled(cron="0 0 * ? * *")
    @PatchMapping("/data/old/clear")
    private void deleteOldDataAutomatically() {
        availableService.deleteOldAvailableDate();
    }

}
