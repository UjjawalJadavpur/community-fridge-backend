package com.fridge.community_fridge_backend.scheduler;

import com.fridge.community_fridge_backend.entity.FridgeItem;
import com.fridge.community_fridge_backend.repository.FridgeItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FridgeItemExpiryScheduler {

    private final FridgeItemRepository fridgeItemRepository;

    // Runs every day at midnight
    @Scheduled(cron = "0 0 0 * * *")
    public void markExpiredItems() {
        List<FridgeItem> items = fridgeItemRepository.findByExpiredFalse();

        LocalDateTime now = LocalDateTime.now();

        for (FridgeItem item : items) {
            if (item.getExpiryDate().isBefore(now)) {
                item.setExpired(true);
                log.info("Expired item: Food '{}', FridgeItem ID {}",
                        item.getFoodItem().getName(), item.getId());
            }
        }

        fridgeItemRepository.saveAll(items); // Batch update
    }
}
