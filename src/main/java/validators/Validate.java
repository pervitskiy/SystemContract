package validators;

import typeOfContracts.Contract;

public interface Validate<Contract> {

     /**
      * @param contract - the contract for validate
      * @return - Validation status message
      */
     Message doValidate(Contract contract);
}
