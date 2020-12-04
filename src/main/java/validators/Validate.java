package validators;

import typeOfContracts.Contract;

public interface Validate<Contract> {
     Message doValidate(Contract contract);
}
