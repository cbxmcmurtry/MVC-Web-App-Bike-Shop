package com.example.demo.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 *
 *
 *
 */
@Entity
@DiscriminatorValue("1")
public class InhousePart extends Part {
    int partId;

    public InhousePart() {
        super();
    }
    public InhousePart(String name, double price, int inv, int minInv, int maxInv) {
        super(name, price, inv, minInv, maxInv);
    }

    public InhousePart(long id, String name, double price, int inv, int minInv, int maxInv) {
        super(id, name, price, inv, minInv, maxInv);
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }
}