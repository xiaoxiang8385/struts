package manning.chapterTwo;

import java.util.LinkedHashMap;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.inject.Inject;
import com.service.UserService;

/* 
 * Hello World Action for chapter one.  This Action expects to receive a 
 * name input from a form on Name.jsp.  The business logic of this action
 * merely concatenates the name with its static greeting message, and then
 * makes that custom message available for the result to which it forwards.  
 */

public class HelloWorld {
	
	@Inject(value = "service2")
	private UserService service2;
	
	
    public String execute()  {
    	
    	
    	UserService u1 = ActionContext.getContext().getContainer().getInstance(UserService.class,"service1");
    	
    	
    	setCustomGreeting(service2.say() + getName() );
    	
    
    	
    	
    	return "SUCCESS";
    }


    /*
     * Definition of Java Bean properties includes a field, getter and
     * setter method for each property.  This is neccessary for the data 
     * transfer mechanisms of Struts2.
     */
    
    private String name;
    
 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    private String customGreeting;
    
    public String getCustomGreeting()
    {
    	return customGreeting;
    }
    
    public void setCustomGreeting( String customGreeting ){
    	this.customGreeting = customGreeting;
    }
    
    
  
    
    
}
