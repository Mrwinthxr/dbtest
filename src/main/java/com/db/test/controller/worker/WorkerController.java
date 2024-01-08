package com.db.test.controller.worker;

import com.db.test.worker.Worker;
import com.db.test.worker.WorkerService;
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
@RequestMapping(value = "/worker")
public class WorkerController {

    final WorkerService workerService;

    @GetMapping(path = "/create/{name}")
    public ResponseEntity<Worker> createUser(@PathVariable("name") String name) {

        Worker worker = workerService.createWorker(name);
        log.info("New worker created: '{}' and info: '{}'", worker.getId(), worker);

        return new ResponseEntity<>(worker, HttpStatus.OK);
    }
}
