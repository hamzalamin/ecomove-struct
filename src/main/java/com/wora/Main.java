package com.wora;

import com.wora.presentation.ContractUi;
import com.wora.presentation.MainMenu;
import com.wora.presentation.PartnerUi;
import com.wora.repositories.ContractRepository;
import com.wora.repositories.PartnerRepository;
import com.wora.services.ContractService;
import com.wora.services.PartnerService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        PartnerRepository PartnerRepository = new PartnerRepository();
        PartnerService partnerService = new PartnerService(PartnerRepository);
        PartnerUi partnerUi = new PartnerUi(partnerService);


        ContractRepository contractRepository = new ContractRepository();
        ContractService contractService = new ContractService(contractRepository);
        ContractUi contractUi = new ContractUi(contractService, partnerService);


        MainMenu mainMenu = new MainMenu(partnerUi, contractUi);
        mainMenu.showMenu();
    }
}