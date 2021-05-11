package org.nikita.api.model;

public class ResponseIpAddress {
    private IpAddressData data;
    private ErrorData[] errors;

    public IpAddressData getData() {
        return data;
    }

    public ErrorData[] getErrors() {
        return errors;
    }

    public void setData(IpAddressData data) {
        this.data = data;
    }
}
