package manning.chapterTwo;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;
import com.service.UserService;



public class InjectAction extends ActionSupport {
	@Inject(value = "service1")
	private UserService service1;

	@Override
	public String execute() throws Exception {
	        UserService service2 = ActionContext.getContext().getContainer().getInstance(UserService.class, "service2");
	        return SUCCESS;
	}
}
