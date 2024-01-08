package com.db.test.shift.impl;

import com.db.test.shift.Shift;
import com.db.test.shift.ShiftRepository;
import com.db.test.shift.ShiftService;
import com.db.test.shop.Shop;
import com.db.test.shop.ShopRepository;
import com.db.test.worker.Worker;
import com.db.test.worker.WorkerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service("shiftservice")
public class ShiftServiceImpl implements ShiftService {
    final ShiftRepository shiftRepository;
    final ShopRepository shopRepository;
    final WorkerRepository workerRepository;

    @Override
    public Shift createShift(int shopId, int workerId, int hours) {
        Shop shop = shopRepository.findShopById(shopId);
        Worker worker = workerRepository.findById(workerId);
        List<Shift> shifts = shiftRepository.findShiftsByWorkerOrderByShiftEndDesc(worker);

        if (hours > 8) {
            throw new RuntimeException("'"+worker+"' can't work that many hours in a row");
        }

        if (!shifts.isEmpty()) {
            // Worker is already assigned to a shop to work at now
            Shift latestShift = shifts.get(0);
            if (latestShift.getShiftEnd().isAfter(Instant.now())) {
                throw new RuntimeException("Can't add Worker '"+worker+"' to shift now, he's working already!");
            }

            Instant oneDayBack = Instant.now().minus(24, ChronoUnit.HOURS);
            Instant FiveDaysBack = Instant.now().minus(5, ChronoUnit.DAYS);

            List<Shift> latestShiftsAtShop24Hours = shifts.stream()
                .filter(shift -> shift.getShop() == shop)
                .filter(shift -> !shift.getShiftEnd().isBefore(oneDayBack))
            .toList();

            int hoursWorkedAtShop = 0;
            for (int i = 1; i <= latestShiftsAtShop24Hours.size(); i++) {
                // To limit the shifts that started before 24 hours ago
                Instant shiftStarted = shifts.get(i).getShiftStart().isBefore(oneDayBack) ? oneDayBack : shifts.get(i).getShiftStart();
                hoursWorkedAtShop += (int) ChronoUnit.HOURS.between(shiftStarted, shifts.get(i).getShiftEnd());
            }

            // Worker can't work more than 8 hours in the same shop within 24 hours timespan
            if (hoursWorkedAtShop + hours > 8) {
                throw new RuntimeException("Can't add Worker '"+worker+"' to shift for amount of hours");
            }

            List<Shift> latestShiftsAtShop5Days = shifts.stream()
                    .filter(shift -> shift.getShop() == shop)
                    .filter(shift -> !shift.getShiftStart().isBefore(FiveDaysBack))
                    .toList();

            // No need to check if less than 5 shifts in the shop is done
            if (latestShiftsAtShop5Days.size() > 4) {
                //
            }

        }



        Shift shift = Shift.builder()
                .shop(shop)
                .worker(worker)
                .shiftStart(Instant.now()) // could also make it possible to select start date/time
                .shiftEnd(Instant.now().plus(hours, ChronoUnit.HOURS))
                .build();
        log.info("New shift created: '{}'", shift);
        return shiftRepository.saveAndFlush(shift);
    }
}
