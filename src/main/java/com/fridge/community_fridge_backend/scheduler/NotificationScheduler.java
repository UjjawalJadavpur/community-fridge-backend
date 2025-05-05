package com.fridge.community_fridge_backend.scheduler;

import com.fridge.community_fridge_backend.entity.FridgeItem;
import com.fridge.community_fridge_backend.repository.FridgeItemRepository;
import com.fridge.community_fridge_backend.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationScheduler {

    private final FridgeItemRepository fridgeItemRepository;
    private final NotificationService notificationService;

    @Scheduled(cron = "0 0 9 * * *") // Daily at 9 AM
    public void sendExpiryNotifications() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusDays(1);

        List<FridgeItem> items = fridgeItemRepository.findAll().stream()
                .filter(item -> !item.isExpired() &&
                        item.getExpiryDate() != null &&
                        (item.getExpiryDate().isBefore(tomorrow) || item.getExpiryDate().isEqual(tomorrow)))
                .collect(Collectors.toList());

        for (FridgeItem item : items) {
            String msg = String.format(
                "⚠️ Food '%s' in Fridge '%s' is expiring on %s.",
                item.getFoodItem().getName(),
                item.getFridge().getName(),
                item.getExpiryDate().toString()
            );

            // TODO: Replace with real contact info
            notificationService.sendEmail("owner@example.com", "Food Expiry Alert", msg);
            notificationService.sendSms("+1234567890", msg);
        }
    }
}
