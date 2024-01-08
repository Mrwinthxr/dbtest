package com.db.test.worker;

import com.db.test.common.CommonEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Worker extends CommonEntity {

    @Column
    private String name;

    @Builder(toBuilder = true)
    public Worker(long id, String name) {
        this.id = id;
        this.name = name;
    }

}
