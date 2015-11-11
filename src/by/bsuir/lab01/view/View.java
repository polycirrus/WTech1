package by.bsuir.lab01.view;

import by.bsuir.lab01.bean.NewBookRequest;
import by.bsuir.lab01.bean.NewBookResponse;
import by.bsuir.lab01.bean.Response;
import by.bsuir.lab01.controller.BookController;

public class View {
	private BookController controller = new BookController();
	
	
	public void menu(){
		
		
		NewBookRequest request = new NewBookRequest();
		// set patameters into request
		Response response = controller.executeRequest(request);
		if(response.getErrorMessage() == null){
			// System.out.println("error");
		}else{
			NewBookResponse newBookREsponse = (NewBookResponse)response;
			// System.out.println...
		}
		
		
	}

}
