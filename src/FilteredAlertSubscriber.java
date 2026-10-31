/****************/
// Ma sinh vien: 202418841
// Ho ten: Duong Thi Quynh Anh
/****************/
import java.util.function.Consumer;

public class FilteredAlertSubscriber {
    private AlertFilter filter;
    private Consumer<DeviceAlertEventArgs> action;

    public FilteredAlertSubscriber(AlertFilter filter, Consumer<DeviceAlertEventArgs> action) {
        this.filter = filter;
        this.action = action;
    }

    public void handleAlert(Object sender, DeviceAlertEventArgs e) {
        if (filter != null && filter.filter(e)) {
            if (action != null) {
                action.accept(e);
            }
        }
    }
}