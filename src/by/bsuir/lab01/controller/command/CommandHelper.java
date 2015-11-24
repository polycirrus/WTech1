package by.bsuir.lab01.controller.command;

import by.bsuir.lab01.controller.command.authorization.RegisterCommand;
import by.bsuir.lab01.controller.command.authorization.SignInCommand;
import by.bsuir.lab01.controller.command.authorization.SignOutCommand;
import by.bsuir.lab01.controller.command.communication.SuggestNewBookCommand;
import by.bsuir.lab01.controller.command.library.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandHelper {
	private Map<CommandName, Command> commands = new HashMap<CommandName, Command>();
	
	public CommandHelper(){
        commands.put(CommandName.ADD_BOOK, new AddBookCommand());
        commands.put(CommandName.GET_BOOKS, new GetBooksCommand());
        commands.put(CommandName.FIND_BOOKS_BY_AUTHOR, new FindBooksByAuthorCommand());
        commands.put(CommandName.FIND_BOOKS_BY_ISBN, new FindBooksByIsbnCommand());
        commands.put(CommandName.FIND_BOOKS_BY_TITLE, new FindBooksByTitleCommand());
        commands.put(CommandName.REMOVE_BOOKS_BY_TITLE, new RemoveBooksByTitleCommand());

        commands.put(CommandName.REGISTER, new RegisterCommand());
        commands.put(CommandName.SIGN_IN, new SignInCommand());
        commands.put(CommandName.SIGN_OUT, new SignOutCommand());

        commands.put(CommandName.SUGGEST_NEW_BOOK, new SuggestNewBookCommand());
    }

    public Command getCommand(String commandName){
		CommandName command = CommandName.valueOf(commandName);
		return commands.get(command);		
	}
}
