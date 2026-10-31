/****************/
// Ma sinh vien: 202418841
// Ho ten: Duong Thi Quynh Anh
/****************/
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DeviceMonitoringCoordinator implements AutoCloseable {
    private List<TeachingDevice> devices;
    private ConsoleNotificationService notificationService;
    private DeviceAuditLogger auditLogger;
    private MaintenanceService maintenanceService;
    private boolean isSubscribed = false;

    private EventHandler<DeviceAlertEventArgs> alertHandler;
    private EventHandler<DeviceStatusChangedEventArgs> statusHandler;
    private EventHandler<DeviceAlertEventArgs> maintenanceHandler;

    public DeviceMonitoringCoordinator(
            Collection<TeachingDevice> devices,
            ConsoleNotificationService notificationService,
            DeviceAuditLogger auditLogger,
            MaintenanceService maintenanceService) {
        this.devices = new ArrayList<>(devices);
        this.notificationService = notificationService;
        this.auditLogger = auditLogger;
        this.maintenanceService = maintenanceService;

        this.alertHandler = this.notificationService::handleAlert;
        this.statusHandler = this.auditLogger::handleStatusChanged;
        this.maintenanceHandler = this.maintenanceService::handleCriticalAlert;
    }

    public void subscribe() {
        if (isSubscribed) {
            return;
        }

        for (TeachingDevice device : devices) {
            device.addAlertRaisedHandler(this.alertHandler);
            device.addStatusChangedHandler(this.statusHandler);
            device.addAlertRaisedHandler(this.maintenanceHandler);
        }

        isSubscribed = true;
    }

    public void unsubscribe() {
        if (!isSubscribed) {
            return;
        }

        for (TeachingDevice device : devices) {
            device.removeAlertRaisedHandler(this.alertHandler);
            device.removeStatusChangedHandler(this.statusHandler);
            device.removeAlertRaisedHandler(this.maintenanceHandler);
        }

        isSubscribed = false;
    }

    public void dispose() {
        unsubscribe();
    }

    @Override
    public void close() {
        dispose();
    }
}