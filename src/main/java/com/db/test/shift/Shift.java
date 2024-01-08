package com.db.test.shift;

import com.db.test.common.CommonEntity;
import com.db.test.shop.Shop;
import com.db.test.worker.Worker;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@Table
@NoArgsConstructor
public class Shift extends CommonEntity {

    @Column(nullable = false)
    Instant shiftStart;

    @Column(nullable = false)
    Instant shiftEnd;

    @ManyToOne
    @JoinColumn(name="worker_id", nullable = false)
    Worker worker;

    @ManyToOne
    @JoinColumn(name="shop_id", nullable = false)
    Shop shop;

    @Builder(toBuilder = true)
    public Shift(long id, Instant shiftStart, Instant shiftEnd, Worker worker, Shop shop) {
        this.id = id;
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
        this.worker = worker;
        this.shop = shop;
    }

}
