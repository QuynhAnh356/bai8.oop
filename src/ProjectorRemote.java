/****************/
// Ma sinh vien: 202418841
// Ho ten: Duong Thi Quynh Anh
/****************/
public class ProjectorRemote extends TeachingDevice {
    private int batteryLevel;

    public ProjectorRemote(String id, String name, int initialBattery) {
        super(id, name);
        this.batteryLevel = initialBattery;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void updateBatteryLevel(int newLevel) {
        this.batteryLevel = newLevel;

        if (newLevel == 0) {
            raiseAlert("Pin may chieu da can kiet (0%)", AlertSeverity.Critical);
        } else if (newLevel < 20) {
            raiseAlert("Pin may chieu xuong thap duoi 20%: " + newLevel + "%", AlertSeverity.Warning);
        }
    }
}