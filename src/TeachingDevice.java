/****************/
// Ma sinh vien: 202418841
// Ho ten: Duong Thi Quynh Anh
/****************/
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class TeachingDevice {
    private String id;
    private String name;
    private DeviceStatus status;

    private List<EventHandler<DeviceStatusChangedEventArgs>> statusChangedHandlers = new ArrayList<>();
    private List<EventHandler<DeviceAlertEventArgs>> alertRaisedHandlers = new ArrayList<>();

    protected TeachingDevice(String id, String name) {
        this.id = id;
        this.name = name;
        this.status = DeviceStatus.Available;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DeviceStatus getStatus() {
        return status;
    }

    public void addStatusChangedHandler(EventHandler<DeviceStatusChangedEventArgs> handler) {
        if (handler != null && !statusChangedHandlers.contains(handler)) {
            statusChangedHandlers.add(handler);
        }
    }

    public void removeStatusChangedHandler(EventHandler<DeviceStatusChangedEventArgs> handler) {
        statusChangedHandlers.remove(handler);
    }

    public void addAlertRaisedHandler(EventHandler<DeviceAlertEventArgs> handler) {
        if (handler != null && !alertRaisedHandlers.contains(handler)) {
            alertRaisedHandlers.add(handler);
        }
    }

    public void removeAlertRaisedHandler(EventHandler<DeviceAlertEventArgs> handler) {
        alertRaisedHandlers.remove(handler);
    }

    public void changeStatus(DeviceStatus newStatus) {
        if (newStatus == this.status) {
            return;
        }

        DeviceStatus oldStatus = this.status;
        this.status = newStatus;

        onStatusChanged(new DeviceStatusChangedEventArgs(this.id, oldStatus, newStatus, LocalDateTime.now()));
    }

    protected void raiseAlert(String message, AlertSeverity severity) {
        onAlertRaised(new DeviceAlertEventArgs(this.id, message, severity, LocalDateTime.now()));
    }

    protected void onStatusChanged(DeviceStatusChangedEventArgs e) {
        for (EventHandler<DeviceStatusChangedEventArgs> handler : statusChangedHandlers) {
            handler.handle(this, e);
        }
    }

    protected void onAlertRaised(DeviceAlertEventArgs e) {
        for (EventHandler<DeviceAlertEventArgs> handler : alertRaisedHandlers) {
            handler.handle(this, e);
        }
    }
}