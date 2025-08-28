package org.example.back.model;

public class Result {
    private Long worksheetId;
    private Boolean isPassed;
    private String completeDatetime;

    public Long getWorksheetId() {
        return worksheetId;
    }

    public void setWorksheetId(Long worksheetId) {
        this.worksheetId = worksheetId;
    }

    public Boolean getPassed() {
        return isPassed;
    }

    public void setPassed(Boolean passed) {
        isPassed = passed;
    }

    public String getCompleteDatetime() {
        return completeDatetime;
    }

    public void setCompleteDatetime(String completeDatetime) {
        this.completeDatetime = completeDatetime;
    }
}
