package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    DataHelper.InfoCard firstCard = DataHelper.getCard1();
    DataHelper.InfoCard secondCard = DataHelper.getCard2();

    @BeforeEach
    void shouldTransfer() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void topUpCardSecond() {
        DashboardPage dashboardPage = new DashboardPage();
        int amount = 10000;
        int firstBalance = dashboardPage.getCardBalance(firstCard);
        int secondBalance = dashboardPage.getCardBalance(secondCard);
        var transfer = dashboardPage.choiceCard(secondCard);
        transfer.upperCard(amount, firstCard);
        assertEquals(firstBalance - amount, dashboardPage.getCardBalance(firstCard));
        assertEquals(secondBalance + amount, dashboardPage.getCardBalance(secondCard));
    }

    @Test
    void topUpCardFirst() {
        DashboardPage dashboardPage = new DashboardPage();
        int amount = 10000;
        int firstBalance = dashboardPage.getCardBalance(firstCard);
        int secondBalance = dashboardPage.getCardBalance(secondCard);
        var transfer = dashboardPage.choiceCard(firstCard);
        transfer.upperCard(amount, secondCard);
        assertEquals(firstBalance + amount, dashboardPage.getCardBalance(firstCard));
        assertEquals(secondBalance - amount, dashboardPage.getCardBalance(secondCard));
    }

    @Test
    void negativeСardBalance() {
        DashboardPage dashboardPage = new DashboardPage();
        int amount = 50000;
        int firstBalance = dashboardPage.getCardBalance(firstCard);
        int secondBalance = dashboardPage.getCardBalance(secondCard);
        var transfer = dashboardPage.choiceCard(secondCard);
        transfer.upperCard(amount, firstCard);
        assertEquals(firstBalance, dashboardPage.getCardBalance(firstCard));
        assertEquals(secondBalance,dashboardPage.getCardBalance(secondCard));
    }
}
