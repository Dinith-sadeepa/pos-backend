package lk.ijse.absd.pos.repository;

public interface LogInRepo {
    boolean checkCredentials(String username, String password);
}
