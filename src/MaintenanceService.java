/****************/
// Ma sinh vien: 202418841
// Ho ten: Duong Thi Quynh Anh
/****************/
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaintenanceService {
    private List<String> requests = new ArrayList<>();

    public List<String> getRequests() {
        return Collections.unmodifiableList(requests);
    }

    public void handleCriticalAlert(Object sender, DeviceAlertEventArgs e) {
        if (e.getSeverity() != AlertSeverity.Critical) {
            return;
        }
        requests.add("Maintenance request for " + e.getDeviceId() + ": " + e.getMessage());
    }
}