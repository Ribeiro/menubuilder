package br.com.gigio.menubuilder.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Before;
import org.junit.Test;
import com.google.gson.Gson;

/**
 * Formatting and validation on JSON could be achieved at http://jsonformatter.curiousconcept.com/#jsonformatter
 * 
 * @author geovanny.ribeiro
 *
 */

public class MenuBuilderTest {
	private static Logger logger = Logger.getLogger(MenuBuilderTest.class);
	private Menu rootMenu;
	private Menu childOfRootMenu;
	private Menu childOfChilfOfRootMenu;
	private Menu childOfChilfOfChildOfRootMenu;
	private List<Menu> menuList;
	
	
	
	@Before
	public void setUp(){
		rootMenu = new ParentMenu(1, null, "PARENT", true, true);
		childOfRootMenu = new ParentMenu(2, rootMenu.getId(), rootMenu.getMenuType(), true, true);
		childOfChilfOfRootMenu = new ParentMenu(3, childOfRootMenu.getId(), childOfRootMenu.getMenuType(), true, true);
		childOfChilfOfChildOfRootMenu = new LinkMenu(4, childOfChilfOfRootMenu.getId(), "LINK", true, true, "http://www.google.com.br");
		
		menuList = new ArrayList<Menu>();
		menuList.add(childOfChilfOfChildOfRootMenu);
		menuList.add(rootMenu);
		menuList.add(childOfRootMenu);
		menuList.add(childOfChilfOfRootMenu);
		
		DOMConfigurator.configure("log4j.xml");
		
	}
	
	@Test
	public void shouldSuccessfullyConvertJavaObjectToJsonForUseWithMenu(){
		rootMenu.addChildren(childOfRootMenu);
		childOfRootMenu.addChildren(childOfChilfOfRootMenu);
		childOfChilfOfRootMenu.addChildren(childOfChilfOfChildOfRootMenu);
		
		Gson gson = new Gson();
		String jsonConverted = gson.toJson(rootMenu);
		String expectedJson = configureJson();
		assertThat(jsonConverted, equalTo(expectedJson));
		logger.info(jsonConverted);
		
	}
	
	@Test
	public void shouldSuccessfullyBuildMenuTreeUsingJavaObjectAndConvertToJsonForUseWithMenu(){
		
		for (Menu menu : menuList) {
			if (menu.getParentId() == null) {
				rootMenu.buildMenuTreeFrom(menuList);
				break;
			}
		}
		
		Gson gson = new Gson();
		String jsonConverted = gson.toJson(rootMenu);
		String expectedJson = configureJson();
		assertThat(jsonConverted, equalTo(expectedJson));
		logger.info(jsonConverted);
	
	}

	private String configureJson() {
		StringBuffer sb = new StringBuffer();
		sb.append("{\"id\":1,\"menuType\":\"PARENT\",\"enabled\":true,\"visible\":true,\"children\":[{\"id\":2,\"parentId\":1,\"menuType\":\"PARENT\",\"enabled\":true,\"visible\":true,\"children\":[{\"id\":3,\"parentId\":2,\"menuType\":\"PARENT\",\"enabled\":true,\"visible\":true,\"children\":[{\"roles\":[],\"id\":4,\"parentId\":3,\"menuType\":\"LINK\",\"enabled\":true,\"visible\":true,\"url\":\"http://www.google.com.br\",\"children\":[]}]}]}]}");
		return sb.toString();
	}


}