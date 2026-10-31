/****************/
// Ma sinh vien: 202418841
// Ho ten: Duong Thi Quynh Anh
/****************/
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProjectorRemote projector1 = new ProjectorRemote("PRJ-101", "Dieu khien may chieu P101", 85);
        ProjectorRemote projector2 = new ProjectorRemote("PRJ-202", "Dieu khien may chieu P202", 45);

        AirConditionerRemote ac1 = new AirConditionerRemote("AC-101", "Dieu khien dieu hoa P101", "Phong 101");
        AirConditionerRemote ac2 = new AirConditionerRemote("AC-202", "Dieu khien dieu hoa P202", "Phong 202");

        WirelessMicrophone mic1 = new WirelessMicrophone("MIC-101", "Micro giang duong P101", 90, 80);
        WirelessMicrophone mic2 = new WirelessMicrophone("MIC-HTA", "Micro hoi truong A", 15, 50);

        ConsoleNotificationService notifService = new ConsoleNotificationService();
        DeviceAuditLogger auditLogger = new DeviceAuditLogger();
        MaintenanceService maintenanceService = new MaintenanceService();

        List<TeachingDevice> devices = Arrays.asList(projector1, projector2, ac1, ac2, mic1, mic2);

        DeviceMonitoringCoordinator coordinator = new DeviceMonitoringCoordinator(devices, notifService, auditLogger, maintenanceService);
        coordinator.subscribe();

        System.out.println("1. Kiem thu thay doi trang thai cac thiet bi:");
        projector1.changeStatus(DeviceStatus.Borrowed);
        projector2.changeStatus(DeviceStatus.Borrowed);
        ac1.changeStatus(DeviceStatus.UnderMaintenance);
        ac2.changeStatus(DeviceStatus.Borrowed);
        mic1.changeStatus(DeviceStatus.Borrowed);
        mic2.changeStatus(DeviceStatus.UnderMaintenance);

        System.out.println("\nNhat ky DeviceAuditLogger:");
        for (String log : auditLogger.getEntries()) {
            System.out.println(log);
        }

        System.out.println("\n2. Kiem thu phat canh bao cac thiet bi:");
        projector1.updateBatteryLevel(18);
        projector2.updateBatteryLevel(0);
        ac1.updateResponseState(false);
        mic1.updateSignalQuality(30);
        mic2.updateBatteryLevel(0);

        System.out.println("\nYeu cau bao tri (MaintenanceService):");
        for (String req : maintenanceService.getRequests()) {
            System.out.println(req);
        }

        System.out.println("\n3. Kiem thu bo loc canh bao (FilteredAlertSubscriber):");
        FilteredAlertSubscriber criticalSubscriber = new FilteredAlertSubscriber(
                alert -> alert.getSeverity() == AlertSeverity.Critical,
                alert -> System.out.println("Filtered: [" + alert.getDeviceId() + "] " + alert.getMessage())
        );
        ac2.addAlertRaisedHandler(criticalSubscriber::handleAlert);
        ac2.updateResponseState(false);

        System.out.println("\n4. Kiem thu huy dang ky (Unsubscribe):");
        coordinator.unsubscribe();
        mic1.updateBatteryLevel(10);

        System.out.println("\n5. Kiem thu ngoai le gia tri doc khong hop le:");
        try {
            projector1.updateBatteryLevel(150);
        } catch (InvalidDeviceReadingException ex) {
            System.out.println("Loi: " + ex.getMessage());
        }

        try {
            mic2.updateSignalQuality(-15);
        } catch (InvalidDeviceReadingException ex) {
            System.out.println("Loi: " + ex.getMessage());
        }
    }
}