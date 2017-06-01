package II_multiClient.tcp_loan;

public class Loan implements java.io.Serializable {
    private double annualInterestRate;
    private int years;
    private int loanAmount;
    private double monthlyPayment;
    private double yearlyPayment;

    public Loan( double annualInterestRate, int years, int loanAmount ) {
        setAnnualInterestRate( annualInterestRate );
        setYears( years );
        setLoanAmount( loanAmount );
        setMonthlyPayment( 0 );
        setYearlyPayment( 0 );
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public double getYearlyPayment() {
        return yearlyPayment;
    }

    public void setYearlyPayment(double yearlyPayment) {
        this.yearlyPayment = yearlyPayment;
    }
}
