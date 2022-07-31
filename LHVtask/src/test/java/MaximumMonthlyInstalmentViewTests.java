import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MaximumMonthlyInstalmentViewTests {

    String cookies = "#pirukas";
    String acceptCookies = "#acceptPirukas";
    String monthlyPayment = ".payment";

    String inputMonthlyIncome = "input#monthly-income.form-control";


    @Test
    public void userCanApplyWithCoApplicant() {
        $(byText("koos kaastaotlejaga")).shouldBe(visible);
        $(byText("koos kaastaotlejaga")).click();
        $(byText("Põhitaotleja")).shouldBe(visible);
        $(byText("Kaastaotleja")).shouldBe(visible);
        $(byText("Maksimaalse kuumakse arvutamiseks on netosissetulek liiga väike.")).shouldBe(visible);;
    }

    @Test
    public void userCanChangeMaritalStatus() {
        $(byText("abielus või vabaabielus")).click();
        $(monthlyPayment, 0).shouldHave(text("218"));
    }

    @Test
    public void userCanChangeNetIncome() {
        $(inputMonthlyIncome).setValue("2500");
        $(monthlyPayment, 0).shouldHave(text("1252"));
    }

    @BeforeClass
    public void beforeClass() {
        open("https://www.lhv.ee/et/liising");
        if ($(cookies).exists()){
            $(acceptCookies).click();
        }
        $(byText("Maksimaalne kuumakse")).click();
    }

    @AfterMethod
    public void tearDown() {
        refresh();
    }
}
