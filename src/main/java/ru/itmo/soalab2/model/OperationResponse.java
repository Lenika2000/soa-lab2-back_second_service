package ru.itmo.soalab2.model;

public class OperationResponse {
    private Long cityId;
    private String message;

    public OperationResponse(Long cityId, String message) {
        this.cityId = cityId;
        this.message = message;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
