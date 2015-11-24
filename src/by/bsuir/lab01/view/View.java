package by.bsuir.lab01.view;

import by.bsuir.lab01.controller.BookController;

public abstract class View {
    protected BookController controller = new BookController();
    protected String sessionId = null;

    public void view() {
//		NewBookRequest request = new NewBookRequest();
//		// set patameters into request
//		Response response = controller.executeRequest(request);
//		if (response.getErrorMessage() == null){
//			// System.out.println("error");
//		} else {
//			NewBookResponse newBookResponse = (NewBookResponse)response;
//			// System.out.println...
//		}
    }
}
