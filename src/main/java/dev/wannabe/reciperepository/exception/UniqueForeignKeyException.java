package dev.wannabe.reciperepository.exception;

public class UniqueForeignKeyException extends RuntimeException {
    public UniqueForeignKeyException(String message) {
        super(message);
    }
}
