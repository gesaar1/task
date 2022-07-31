import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class MainTests {

    String cookies = "#pirukas";
    String acceptCookies = "#acceptPirukas";

    String calculator = "#kalkulaator";
    String calculatorHeader = "#kalkulaator.row.section-title";
    String inputVehiclePrice = "input#price.form-control";
    String inputDownpaymentPercentage = "input#initial_percentage.form-control";
    String inputDownpaymentAmount = "input#initial.form-control";
    String inputInterestRate = "input#interest_rate.form-control";
    String inputReminderPercentage = "input#reminder_percentage.form-control";
    String inputReminderAmount = "input#reminder.form-control";
    String monthlyPayment = ".payment";

    String inputMonthlyIncome = "input#monthly-income.form-control";

    @Test
    public void userCanOpenLhvLeasingPage() {
        $(calculator).shouldBe(visible);
        $(calculatorHeader).find("h2").shouldHave(text("Arvuta kuumakse"));
    }

    @Test
    public void userCanSeeLeasingCalculatorDefaultValues() {
        $(inputVehiclePrice).shouldHave(value("15 000"));
        $(inputDownpaymentPercentage).shouldHave(value("10"));
        $(inputDownpaymentAmount).shouldHave(value("1500"));
        $(inputInterestRate).shouldHave(value("4"));
        $(inputReminderPercentage).shouldHave(value("10"));
        $(inputReminderAmount).shouldHave(value("1500"));
        $(monthlyPayment, 0).shouldHave(text("192"));
    }

    @Test
    public void userCanOpenMaximumMonthlyInstalmentView() {
        $(byText("Maksimaalne kuumakse")).click();
        $(byText("Ülalpeetavate arv")).shouldBe(visible);
        $(inputMonthlyIncome).shouldHave(value("900"));
    }


    @BeforeClass
    public void beforeClass() {
        open("https://www.lhv.ee/et/liising");
        if ($(cookies).exists()){
            $(acceptCookies).click();
        }
    }

    @AfterMethod
    public void tearDown() {
        refresh();
    }
}
