# evoBlackJack
Evolution of a simple Black Jack game to demonstrate OOP

# Evo 1
Shows basic OO responsibility assigment. The rules of the game are simplified, and there is no human player, but rather a very brave/foolish AI player.

## Rules
The game is played with one standard deck of cards. The colors are Hearts, Clubs, Diamonds, and Spades, the values are 2-10, Knight, Queen, King, and Ace.

The score of a hand (the cards assigned to the Dealer or Player) is computed by adding the score of each card. The car score is the value of the card, or 10 if the card i Knight, Queen or King. If the card is an Ace the value of the card can be 11 or 1 depending, the most advantageous score should always be used.

The objective of the game is to have a hand with a score as close to, but not over 21. If the score is over 21 the hand is bust.

### Start Phase
 1. The Dealer gets a new deck of cards and shuffles them randomly without showing the card face to anyone.
 2. The Dealer deals one card from the top of the deck to the Player and shows the face with the color and value to all players.
 3. The Dealer deals one card from the top of the deck to herself and shows the face to all players.
 4. The Dealer deals one card from the top of the deck to the Player and shows the face with the color and value to all players.
 5. The Dealer deals one card to herself and shows the face to all players.
 6. The phase ends with both the Dealer and Player having two cards in their hand with the face value visible to all players. The score of each hand can now be calculated.
 
### Players Phase
 1. While the Player is not bust the Player can choose to Hit: The Dealer will give the Player a new card and shows the face with the color and value to all players.
 2. If the player is bust the Dealer wins the game, otherwise the game enters the Dealers Phase.
 
### Dealers Phase
 1. While the Dealer is not bust and the Dealers score is <17 the dealer must Hit: The Dealer deals one card from the top of the deck to herself and shows the face to all players.
 2. If the Dealer is bust the Player wins the game, otherwise the game is won by the one who has the score closest to 21. If Dealer and Player have an equal score the Dealer wins.

## Design
We will now design an object oriented program based on these rules. To aid in this we will use UML sequence and class diagrams. We will then implement the design in Java.

### Design Rules
To aid in our design we will try to assign responsibilities to objects so that the responsibilities
 1. make sense from a real life perspective
 2. follows the principle that an object that owns information should be responsible for behavior that uses that information.
 
These two rules of thumb assures that we maximise two important aspects of object orientation: lowering the semantic gap between a problem (requirements) and the solution (program code), and encapsulation. Lowering the semantic gap in this case would mean that if we understand how to play black jack we should find few surprises in the actual design and implementation of the game. The design should reflect and represent reality.

Encapsulation is a general design goal in most methodologies/languages. To put it simply we want each object to be responsible for dealing with its own information and other objects should not be able to change this information. This will often make it easier to understand the design and implementation, i.e. the functionality and data needed to perform that functionality are put together. It also lowers the risk of bugs as rules for operating on the data can be collected in one place and we can ensure that the rules are executed. Good encapsulation also makes it easier to reuse parts of our design and implementation in other projects.

Sometimes these two rules can be conflicting, in such cases we will generally favor encapsulation.
 
Looking at the requirements above we can construct some sequence diagrams to clarify the needed behavior. A sequence diagram shows how objects collaborate to achive some goal by sending messages to eachother.
 
 ### Shuffling the Deck
 In the first step the Dealer interacts with two types of objects in real life: the Deck type and Card type. We can note that the Dealer will interact with one Deck object but serveral Card objects. A simple way of shuffling a deck is to take a random card from the deck and placing it on top of the deck. We can simply let the Dealer do this (as in real life).
 ![](https://app.genmymodel.com/api/projects/_-kk90CqgEeuxZMUIY-ihGw/diagrams/_OSvpEAzPEDmYaKiOUqCuEA/jpeg)
 
 
