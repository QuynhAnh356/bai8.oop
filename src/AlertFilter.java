/****************/
// Ma sinh vien: 202418841
// Ho ten: Duong Thi Quynh Anh
/****************/
@FunctionalInterface
public interface AlertFilter {
    boolean filter(DeviceAlertEventArgs alert);
}