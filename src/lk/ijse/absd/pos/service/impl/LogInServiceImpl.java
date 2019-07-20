package lk.ijse.absd.pos.service.impl;

import lk.ijse.absd.pos.repository.LogInRepo;
import lk.ijse.absd.pos.repository.impl.LogInRepoImpl;
import lk.ijse.absd.pos.service.LogInService;

public class LogInServiceImpl implements LogInService {
    private LogInRepo logInRepo;

    public LogInServiceImpl() {
        if (logInRepo == null) {
            logInRepo = new LogInRepoImpl();
        }
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        return logInRepo.checkCredentials(username, password);
    }
}
