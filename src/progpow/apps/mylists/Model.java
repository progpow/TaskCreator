package progpow.apps.mylists;

import java.util.ArrayList;

public class Model {

	private String name;
	private boolean selected;
	private String description;
	private ArrayList<Item> items;
	
	public Model(String name) {
		this.name = name;
		selected = false;
		this.description = "";
		items = new ArrayList<Item>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description= description;
	}
	
	public ArrayList<Item> getItems()
	{
		return this.items;
	}
}
