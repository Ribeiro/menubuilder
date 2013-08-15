package br.com.gigio.menubuilder.domain;

import java.util.ArrayList;
import java.util.List;

public class LinkMenu extends Menu{
	private final List<Role> roles;

	public LinkMenu(Integer id, Integer parentId, String menuType,
			boolean enabled, boolean visible, String url) {
		super(id, parentId, menuType, enabled, visible, url);
		this.roles = new ArrayList<Role>();
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	
	@Override
	public boolean isVisible() {
		return !this.roles.isEmpty() && enabled ? true : false;
	}

}
