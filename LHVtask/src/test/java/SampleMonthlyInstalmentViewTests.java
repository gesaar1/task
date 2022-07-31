import com.codeborne.selenide.selector.ByAttribute;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SampleMonthlyInstalmentViewTests {

    String cookies = "#pirukas";
    String acceptCookies = "#acceptPirukas";

    String inputVehiclePrice = "input#price.form-control";
    String inputDownpaymentPercentage = "input#initial_percentage.form-control";
    String inputDownpaymentAmount = "input#initial.form-control";
    String inputInterestRate = "input#interest_rate.form-control";
    String inputReminderPercentage = "input#reminder_percentage.form-control";
    String inputReminderAmount = "input#reminder.form-control";
    String monthlyPayment = ".payment";


    @Test
    public void userCanChooseToApplyAsLegalPerson() {
        $(byText("juriidilise isikuna")).shouldBe(visible);
        $(byText("juriidilise isikuna")).click();
        $(byText("Käibemaksu tasumine")).shouldBe(visible);
        $(monthlyPayment, 0).shouldHave(text("157"));
    }

    @Test
    public void userCanChangeLeaseTypeToOperationalLease() {
        $(byText("kasutusrent")).shouldBe(visible);
        $(byText("kasutusrent")).click();
        $(monthlyPayment, 0).shouldHave(text("188"));
    }

    @Test
    public void userCanChangeDownPaymentPercentage() {
        $(inputDownpaymentPercentage).setValue("20");
        $(inputDownpaymentAmount).shouldHave(value("3000"));
        $(monthlyPayment, 0).shouldHave(text("169"));
    }

    @Test
    public void userCanChangeDownPaymentAmount() {
        $(inputDownpaymentAmount).setValue("4000");
        $(inputDownpaymentPercentage).shouldHave(value("26.67"));
        $(monthlyPayment, 0).shouldHave(text("153"));
    }

    @Test
    public void userCanChangeInterest() {
        $(inputInterestRate).setValue("6");
        $(monthlyPayment, 0).shouldHave(text("206"));
    }

    @Test
    public void userCanChangeResidualValuePercentage() {
        $(inputReminderPercentage).setValue("30");
        $(inputReminderAmount).shouldHave(value("4500"));
        $(monthlyPayment, 0).shouldHave(text("155"));
    }

    @Test
    public void userCanChangeResidualValueAmount() {
        $(inputReminderAmount).setValue("6000");
        $(inputReminderPercentage).shouldHave(value("40"));
        $(monthlyPayment, 0).shouldHave(text("137"));
    }

    @Test
    public void userCanChangePriceOfTheVehicle() {
        $(inputVehiclePrice).setValue("40 000");
        $(inputDownpaymentAmount).shouldHave(value("4000"));
        $(inputReminderAmount).shouldHave(value("4000"));
        $(monthlyPayment, 0).shouldHave(text("513"));

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
