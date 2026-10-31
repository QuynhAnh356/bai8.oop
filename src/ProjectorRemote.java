/****************/
// Ma sinh vien: 202418841
// Ho ten: Duong Thi Quynh Anh
/****************/
public class ProjectorRemote extends TeachingDevice {
    private int batteryLevel;

    public ProjectorRemote(String id, String name, int initialBattery) {
        super(id, name);
        if (initialBattery < 0 || initialBattery > 100) {
            throw new InvalidDeviceReadingException(id, initialBattery);
        }
        this.batteryLevel = initialBattery;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void updateBatteryLevel(int newLevel) {
        if (newLevel < 0 || newLevel > 100) {
            throw new InvalidDeviceReadingException(getId(), newLevel);
        }

        if (newLevel == this.batteryLevel) {
            return;
        }

        this.batteryLevel = newLevel;

        if (newLevel == 0) {
            raiseAlert("Pin may chieu da can kiet (0%)", AlertSeverity.Critical);
        } else if (newLevel < 20) {
            raiseAlert("Pin may chieu xuong thap duoi 20%: " + newLevel + "%", AlertSeverity.Warning);
        }
    }
}