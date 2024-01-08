package com.db.test.worker.impl;

import com.db.test.worker.Worker;
import com.db.test.worker.WorkerRepository;
import com.db.test.worker.WorkerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Slf4j
@Primary
@RequiredArgsConstructor
@Service("workerservice")
public class WorkerServiceImpl implements WorkerService {

    final WorkerRepository workerRepository;

    @Override
    public Worker createWorker(String name) {
        Worker worker = Worker.builder().name(name).build();
        return workerRepository.saveAndFlush(worker);
    }
}
