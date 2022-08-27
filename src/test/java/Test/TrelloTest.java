package Test;

import ApiModel.Board;
import ApiModel.Card;
import org.testng.annotations.Test;

public class TrelloTest {

    Board board = new Board();
    Card card = new Card();

    @Test
    public void TS001() {
        String boardID,card1ID,card2ID,boardListID;

        boardID = board.createBoard("TestBoard1");
        boardListID = board.getListID(boardID);
        card1ID = card.createCard("İlk Kart",boardListID);
        card2ID = card.createCard("İkici Kart",boardListID);
        card
                .updateCardName(new String[]{card1ID, card2ID},"Yeni Kart İsmi")
                .deleteCard(card1ID)
                .deleteCard(card2ID);
        board
                .deleteBoard(boardID);

    }

}
