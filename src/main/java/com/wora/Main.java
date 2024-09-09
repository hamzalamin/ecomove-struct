package com.wora;

import com.wora.presentation.*;
import com.wora.repositories.ContractRepository;
import com.wora.repositories.IUserRepository;
import com.wora.repositories.PartnerRepository;
import com.wora.repositories.UserRepository;
import com.wora.services.ContractService;
import com.wora.services.IUserService;
import com.wora.services.PartnerService;
import com.wora.services.UserService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        PartnerRepository PartnerRepository = new PartnerRepository();
        PartnerService partnerService = new PartnerService(PartnerRepository);
        PartnerUi partnerUi = new PartnerUi(partnerService);


        ContractRepository contractRepository = new ContractRepository();
        ContractService contractService = new ContractService(contractRepository);
        ContractUi contractUi = new ContractUi(contractService, partnerService);

        IUserRepository userRepository = new UserRepository();
        IUserService userService = new UserService(userRepository);

        MainMenu mainMenu = new MainMenu(partnerUi, contractUi);
        UserUi userUi = new UserUi(userService, mainMenu);

        AuthUi authMenu = new AuthUi(userUi);
        authMenu.AuthMenu();
        userUi.login();
    }
}