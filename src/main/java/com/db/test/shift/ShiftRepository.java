package com.db.test.shift;

import com.db.test.worker.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShiftRepository extends JpaRepository<Shift, Long> {

    List<Shift> findShiftsByWorkerOrderByShiftEndDesc(Worker worker);
}
