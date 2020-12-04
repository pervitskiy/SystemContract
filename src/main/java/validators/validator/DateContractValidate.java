package validators.validator;

import typeOfContracts.Contract;
import validators.Message;
import validators.Validate;
import validators.ValidationStatus;

import java.time.LocalDate;

/**
 * Class for validating an Date Contract field for Contracts
 */
public class DateContractValidate implements Validate<Contract> {

    /**
     * @param contract - the contract for validate
     * @return - Validation status message
     */
    @Override
    public Message doValidate(Contract contract) {
        LocalDate startDate = contract.getStartDate();
        LocalDate endDate = contract.getEndDate();
        if (checkCorrectDate(startDate, endDate))
            return new Message("incorrect chronological order of dates", ValidationStatus.ERROR);
        return new Message(ValidationStatus.ОК);
    }

    /**
     * @param startDate - start date contract
     * @param endDate - expiration date contact
     * @return true - if dates in the correct chronological order
     */
    private boolean checkCorrectDate(LocalDate startDate, LocalDate endDate) {
        return startDate.isAfter(endDate);
    }
}
