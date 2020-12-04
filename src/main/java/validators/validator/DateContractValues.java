package validators.validator;

import typeOfContracts.Contract;
import validators.Message;
import validators.Validate;
import validators.ValidationStatus;

import java.time.LocalDate;

public class DateContractValues implements Validate<Contract> {

    @Override
    public Message doValidate(Contract contract) {
        LocalDate startDate = contract.getStartDate();
        LocalDate endDate = contract.getEndDate();
        if (checkCorrectDate(startDate, endDate))
            return new Message("incorrect chronological order of dates", ValidationStatus.ERROR);
        return new Message(ValidationStatus.ОК);
    }

    public boolean checkCorrectDate(LocalDate startDate, LocalDate endDate) {
        return startDate.isAfter(endDate);
    }
}
