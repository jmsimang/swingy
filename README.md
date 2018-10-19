# swingy
An implementation of a minimalistic text-/GUI-based RPG game
The program needs to follow the Model-View-Controller architecture and allow switching between the console view and GUI view.

## Gameplay 
A player can have multiple heroes of diﬀerent types. When the player starts the game he has 2 options: 
• Create a hero 
• Select a previously created hero. 

In either case, the player can see the hero stats. Hero stats are aﬀected by the hero level and artifacts. 

There are 3 types of artefacs: 
• Weapon - increases the attack 
• Armor - increases defense 
• Helm - increases hit points 
After choosing a hero the actual game begins. 

The hero needs to navigate a square map with the size calculated by the formula (level-1)*5+10-(level%2).

The initial position of the hero is in the center of the map. He wins the game if he reaches on of the borders of the map. Each turn he can move one position in one of the 4 four directions:
• North 
• East 
• South 
• West

When a map is generated, villains of varying power will be spread randomly over the map. When a hero moves to a position occupied by a villain, the hero has 2 options: 
• Fight, which engages him in a battle with the villain 
• Run, which gives him a 50% chance of returning to the previous position. 
If the odds aren’t on his side, he must ﬁght the villain. You will need to simulate the battle between the hero and monster and present the user the outcome of the battle. 

If a hero looses a battle, he dies and also looses the mission.
If a hero wins a battle, he gains: 
• Experience points, based on the villain power. He will level up if he reaches the next level experience. 
• An artifact, which he can keep or leave. The quality of the artefact also varies depending on the villain’s strength. 

Leveling up is based on the following formula level*1000+(level−1)2*450. So the necessary experience to level up will follow this pattern: 
• Level 1 - 1000 XP 
• Level 2 - 2450 XP 
• Level 3 - 4800 XP 
• Level 4 - 8050 XP 
• Level 5 - 12200 XP

## Features
The game can be launched in 2 modes as described below.
- $java -jar swingy.jar console
- $java -jar swingy.jar gui

## Validation
You will need to integrate a third party library in your project in order to provide annotation based validation. We highly recommend that you use a library that implements the javax.validation speciﬁcation. You will not allow any abnormal user input to disrupt the game behaviour. Validation failure will be highlighted to the user.

## Bonus parts
 You persist the user’s heroes in a relational database.
 You can switch between console view and GUI view at runtime, without closing the game
 
