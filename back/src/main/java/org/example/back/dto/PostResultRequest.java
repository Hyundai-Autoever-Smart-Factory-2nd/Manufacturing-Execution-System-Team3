package org.example.back.dto;

public class PostResultRequest {
    private Long worksheetId;
    private Boolean isPassed;

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
}
