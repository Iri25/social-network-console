package socialnetwork.domain.validators;

import socialnetwork.domain.User;

public class UserValidator implements Validator<User> {

    /**
     * validate the user
     * @param entity
     * @throws ValidationException
     */
    @Override
    public void validate(User entity) throws ValidationException {
        String firstName = entity.getFirstName();
        String lastName = entity.getLastName();
        long id = entity.getId();

        String errors = "";

        if(firstName.equals(""))
            errors += "First name not valid! ";
        if(firstName.length() < 3)
            errors += "Firs name should have at least two characters! ";
        char[] charsFirstName = firstName.toCharArray();
        for(char characterFirstName : charsFirstName){
            if(Character.isDigit(characterFirstName)){
                errors += "First name must not contain digits! ";
                break;
            }
        }

        if(lastName.equals(""))
            errors += "Last name not valid! ";
        if(lastName.length() < 3)
            errors += "Last name should have at least two characters! ";
        char[] charsLastName = firstName.toCharArray();
        for(char characterLastName : charsLastName){
            if(Character.isDigit(characterLastName)){
                errors += "Last name must not contain digits! ";
                break;
            }
        }

        if(id < 0)
            errors += "Id not valid! ";

        if(errors.length() > 0)
            throw new ValidationException(errors);

    }
}



