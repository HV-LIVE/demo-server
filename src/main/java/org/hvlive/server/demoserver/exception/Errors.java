package org.hvlive.server.demoserver.exception;

public interface Errors {
    interface User {
        int CODE_ACCOUNT_EXISTS = 10000;
        String MESSAGE_ACCOUNT_EXISTS = "account exists";

        int CODE_ACCOUNT_PASSWORD_WRONG = 10001;
        String MESSAGE_ACCOUNT_PASSWORD_WRONG = "account password wrong";
    }

    interface Channel {
        int CODE_NAME_EXISTS = 20000;
        String MESSAGE_NAME_EXISTS = "name exists";
    }

    interface Section {
        int CODE_NAME_EXISTS = 30000;
        String MESSAGE_NAME_EXISTS = "name exists";
    }

    interface Live {
        int CODE_TIME_CONFLICT = 40000;
        String MESSAGE_TIME_CONFLICT = "time conflict";
    }
}
