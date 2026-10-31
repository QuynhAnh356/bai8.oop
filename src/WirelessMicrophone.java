/****************/
// Ma sinh vien: 202418841
// Ho ten: Duong Thi Quynh Anh
/****************/
public class WirelessMicrophone extends TeachingDevice {
    private int batteryLevel;
    private int signalQuality;

    public WirelessMicrophone(String id, String name, int initialBattery, int initialSignalQuality) {
        super(id, name);
        if (initialBattery < 0 || initialBattery > 100) {
            throw new InvalidDeviceReadingException(id, initialBattery);
        }
        if (initialSignalQuality < 0 || initialSignalQuality > 100) {
            throw new InvalidDeviceReadingException(id, initialSignalQuality);
        }
        this.batteryLevel = initialBattery;
        this.signalQuality = initialSignalQuality;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public int getSignalQuality() {
        return signalQuality;
    }

    public void updateBatteryLevel(int newLevel) {
        if (newLevel < 0 || newLevel > 100) {
            throw new InvalidDeviceReadingException(getId(), newLevel);
        }

        this.batteryLevel = newLevel;

        if (newLevel == 0) {
            raiseAlert("Micro can kiet pin (0%)", AlertSeverity.Critical);
        } else if (newLevel < 30) {
            raiseAlert("Pin micro xuong thap duoi 30%: " + newLevel + "%", AlertSeverity.Warning);
        }
    }

    public void updateSignalQuality(int newQuality) {
        if (newQuality < 0 || newQuality > 100) {
            throw new InvalidDeviceReadingException(getId(), newQuality);
        }

        this.signalQuality = newQuality;

        if (newQuality == 0) {
            raiseAlert("Mat hoan toan tin hieu micro (0%)", AlertSeverity.Critical);
        } else if (newQuality < 40) {
            raiseAlert("Tin hieu micro kem duoi 40%: " + newQuality + "%", AlertSeverity.Warning);
        }
    }
}