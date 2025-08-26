package org.example.back.model;

import org.example.back.model.Machine;
import org.example.back.model.Product;
import org.example.back.model.Worker;

public class Worksheet {
    private Long worksheetId;
    private int amount;
    private Long productId;
    private Long machineId;
    private Long workerId;
    private String createDatetime;

    public Long getWorksheetId() {
        return worksheetId;
    }

    public void setWorksheetId(Long worksheetId) {
        this.worksheetId = worksheetId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
