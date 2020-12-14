package stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import pages.MainPage;

public class MainPageStepDefs {

    MainPage mainPage = new MainPage();

    @Given("I am on the Main page of application")
    public void i_am_on_the_main_page_of_application(){
        mainPage.oenMainPage();
    }

    @When("I fill subscription field with already registered address {string}")
    public void i_fill_subscription_field_with_already_registered_address(String email){
        //System.out.format("Thread ID - %2d - %s from %s feature file.\n",
               // Thread.currentThread().getId(), email);
        mainPage.findIframe();
        mainPage.waitingUntilLoaderisVisible();
        mainPage.scrollToVisibleBlockNewsLetter();
        mainPage.fillAlreadyUsedEmail(email);
    }
    @Then("I see success message {string} appears")
    public void i_see_success_message_appears(String successMessage){
       // System.out.format("Thread ID - %2d - %s from %s feature file.\n",
                //Thread.currentThread().getId(), errorMessage);
        String actualSuccessSubscriptionMessage = mainPage.getSuccessSubscriptionMessage();
        Assertions.assertThat(actualSuccessSubscriptionMessage).isEqualTo(successMessage);
    }

    @When("I open localization drop down list")
    public void i_open_localization_drop_down_list(){
        mainPage.findIframe();
        mainPage.waitingUntilLoaderisVisible();
        mainPage.openLanguagesDropDownList();
    }

    @Then("I see that {int} languages exists in top menu")
    public void i_see_that_languages_exists_in_top_menu(int numOfLang){
        int actualActualLangNumber = mainPage.checkNumberOfLanguages();
        Assertions.assertThat(actualActualLangNumber).isGreaterThanOrEqualTo(numOfLang);
    }
    @And("I see that {string} exist in list of languages")
    public void i_see_that_exist_in_list_of_languages(String ukrSelectItem){
        String actualTextUkrItem = mainPage.getTextUkrItem();
        Assertions.assertThat(actualTextUkrItem).isEqualTo(ukrSelectItem);
    }


}
