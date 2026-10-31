/****************/
// Ma sinh vien: 202418841
// Ho ten: Duong Thi Quynh Anh
/****************/
@FunctionalInterface
public interface EventHandler<T> {
    void handle(Object sender, T args);
}