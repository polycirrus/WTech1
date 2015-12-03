package by.bsuir.lab01.view.console;

import by.bsuir.lab01.entity.AccessLevel;

import java.util.function.BooleanSupplier;

class MenuItem {
    private BooleanSupplier method;
    private String description;
    private AccessLevel accessLevel;

    public MenuItem(BooleanSupplier method, String description, AccessLevel accessLevel) {
        setMethod(method);
        setDescription(description);
        setAccessLevel(accessLevel);
    }

    public BooleanSupplier getMethod() {
        return method;
    }

    public void setMethod(BooleanSupplier method) {
        if (method == null)
            throw new IllegalArgumentException();

        this.method = method;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null)
            throw new IllegalArgumentException();

        this.description = description;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }
}
