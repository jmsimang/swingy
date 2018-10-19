package za.co.amjsolutions.swingy.controllers;

import za.co.amjsolutions.swingy.interfaces.WelcomeViewCommands;

public class WelcomeViewCommandsController {
    private WelcomeViewCommands welcomeViewCommands;

    public WelcomeViewCommandsController(WelcomeViewCommands welcomeViewCommands) {
        this.welcomeViewCommands = welcomeViewCommands;
    }

    public void onChooseHero() {
        welcomeViewCommands.chooseHeroView();
    }

    public void onCreateHero() {
        welcomeViewCommands.createHeroView();
    }

    public void onSwitchViews() {
        welcomeViewCommands.switchView();
    }
}
