package a;

import java.util.List;

public class Command {
	public List<CardDTO> getCardsByUserAndType(String userId,String cardType){
		CardDAO cardDAO = new CardDAO();
		List<CardDTO> cardList = cardDAO.getCardsByUserAndType(userId, cardType);
		return cardList;
	}
}
