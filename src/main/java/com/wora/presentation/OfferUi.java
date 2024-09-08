package com.wora.presentation;

import com.wora.models.dtos.CreateOfferDto;
import com.wora.models.entities.Offer;
import com.wora.models.enums.OfferStatus;
import com.wora.models.enums.ReductionType;
import com.wora.services.ContractService;
import com.wora.services.OfferService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static com.wora.utli.InputScanner.scanDate;

public class OfferUi {
    private final OfferService service;
    private final ContractService contractService;
    private final Scanner scanner = new Scanner(System.in);

    public OfferUi(OfferService service, ContractService contractService) {
        this.service = service;
        this.contractService = contractService;
    }

    public void creat() {

        try {
            System.out.println("Enter the Offre name:");
            String offreName = scanner.nextLine();

            System.out.println("Enter the Description:");
            String description = scanner.nextLine();

            java.util.Date startDate = scanDate("Enter start date (YYYY-MM-DD):");
            java.util.Date endDate = scanDate("Enter end date (YYYY-MM-DD):");

            System.out.println("Enter the discount Value");
            Double discountValue = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Enter the conditions");
            String conditions = scanner.nextLine();

            System.out.print("Enter Reduction Type (PERCENTAGE, FIXED_AMOUNT): ");
            ReductionType reductionType = ReductionType.valueOf(scanner.nextLine().toUpperCase());

            System.out.println("enter the Offre States(ACTIVE, INACTIVE, SUSPENDED): ");
            OfferStatus offerStatus = OfferStatus.valueOf(scanner.nextLine().toUpperCase());


            System.out.println("Enter the Id of the Contract: ");
            UUID ContractId = UUID.fromString(scanner.nextLine().toString());

            CreateOfferDto dto = new CreateOfferDto(
                    offreName,
                    description,
                    startDate,
                    endDate,
                    discountValue,
                    conditions,
                    reductionType,
                    offerStatus,
                    ContractId
            );
            service.create(dto);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
        try {
            List<Offer> offers = service.findAll();
            if (offers.isEmpty()){
                System.out.println("no offers found");
                return;
            }
            for (int offer = 0 ; offer < offers.size(); offer++){
                System.out.println((offer + 1) + " . " + offers.get(offer).getOfferName() + " (ID: "  + offers.get(offer).getId() + ")");
            }
            System.out.println("Enter the number you want to update : ");
            int index = scanner.nextInt();

            if (index < 1 || index > offers.size()){
                System.out.println("Invalid selection.");
                return;
            }
            Offer existOffer = offers.get(index - 1);
            scanner.nextLine();
            System.out.println("Enter new Offer Name (or press Enter to keep it the same):");
            String offerName = scanner.nextLine();
            if (!offerName.isEmpty()){
                existOffer.setOfferName(offerName);
            }

            scanner.nextLine();
            System.out.println("Enter new Offer Description (or press Enter to keep it the same):");
            String description = scanner.nextLine();
            if (!description.isEmpty()){
                existOffer.setDescription(description);
            }

            Date startDate = scanDate("Enter new Start Date (yyyy-MM-dd, or press Enter to keep it the same): ");
            existOffer.setStartDate(startDate != null ? startDate : new Date(System.currentTimeMillis()));

            Date endtDate = scanDate("Enter new End Date (yyyy-MM-dd, or press Enter to keep it the same): ");
            existOffer.setEndDate(endtDate != null ? endtDate : new Date(System.currentTimeMillis()));

            scanner.nextLine();
            System.out.println("Enter new Offer discount Value (or press Enter to keep it the same):");
            Double discountValue = scanner.nextDouble();
            if (discountValue != null){
                existOffer.setDiscountValue(discountValue);
            }

            scanner.nextLine();
            System.out.println("Enter new Offer conditions (or press Enter to keep it the same):");
            String condition = scanner.nextLine();
            if (!condition.isEmpty()){
                existOffer.setCondition(condition);
            }


            scanner.nextLine();
            System.out.print("Enter new Offer Status ( ACTIVE, EXPIRED, SUSPENDED) (or press Enter to keep it the same): ");
            OfferStatus offerStatus = OfferStatus.valueOf(scanner.nextLine());
            if (offerStatus != null) {
                existOffer.setOfferStatus(offerStatus);
            }

            scanner.nextLine();
            System.out.print("Enter new Reduction Type (PERCENTAGE, FIXED_AMOUNT)(or press Enter to keep it the same): ");
            ReductionType reductionType = ReductionType.valueOf(scanner.nextLine());
            if (reductionType != null) {
                existOffer.setReductionType(reductionType);
            }


            System.out.println("Enter the Id of the Contract: ");
            UUID contractId = UUID.fromString(scanner.nextLine());
            if (contractId == null) {
                System.out.println("please Enter the Id of the Contract: ");
                 UUID.fromString(scanner.nextLine());
            }

            CreateOfferDto dto = new CreateOfferDto(
                    offerName,
                    description,
                    startDate,
                    endtDate,
                    discountValue,
                    condition,
                    reductionType,
                    offerStatus,
                    contractId
            );
            service.update(dto, existOffer.getId());


            System.out.println("offre updated successfully.");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(){
        try {
            List<Offer> offers = service.findAll();
            if (offers.isEmpty()){
                System.out.println("no offers found");
                return;
            }
            for (int offer = 0 ; offer < offers.size(); offer++){
                System.out.println((offer + 1) + " . " + offers.get(offer).getOfferName() + " (ID: "  + offers.get(offer).getId() + ")");
            }
            System.out.println("Enter the number you want to delete : ");
            int index = scanner.nextInt();

            if (index < 1 || index > offers.size()){
                System.out.println("Invalid selection.");
                return;
            }
            Offer existOffer = offers.get(index - 1);
            if (existOffer.getId() != null) {
                service.delete(existOffer.getId().toString());
                System.out.println("Contract deleted successfully.");
            } else {
                System.out.println("Error: The selected Contract does not have a valid ID.");
            }
            System.out.println("contract Deleted successfully.");

            System.out.println("offre delete successfully.");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
