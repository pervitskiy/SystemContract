package validators.validator;

import typeOfContracts.Contract;
import typeOfContracts.InternetContract;
import typeOfContracts.MobileContract;
import typeOfContracts.TVContract;
import validators.Message;
import validators.Validate;
import validators.ValidationStatus;

import java.util.regex.Pattern;


/**
 * Class for validating an additional field for Contracts
 */
public class AdditionValidate implements Validate<Contract> {

    private static final int MIN_SMS = 0;
    private static final int MAX_SMS = 5000;

    private static final int MIN_MEGABYTES = 0;
    private static final int MAX_MEGABYTES = 1024 * 150;

    private static final int MIN_MINUTES = 0;
    private static final int MAX_MINUTES = 3600;


    private static final int MIN_SPEED = 4;
    private static final int MAX_SPEED = 3000;
    private static final int MIN_RECOMMENDED_SPEED = 50;

    /**
     * @param contract - the contract for validate
     * @return - Validation status message
     */
    @Override
    public Message doValidate(Contract contract) {
        //
        if (contract instanceof InternetContract) {
            return validateInternetContact((InternetContract)contract);
        }
        if (contract instanceof TVContract) {
            return new Message(ValidationStatus.ОК);
        }
        if (contract instanceof MobileContract) {
            return validateMobileContract((MobileContract)contract);
        }
        return new Message("something went wrong", ValidationStatus.ERROR);
    }

    /**
     * @param contract - the MobileContract for validate
     * @return - Validation status message
     */

    private Message validateMobileContract(MobileContract contract) {
        int sms = contract.getRate().getNumberOfSms();
        int gb = contract.getRate().getNumberOfGb();
        int minutes = contract.getRate().getNumberOfMinutes();

        if (checkInvalidChar(sms) && checkInvalidChar(minutes) && checkInvalidChar(gb))
            return new Message(contract.getRate() + "is invalid character", ValidationStatus.ERROR);
        if (checkTheRangeOfSmsValues(sms))
            return new Message("the number of sms" + sms + "is not in the range", ValidationStatus.ERROR);
        if (checkTheRangeOfMegabytesValues(gb))
            return new Message("the number of gb" + gb + "is not in the range", ValidationStatus.ERROR);
        if (checkTheRangeOfMinutesValues(minutes))
            return new Message("the number of minutes" + minutes + "is not in the range", ValidationStatus.ERROR);
        return new Message(ValidationStatus.ОК);
    }

    /**
     * @param sms - number of SMS messages
     * @return true - if it is not in the acceptable range
     */
    private boolean checkTheRangeOfSmsValues(int sms) {
        return sms < MIN_SMS || sms > MAX_SMS;
    }


    /**
     * @param str - any line
     * @return true - if the line contains invalid characters
     */
    private boolean checkInvalidChar(int str) {
        Pattern pattern = Pattern.compile("[^0-9]");
        return pattern.matcher(String.valueOf(str)).find();
    }

    /**
     * @param megabytes  - number of megabytes in rate
     * @return true - if it is not in the acceptable range
     */
    private boolean checkTheRangeOfMegabytesValues(int megabytes) {
        return megabytes < MIN_MEGABYTES || megabytes > MAX_MEGABYTES;

    }

    /**
     * @param minutes - number of minutes in rate
     * @return true - if it is not in the acceptable range
     */
    private boolean checkTheRangeOfMinutesValues(int minutes) {
        return minutes < MIN_MINUTES || minutes > MAX_MINUTES;
    }

    /**
     * @param contract - the MobileContract for validate
     * @return - Validation status message
     */
    private Message validateInternetContact(InternetContract contract) {
        int internetSpeed = contract.getInternetSpeed();
        if (checkInvalidChar(internetSpeed)) {
            return new Message("internet Speed " + internetSpeed + "is invalid character", ValidationStatus.ERROR);
        }
        if (checkInternetSpeedLessThenMinRecommendedSpeed(internetSpeed))
            return new Message("internet speed " + internetSpeed + "is less than the recommended values", ValidationStatus.WARNING);

        if (checkInternetSpeed(internetSpeed))
            return new Message("internet speed" + internetSpeed + "is not in the range", ValidationStatus.ERROR);
        return new Message(ValidationStatus.ОК);
    }

    /**
     * @param speed - internet speed
     * @return true - if internet Speed is not in the acceptable range
     */
    private boolean checkInternetSpeed(int speed) {
        return speed < MIN_SPEED || speed > MAX_SPEED;
    }

    /**
     * @param internetSpeed - internet speed
     * @return true - if the internet speed is less than the recommended value
     */
    private boolean checkInternetSpeedLessThenMinRecommendedSpeed(int internetSpeed) {
        return internetSpeed < MIN_RECOMMENDED_SPEED && internetSpeed > MIN_SPEED ;
    }

}
