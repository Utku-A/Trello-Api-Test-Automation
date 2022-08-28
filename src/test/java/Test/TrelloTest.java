package Test;

import ApiModel.Board;
import ApiModel.Card;
import org.testng.annotations.Test;

public class TrelloTest {

    Board board = new Board();
    Card card = new Card();

    @Test
    public void TS001() {
        String boardID,card1ID,card2ID;

        boardID = board.createBoard("TestBoard1");
        card1ID = card.createCard("İlk Kart", board.getListID(boardID));
        card2ID = card.createCard("İkici Kart", board.getListID(boardID));
        card
                .updateCardName(new String[]{card1ID, card2ID},"Yeni Kart İsmi")
                .deleteCard(card1ID)
                .deleteCard(card2ID);
        board
                .deleteBoard(boardID);

    }

}
