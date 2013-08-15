package br.com.gigio.menubuilder.domain;

public class ParentMenu extends Menu{

	public ParentMenu(Integer id, Integer parentId, String menuType,
			boolean enabled, boolean visible) {
		super(id, parentId, menuType, enabled, visible);
	}

}
