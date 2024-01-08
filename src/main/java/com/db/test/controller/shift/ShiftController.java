package com.db.test.controller.shift;

import com.db.test.shift.Shift;
import com.db.test.shift.ShiftService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/shift")
public class ShiftController {

    final ShiftService shiftService;

    @GetMapping(path= "/create/{shop}/{worker}/{hours}")
    public ResponseEntity<Shift> createShift(@PathVariable("shop") int shopId,
                                             @PathVariable("worker") int workerId,
                                             @PathVariable("hours") int hours) {
        Shift shift = shiftService.createShift(shopId, workerId, hours);

        return new ResponseEntity<>(shift, HttpStatus.OK);
    }
}
