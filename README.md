# evoBlackJack
Evolution of a simple Black Jack game to demonstrate OOP

# Evo 1
Shows basic OO responsibility assigment. The rules of the game are simplified, and there is no human player, but rather a very brave/foolish AI player.

## Rules
The game is played with one standard deck of cards. The deck contains 52 cards with one combination of each color and value. The colors are Hearts, Clubs, Diamonds, and Spades, the values are 2..10, Knight, Queen, King, and Ace.

The score of a hand (the cards assigned to the Dealer or Player) is computed by adding the score of each card. The car score is the value of the card, or 10 if the card i Knight, Queen or King. If the card is an Ace the value of the card can be 11 or 1 depending, the most advantageous score should always be used.

The objective of the game is to have a hand with a score as close to, but not over 21. If the score is over 21 the hand is bust.

### Start Phase
 1. The Dealer gets a new deck of cards and shuffles them randomly without showing the card face to anyone.
 2. The Dealer deals one card from the top of the deck, shows the card and gives it to the Player (the card is now included in the Player's hand).
 3. The Dealer deals one card from the top of the deck, shows the card and gives it to the herself (the card is now included in the Dealers's hand)
 4. The Dealer deals one card from the top of the deck, shows the card and gives it to the Player (the card is now included in the Player's hand).
 5. The Dealer deals one card from the top of the deck, shows the card and gives it to the herself (the card is now included in the Dealers's hand)
 6. The phase ends with both the Dealer and Player having two cards in their hand with the face value visible to all players. The score of each hand can now be calculated.
 
### Players Phase
 1. While the Player is not bust the Dealer asks the player to hit or stand. If the Player hits the Dealer deals one card from the top of the deck, shows the card and gives it to the Player (the card is now included in the Player's hand).
 2. If the player is bust the Dealer wins the game, otherwise the game enters the Dealers Phase.
 
### Dealers Phase
 1. While the Dealer is not bust and the Dealers score is <17 the dealer must Hit: The Dealer deals one card from the top of the deck, shows the card and gives it to the herself (the card is now included in the Dealers's hand)
 2. If the Dealer is bust the Player wins the game, otherwise the game is won by the one who has the score closest to 21. If Dealer and Player have an equal score the Dealer wins.

## Design
We will now design an object oriented program based on these rules. To aid in this we will use UML sequence and class diagrams. We will then implement the design in Java.

### Design Rules
To aid in our design we will try to assign responsibilities to objects so that the responsibilities
 1. make sense from a real life perspective (model reality)
 2. follows the principle that an object that contains information should be responsible for behavior that uses that information (information expert)
 
These two rules of thumb assures that we maximise two important aspects of object orientation: lowering the semantic gap between a problem (requirements) and the solution (program code), and encapsulation. Lowering the semantic gap in this case would mean that if we understand how to play black jack we should find few surprises in the actual design and implementation of the game. The design should reflect and represent reality.

Encapsulation is a general design goal in most methodologies/languages. To put it simply we want each object to be responsible for dealing with its own information and other objects should not be able to change this information. This will often make it easier to understand the design and implementation, i.e. the functionality and data needed to perform that functionality are put together. It also lowers the risk of bugs as rules for operating on the data can be collected in one place and we can ensure that the rules are executed. Good encapsulation also makes it easier to reuse parts of our design and implementation in other projects.

Sometimes these two rules can be conflicting, in such cases we will generally favor encapsulation.
 
Looking at the requirements above we can construct some sequence diagrams to clarify the needed behavior. A sequence diagram shows how objects collaborate to achive some goal by sending messages to eachother.
 
 ### Shuffling the Deck
 In the first step the Dealer interacts with two types of objects in real life: the Deck type and Card type. We can note from the requirements that the Deck actually contains the cards. We can note that the Dealer will interact with one Deck object but serveral Card objects. A simple way of shuffling a deck is to take a random card from the deck and placing it on top of the deck and repeat this proces a bunch of times. We can simply let the Dealer do this (as in real life), or let the deck do this as the Deck is the object that contains the cards to be shuffled have this responsibility. We can also note that the card objects are actually not that involved in the shuffling, they are only passed around in messages and return values. The sequence diagram below shows these two alternatives.
 ![](https://app.genmymodel.com/api/projects/_-kk90CqgEeuxZMUIY-ihGw/diagrams/_OSvpEAzPEDmYaKiOUqCuEA/jpeg)
 
 In reality often only people and complex things have behavior but the majority of objects are inanimate and do nothing. In software this is not always the case. We can easily give a behaviour to an object that is lifeless in reality.

In reality the Dealer picks a random card from the deck and places it on top, in sofware we can easily give this behavior to the deck itself.

 In this case the advantage is that we reduce the complexity of the Dealer (it will contain less code and it seems from the requirements the Dealer is quite busy so this could be worthwile). Deck will recieve a behavior that we likely can reuse, i.e. shuffling the cards in a deck is a common requirement in the domain of card games. We can also see that the Deck becomes simpler from an outside user, it will only have one public message (shuffle) instead of two (get random card, add card on top). Letting the deck work with the information (cards) it contains supports the information expert rule of thumb.

 The downside is that it can seem surprising with inanimate objects that has advanced behaviors. The software does not represent reality and can as a consequence become harder to understand. It is simply not that common with Decks that can shuffle themselves in reality.
 
 We will follow the information expert rule and let the Deck shuffle itself as the prefered design.
 
 ### Creating Objects
 One thing that is radically different between software and reality is object creation. In reality objects simply exist (well they are created in some way but this is seldom an issue). In software we need to expicitly create objects, and assigning this responsibility is not always easy. In this case the Dealer needs a Deck and the Deck needs to contain the Cards. As the Deck contains the cards we should (according to information expert) let the Deck be responsible for creating the cards it contains and make sure we get one card of every color and value combination every time a Deck object is created. It does not make sense to have an empty deck to start with.
 Also according to the requirements we can see that the Dealer is the only one handling the Deck. While we can hardly say the Dealer contains the Deck we can see that the Dealer works closely with the deck and no other object does that. In reality you would probably get kicked out of the casino if you tried to handle the deck as a player. So in this case the responsibility to handle the creation of the Deck also points towards the Dealer.
 
 ### Cards?!
 This leads us to one of the central objects in any card game. The card objects themselves. From the description we can see that they have at least two obvious responsibilities: to know their own color, and value. The color and value of a card is assigned to the card when it is created and should never change after that. Color and value should thus be parameters when creating a card object. In this case it can be tempting to add the color and value as primitive datatypes (int or string), but that would create the opportunity for errors. There are only a limited set of colors and values, ints or strings can contain much more than that. If we used them we would need to check these so they are not "out of bounds" we would also need to add an "interpretation" i.e. 11 is ace. Fortunately there is a simple solution to this problem: use Enumerations.
 There is a third more subtle thing about cards mentioned in the requirements, they can be hidden or visible. If they are hidden the color and values cannot be seen. This is a third responsibility of cards, they need to know if they are visible or not.
 
### Finally Starting the Game
We now taken care of the first step: created a deck of cards, shuffled them, and can move on.
In the second step the Dealer takes the top card from the deck, shows it and gives it to the player and it is now included in the hand. We can see that this responsibility is repeated at least once more. In this description we find two new objects: the Player and the Player's Hand. We can also see that the next step is quite similar, and introduces a third new object: the Dealer's Hand.
We first need a responsibility to get the top card Card (from the Deck), and as the Deck contains the Cards, it should have this responsibility. The next responsibility is to show the card, as the card itself is responsible for knowing if it is hidden, this responsibility is assigned to the Card. Next the Player is responsible for having it's own hand, and thus needs a responsibility for recieving a card and in turn the Hand needs to accept that a card has been added to it. Finally the dealer is responsible for this overall process of dealing a card to the player.
Similarly the Dealer is responsible for having it's own hand.
The process is repeated two times (step 4 and 5)

To calculate the scores of the Dealer and Player we take a look at the information needed (the Dealer's and Player's cards respectively) and also in reality both the Dealer and Player can calulate the scores. 

