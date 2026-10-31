/****************/
// Ma sinh vien: 202418841
// Ho ten: Duong Thi Quynh Anh
/****************/
public class AirConditionerRemote extends TeachingDevice {
    private String assignedRoom;
    private boolean isResponsive;

    public AirConditionerRemote(String id, String name, String assignedRoom) {
        super(id, name);
        this.assignedRoom = assignedRoom;
        this.isResponsive = true;
    }

    public String getAssignedRoom() {
        return assignedRoom;
    }

    public boolean isResponsive() {
        return isResponsive;
    }

    public void updateResponseState(boolean isResponsive) {
        if (this.isResponsive && !isResponsive) {
            this.isResponsive = false;
            raiseAlert("Dieu hoa phong " + this.assignedRoom + " khong phan hoi", AlertSeverity.Critical);
        } else {
            this.isResponsive = isResponsive;
        }
    }
}