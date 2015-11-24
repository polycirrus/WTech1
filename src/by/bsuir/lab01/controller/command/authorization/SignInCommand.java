package by.bsuir.lab01.controller.command.authorization;


import by.bsuir.lab01.bean.SessionInfo;
import by.bsuir.lab01.bean.request.Request;
import by.bsuir.lab01.bean.request.impl.SignInRequest;
import by.bsuir.lab01.bean.response.Response;
import by.bsuir.lab01.bean.response.impl.SignInResponse;
import by.bsuir.lab01.controller.command.Command;
import by.bsuir.lab01.controller.command.CommandException;
import by.bsuir.lab01.service.AuthorizationService;
import by.bsuir.lab01.service.ServiceException;

public class SignInCommand implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        SignInRequest siRequest;
        try {
            siRequest = (SignInRequest) request;
        } catch (ClassCastException exception) {
            throw new CommandException("Invalid request.");
        }

        SessionInfo sessionInfo;
        try {
            if (siRequest.getSessionId() != null)
                AuthorizationService.SignOut(siRequest.getSessionId());

            sessionInfo = AuthorizationService.SignIn(siRequest.getCredentials());
        } catch (ServiceException exception) {
            throw new CommandException(exception.getMessage());
        }

        SignInResponse response = new SignInResponse();
        response.setMessage("Sign in successful.");
        response.setSessionInfo(sessionInfo);

        return response;
    }
}
