public class Deck{
    public static int[] cards;
    Deck(){
        cards = [1, 3, 4, 10];
    }

    //dingie.cards = [1, 3, 4, 3];
    Deck dingie = Deck();
    dingie.cards[3] = 3;

    //pilates.cards = [1, 2, 4, 10];
    Deck pilates = new Deck();
    pilates.cards[1] = 2;

    /*dingie.cards = [1, 2 ,4 ,10];
    **pilates.cards = [2, 2, 4, 1, 3];
    */ 
    int[] newArrWhoDis = {2, 2, 4, 1, 3};
    dingie.cards = pilates.cards;
    pilates.cards = newArrWhoDis;
    newArrWhoDis = null;

}