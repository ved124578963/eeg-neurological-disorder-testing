package com.neurodetect.tests;

import com.neurodetect.pages.TeamPage;
import com.neurodetect.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamPageTest extends BaseTest {

    @BeforeMethod
    public void goToTeamPage() {
        navigateTo("/team");
    }

    @Test(description = "Page title is displayed")
    public void pageTitleIsDisplayed() {
        TeamPage page = new TeamPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertFalse(title.isEmpty(), "Team page title should not be empty");
    }

    @Test(description = "Exactly 4 team member cards are displayed")
    public void fourTeamCardsAreDisplayed() {
        TeamPage page = new TeamPage(getDriver());
        Assert.assertEquals(page.getTeamCardCount(), 4,
            "Exactly 4 team member cards should be displayed");
    }

    @Test(description = "Each team card has a member name")
    public void eachCardHasMemberName() {
        TeamPage page = new TeamPage(getDriver());
        Assert.assertEquals(page.getMemberNameCount(), 4,
            "Each of the 4 team cards should have a member name (h3)");
    }

    @Test(description = "Each team card has a GitHub link")
    public void eachCardHasGithubLink() {
        TeamPage page = new TeamPage(getDriver());
        Assert.assertEquals(page.getGithubLinkCount(), 4,
            "Each team card should have a GitHub link");
    }

    @Test(description = "Each team card has a LinkedIn link")
    public void eachCardHasLinkedinLink() {
        TeamPage page = new TeamPage(getDriver());
        Assert.assertEquals(page.getLinkedinLinkCount(), 4,
            "Each team card should have a LinkedIn link");
    }
}
