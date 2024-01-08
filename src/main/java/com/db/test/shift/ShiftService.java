package com.db.test.shift;

import java.time.Instant;

public interface ShiftService {

    Shift createShift(int shopId, int workerId, int hours);
}
