package bgsoftuni.mobilele.service;

import bgsoftuni.mobilele.model.service.UserLoginServiceModel;

public interface UserService {

    boolean login(UserLoginServiceModel userLoginServiceModel);

    void initializationUser();


}
