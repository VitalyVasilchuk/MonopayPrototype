package apps.basilisk.monopayprototype.model.entity;

public class PaymentDetails {
    private String number;
    private String recipient;
    private String purpose;
    private String amountCommissionFree;
    private String commission;
    private String amount;

    public PaymentDetails(String number, String recipient, String purpose, String amountCommissionFree, String commission, String amount) {
        this.number = number;
        this.recipient = recipient;
        this.purpose = purpose;
        this.amountCommissionFree = amountCommissionFree;
        this.commission = commission;
        this.amount = amount;
    }

    public PaymentDetails() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getAmountCommissionFree() {
        return amountCommissionFree;
    }

    public void setAmountCommissionFree(String amountCommissionFree) {
        this.amountCommissionFree = amountCommissionFree;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PaymentDetails{" +
                "number='" + number + '\'' +
                ", recipient='" + recipient + '\'' +
                ", purpose='" + purpose + '\'' +
                ", amountCommissionFree='" + amountCommissionFree + '\'' +
                ", commission='" + commission + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
