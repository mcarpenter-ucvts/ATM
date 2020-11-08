public class ATM {

    // supported name formats.

    final static int DEFAULT = 1;   // e.g., Jane Doe
    final static int FORMAL = 2;    // e.g., Mrs. Doe
    final static int SORTABLE = 3;  // e.g., Doe, Jane

    // transaction status codes.

    final static int SUCCESS = 1;
    final static int INVALID_AMOUNT = 2;
    final static int INSUFFICIENT_FUNDS = 3;
    final static int ACCOUNT_NOT_FOUND = 4;

    private static BankAccount[] database = new BankAccount[2];     // dummy database

    /**
     * Creates an instance of the ATM class.
     */
    public ATM() {
        ATM.populate();
    }

    /**
     * Simulates using the ATM.
     */
    public void run() {
        BankAccount active = database[0];       // dummy accounts for
        BankAccount destination = database[1];  // simulation purposes

        simulateShowDetails(active);
        simulateValidDeposit(active);
        simulateInvalidDeposit(active);
        simulateValidWithdrawal(active);
        simulateInvalidWithdrawal(active);
        simulateInsufficientWithdrawal(active);
        simulateValidTransfer(active, destination);
        simulateInvalidTransfer(active, destination);
        simulateInsufficientTransfer(active, destination);
        simulateAccountNotFoundTransfer(active);
        simulationChangeInformation(active);
    }

    /*
     * Simulates viewing the account information.
     *
     * @param account the account to view
     */
    private void simulateShowDetails(BankAccount account) {
        System.out.println("Owner: " + account.getAccountOwner().getName(ATM.SORTABLE));
        System.out.println("Account No.: " + account.getMaskedAccountNumber());
        System.out.println("Email: " + account.getAccountOwner().getEmailAddress());
        System.out.println("Phone: " + account.getAccountOwner().getFormattedPhoneNumber());
        System.out.println("Mailing Address : " + pad(18, account.getAccountOwner().getMailingAddress().getFormattedAddress()));
        System.out.println();
    }

    /*
     * Simulates making a legal deposit.
     *
     * @param account the account into which to deposit funds
     */
    private void simulateValidDeposit(BankAccount account) {
        System.out.println("Account No.: " + account.getMaskedAccountNumber());
        System.out.println("Balance (before): " + account.getFormattedBalance());
        System.out.println("Status: " + getStatus(account.deposit(250)));
        System.out.println("Balance (after): " + account.getFormattedBalance());
        System.out.println();
    }

    /*
     * Simulates making an illegal deposit.
     *
     * @param account the account into which to deposit funds
     */
    private void simulateInvalidDeposit(BankAccount account) {
        System.out.println("Account No.: " + account.getMaskedAccountNumber());
        System.out.println("Balance (before): " + account.getFormattedBalance());
        System.out.println("Status: " + getStatus(account.deposit(0)));
        System.out.println("Balance (after): " + account.getFormattedBalance());
        System.out.println();
    }

    /*
     * Simulates making a legal withdrawal.
     *
     * @param account the account from which to withdraw funds
     */
    private void simulateValidWithdrawal(BankAccount account) {
        System.out.println("Account No.: " + account.getMaskedAccountNumber());
        System.out.println("Balance (before): " + account.getFormattedBalance());
        System.out.println("Status: " + getStatus(account.withdraw(250)));
        System.out.println("Balance (after): " + account.getFormattedBalance());
        System.out.println();
    }

    /*
     * Simulates making an illegal withdrawal.
     *
     * @param account the account from which to withdraw funds
     */
    private void simulateInvalidWithdrawal(BankAccount account) {
        System.out.println("Account No.: " + account.getMaskedAccountNumber());
        System.out.println("Balance (before): " + account.getFormattedBalance());
        System.out.println("Status: " + getStatus(account.withdraw(-1)));
        System.out.println("Balance (after): " + account.getFormattedBalance());
        System.out.println();
    }

    /*
     * Simulates making a withdrawal with insufficient funds.
     *
     * @param account the account from which to withdraw funds
     */
    private void simulateInsufficientWithdrawal(BankAccount account) {
        System.out.println("Account No.: " + account.getMaskedAccountNumber());
        System.out.println("Balance (before): " + account.getFormattedBalance());
        System.out.println("Status: " + getStatus(account.withdraw(5000)));
        System.out.println("Balance (after): " + account.getFormattedBalance());
        System.out.println();
    }

    /*
     * Simulates making a legal transfer.
     *
     * @param origin the origin account
     * @param destination the destination account
     */
    private void simulateValidTransfer(BankAccount origin, BankAccount destination) {
        System.out.println("Origin: " + origin.getMaskedAccountNumber());
        System.out.println("Balance (before): " + origin.getFormattedBalance());
        System.out.println("Destination: " + destination.getMaskedAccountNumber());
        System.out.println("Balance (before): " + destination.getFormattedBalance());
        System.out.println("Status: " + getStatus(origin.transfer(destination.getAccountNumber(), 500)));
        System.out.println("Balance (after, origin): " + origin.getFormattedBalance());
        System.out.println("Balance (after, destination): " + destination.getFormattedBalance());
        System.out.println();
    }

    /*
     * Simulates making an illegal transfer.
     *
     * @param origin the origin account
     * @param destination the destination account
     */
    private void simulateInvalidTransfer(BankAccount origin, BankAccount destination) {
        System.out.println("Origin: " + origin.getMaskedAccountNumber());
        System.out.println("Balance (before): " + origin.getFormattedBalance());
        System.out.println("Destination: " + destination.getMaskedAccountNumber());
        System.out.println("Balance (before): " + destination.getFormattedBalance());
        System.out.println("Status: " + getStatus(origin.transfer(destination.getAccountNumber(), -1)));
        System.out.println("Balance (after, origin): " + origin.getFormattedBalance());
        System.out.println("Balance (after, destination): " + destination.getFormattedBalance());
        System.out.println();
    }

    /*
     * Simulates making a transfer with insufficient funds.
     *
     * @param origin the origin account
     * @param destination the destination account
     */
    private void simulateInsufficientTransfer(BankAccount origin, BankAccount destination) {
        System.out.println("Origin: " + origin.getMaskedAccountNumber());
        System.out.println("Balance (before): " + origin.getFormattedBalance());
        System.out.println("Destination: " + destination.getMaskedAccountNumber());
        System.out.println("Balance (before): " + destination.getFormattedBalance());
        System.out.println("Status: " + getStatus(origin.transfer(destination.getAccountNumber(), 5000)));
        System.out.println("Balance (after, origin): " + origin.getFormattedBalance());
        System.out.println("Balance (after, destination): " + destination.getFormattedBalance());
        System.out.println();
    }

    /*
     * Simulates making a transfer to a non-existent account.
     *
     * @param origin the origin account
     */
    private void simulateAccountNotFoundTransfer(BankAccount origin) {
        System.out.println("Origin: " + origin.getMaskedAccountNumber());
        System.out.println("Balance (before): " + origin.getFormattedBalance());
        System.out.println("Destination: 1000009");
        System.out.println("Balance (before): N/A");
        System.out.println("Status: " + getStatus(origin.transfer(10000009L, 500)));
        System.out.println("Balance (after, origin): " + origin.getFormattedBalance());
        System.out.println("Balance (after, destination): N/A");
        System.out.println();
    }

    /*
     * Simulates changing the account information.
     *
     * @param account the account to change
     */
    private void simulationChangeInformation(BankAccount account) {
        account.getAccountOwner().setSalutation("Ms");
        account.getAccountOwner().setFirstName("Paulina");
        account.getAccountOwner().setLastName("Bridges");
        account.getAccountOwner().setEmailAddress("pbridges@example.com");
        account.getAccountOwner().setPhoneNumber(5557773333L);
        account.getAccountOwner().getMailingAddress().setPrimaryStreet("456 Center Street");
        account.getAccountOwner().getMailingAddress().setSecondaryStreet("Building 7, Apartment G");
        account.getAccountOwner().getMailingAddress().setCity("New York");
        account.getAccountOwner().getMailingAddress().setState("New York");
        account.getAccountOwner().getMailingAddress().setPostalCode("10027");

        System.out.println("Owner: " + account.getAccountOwner().getName(ATM.FORMAL));
        System.out.println("Account No.: " + account.getMaskedAccountNumber());
        System.out.println("Email: " + account.getAccountOwner().getEmailAddress());
        System.out.println("Phone: " + account.getAccountOwner().getFormattedPhoneNumber());
        System.out.println("Mailing Address : " + pad(18, account.getAccountOwner().getMailingAddress().getFormattedAddress()));
    }

    /*
     * Performs an account lookup by account number.
     *
     * @param accountNumber the account number to find
     * @return the bank account, or null if no account was found
     */
    public static BankAccount lookup(long accountNumber) {
        for (BankAccount account : database) {
            if (account != null && account.getAccountNumber() == accountNumber) {
                return account;
            }
        }

        return null;
    }

    /*
     * Dummy method to provide you with two workable accounts with which to
     * test your code. This is obviously not how we'd do this in the real world.
     */
    private static void populate() {
        database[0] = new BankAccount(
                new AccountOwner(
                        "Mr",
                        "James",
                        "Stewart",
                        "jstewart@example.com",
                        5551232244L,
                        new MailingAddress(
                                "123 Main Street",
                                "Apartment 1A",
                                "Scotch Plains",
                                "NJ",
                                "07076"
                        )
                ),
                1000
        );
        database[1] = new BankAccount(
                new AccountOwner(
                        "Mrs",
                        "Henrietta",
                        "Phillips",
                        "henphillips@example.com",
                        5551236688L,
                        new MailingAddress(
                                "2467 Hemlock Road",
                                null,
                                "North Arlington",
                                "Texas",
                                "76006"
                        )
                ),
                2500
        );
    }

    /*
     * Left pads a string at the line breaks with a number of spaces.
     *
     * @param pad the number of spaces needed
     * @param text the text to be left padded at the line breaks
     * @return the left padded text
     */
    private String pad(int pad, String text) {
        int length = text.length() + pad;
        String[] parts = text.split("\n");
        StringBuilder sb = new StringBuilder();

        if (parts.length == 3) {
            while (sb.length() < length - text.length()) {
                sb.append(" ");
            }

            return parts[0] + "\n" + sb.toString() + parts[1] + "\n" + sb.toString() + parts[2];
        } else if (parts.length == 2) {
            while (sb.length() < length - text.length()) {
                sb.append(" ");
            }

            return parts[0] + "\n" + sb.toString() + parts[1];
        } else {
            return text;
        }
    }

    /*
     * Generates and returns a readable status message.
     *
     * @param code the status code
     * @return the readable message
     */
    private String getStatus(int code) {
        switch (code) {
            case ATM.SUCCESS: return "Success.";
            case ATM.INVALID_AMOUNT: return "Invalid amount.";
            case ATM.INSUFFICIENT_FUNDS: return "Insufficient funds.";
            case ATM.ACCOUNT_NOT_FOUND: return "Account not found.";
            default: return "Unknown.";
        }
    }

    /*
     * Main method.
     *
     * @param args optional command line arguments
     */
    public static void main(String[] args) {
        new ATM().run();
    }
}