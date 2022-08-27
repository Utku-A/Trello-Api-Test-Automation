package ApiModel;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Card {

    String token = "e79b6e428ea3c377f7b6a1c92ea6cafdd01d1202de75907139c6d8e4e4805eff";
    String apikey = "81817444f27c74313b64e8bcdd7350d1";

    public String createCard(String name,String idlist) {
        return given()
                .contentType("application/json")
                .queryParam("name",name)
                .queryParam("key",apikey)
                .queryParam("token",token)
                .queryParam("idList",idlist)
                .when()
                .post("https://api.trello.com/1/cards/")
                .then().log().all().statusCode(200).body("name",equalTo(name)).extract().path("id");
    }

    public Card updateCardName(String[] cardID,String name) {
        Random r = new Random();
        String selectCardID = cardID[r.nextInt(cardID.length)];

        given()
                .contentType("application/json")
                .queryParam("name",name)
                .queryParam("key",apikey)
                .queryParam("token",token)
                .when()
                .put("https://api.trello.com/1/cards/"+selectCardID+"/")
                .then().log().all().statusCode(200).body("name",equalTo(name));
        return this;
    }

    public Card deleteCard(String cardID) {
        given()
                .contentType("application/json")
                .queryParam("key",apikey)
                .queryParam("token",token)
                .when()
                .delete("https://api.trello.com/1/cards/"+cardID+"/")
                .then().log().all().statusCode(200);
        return this;
    }

}
