package ui;

import model.AmortizedLoan;

public class Main {
    public static void main(String[] args) {
        AmortizedLoan testloan = new AmortizedLoan("test",5000,0.08,4);
        System.out.println(testloan.getYearlyAnnuities());

    }
}
