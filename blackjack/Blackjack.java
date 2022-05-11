package blackjack;

import java.util.Scanner;


//import javax.swing.text.PlainDocument;

public class Blackjack{

        public static void main(String[] args) {

                //Welcome
                System.out.println("Welcome To Blackjack");
                //Playing Deck
                Deck playingDeck = new Deck();
                playingDeck.createFullDeck();
                playingDeck.shuffle();
                //create a deck for player
                Deck playerDeck = new Deck();
                Deck dealerDeck = new Deck();

                double playerMoney = 100000.00;

                Scanner userInput = new Scanner(System.in);
        //Game Loop
                 while(playerMoney >0){
                         System.out.println("You have $" +playerMoney+", How much would you like to bet");
                        double playerBet = userInput.nextDouble();
                        if(playerBet > playerMoney){
                        System.out.println("What Are You trying to Pull?");
                        break;
                }

                boolean endRound = false;



                //player cards
                playerDeck.draw(playingDeck);
                playerDeck.draw(playingDeck);

                //Dealer cards
                dealerDeck.draw(playingDeck);
                dealerDeck.draw(playingDeck);

                while (true){
                        System.out.println("Your Hand:");
                        System.out.print(playerDeck.toString());
                        System.out.println("Total:" + playerDeck.cardsValue());

                                //dealer gand
                        System.out.println("Dealer Hand" +dealerDeck.getCard(0).toString() + "and [Hidden]");
                        System.out.println("Would you like to (1)Hit (2)Stand");
                        int response = userInput.nextInt();

                        if (response ==1){
                                playerDeck.draw(playingDeck);
                                System.out.println("You Draw a: " + playerDeck.getCard(playerDeck.deckSize()-1).toString());
                                //bust if > 21
                                if(playerDeck.cardsValue() > 21){
                                        System.out.println("BUST at:" +playerDeck.cardsValue());
                                        playerMoney-= playerBet;
                                        endRound = true;
                                        break;
                                }
                       
                        }
                        if(response == 2){
                                break;
                        }
                }

                //reveal
                System.out.println("Dealers Card: "+ dealerDeck.toString());
                //see if dealer has more than dealer
                if ((dealerDeck.cardsValue() > playerDeck.cardsValue()) &&endRound==false ){
                        System.out.println("Dealer wins: ");
                        endRound = true;

                }
                //dealer draws to 16 stand if 17
                while ((dealerDeck.cardsValue() < 17)&& endRound == false){
                        dealerDeck.draw(playerDeck);
                        System.out.println("Dealer Draws:" + dealerDeck.getCard(dealerDeck.deckSize() -1).toString() );
                }
                //display total dealer
                System.out.println("dealers hand is: "+ dealerDeck.cardsValue() );
                
                if((dealerDeck.cardsValue() >21) && endRound == false){
                        System.out.println("Dealer has busted: You win!");
                        playerMoney += playerBet;
                        endRound = true;
                }
                        //Push?
                        if ((playerDeck.cardsValue() == dealerDeck.cardsValue())&& endRound == false){
                                System.out.println("PUSH");
                                endRound = true;
                        }

                        if((playerDeck.cardsValue()>dealerDeck.cardsValue()) && endRound == false){
                                System.out.println("YOU WIN");
                                playerMoney += playerBet;
                                endRound = true;
                        }
                playerDeck.moveAllToDeck(playingDeck);
                dealerDeck.moveAllToDeck(playerDeck);
                System.out.println("End of hand. Place Your bets");


                        



        }
        System.out.println("GAME OVER YOURE BROKE");
            
        }



}