package org.example.back.model;

public class Result {
    private Long worksheetId;
    private Integer passQty;
    private Integer failQty;
    private String completeDatetime;

    public Long getWorksheetId() {
        return worksheetId;
    }

    public void setWorksheetId(Long worksheetId) {
        this.worksheetId = worksheetId;
    }

    public String getCompleteDatetime() {
        return completeDatetime;
    }

    public void setCompleteDatetime(String completeDatetime) {
        this.completeDatetime = completeDatetime;
    }

    public Integer getFailQty() {
        return failQty;
    }

    public void setFailQty(Integer failQty) {
        this.failQty = failQty;
    }

    public Integer getPassQty() {
        return passQty;
    }

    public void setPassQty(Integer passQty) {
        this.passQty = passQty;
    }
}
