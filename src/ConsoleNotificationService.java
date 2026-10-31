/****************/
// Ma sinh vien: 202418841
// Ho ten: Duong Thi Quynh Anh
/****************/
public class ConsoleNotificationService {
    public void handleAlert(Object sender, DeviceAlertEventArgs e) {
        System.out.println("[" + e.getSeverity() + "] " + e.getDeviceId() + ": " + e.getMessage());
    }
}