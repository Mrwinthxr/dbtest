package com.db.test.shop;

import com.db.test.common.CommonEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Shop extends CommonEntity {

    @Column
    private String name;

    @Column
    private String workers;

    @Builder(toBuilder = true)
    public Shop(long id, String name, String workers) {
        this.id = id;
        this.name = name;
        this.workers = workers;
    }

    public List<String> workersAtShop() {
        if (this.workers == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(this.workers.split(","));
    }
}
