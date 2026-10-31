/****************/
// Ma sinh vien: 202418841
// Ho ten: Duong Thi Quynh Anh
/****************/
public class InvalidDeviceReadingException extends RuntimeException {
    private String deviceId;
    private int invalidValue;

    public InvalidDeviceReadingException(String deviceId, int invalidValue) {
        super("Gia tri doc khong hop le cho thiet bi " + deviceId + ": " + invalidValue);
        this.deviceId = deviceId;
        this.invalidValue = invalidValue;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public int getInvalidValue() {
        return invalidValue;
    }
}