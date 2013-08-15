package br.com.gigio.menubuilder.domain;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	protected Integer id;
	protected Integer parentId;
	protected String menuType;
	protected boolean enabled;
	protected boolean visible;
	protected String url;
	protected List<Menu> children;
	
	public Menu(Integer id, Integer parentId, String menuType, boolean enabled, boolean visible){
		this.id = id;
		this.parentId = parentId;
		this.menuType = menuType;
		this.enabled = enabled;
		this.visible = visible;
		this.children = new ArrayList<Menu>();
		
	}
	
//	public void setChildren(Menu children) {
//		this.children = children;
//	}
//	
//	public Menu getChildren() {
//		return children;
//	}
	
	public void addChildren(Menu children){
		this.children.add(children);
	}
	
	public Menu(Integer id, Integer parentId, String menuType, boolean enabled, boolean visible, String url){
		this(id, parentId, menuType, enabled, visible);
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public void buildMenuTreeFrom(List<Menu> menuList){
		for (Menu menuFromOuterLoop : menuList) {
			if (menuFromOuterLoop.getParentId() != null) {
				for (Menu menuFromInnerLoop : menuList) {
					if (menuFromOuterLoop.getParentId() == menuFromInnerLoop.getId()) {
						menuFromInnerLoop.addChildren(menuFromOuterLoop);
					}
				}
				
			
			}
			
		}
	}

}