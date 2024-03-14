import java.util.ArrayList;

public class Items {
	private static Items items;
	private ArrayList<Item> itemList;
	
	private Items() {
		itemList = DataLoader.getItems();
	}
	
	public static Items getInstance() {
		if(items == null) {
			items = new Items();
		}
		
		return items;
	}

	public boolean haveItem(String itemName) {
		return true;
	}
	
	public Item getItem(String itemName) {
		if(!haveItem(itemName)) return null;
		
		return new Item(itemName, "Jim Hart");
	}
}
