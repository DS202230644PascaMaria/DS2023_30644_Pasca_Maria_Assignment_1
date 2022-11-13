package ds.assign1.accounts.services;

import java.util.Arrays;
import java.util.regex.*;
import ds.assign1.accounts.repos.AccountRepo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountValidators {
    private final AccountRepo REPO;

    public void usernameValidators(String username){
        if(REPO.findAll().stream().anyMatch(x -> x.getCredentials().getUsername().equals(username))){
            throw new RuntimeException("Username already exists");
        }

        Pattern p = Pattern.compile("[a-zA-Z]+[0-9]*([_-]*[a-zA-Z])?");
        Matcher m = p.matcher(username);
        if(!m.matches()){
            throw new IllegalArgumentException("Username doesn't follow the wanted form");
        }
    }

    public void passwordValidator(String password){
        Pattern p = Pattern.compile(" ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
        Matcher m = p.matcher(password);
        if(!m.matches()){
            throw new IllegalArgumentException("Password doesn't follow the wanted form");
        }
    }

    public void nameValidator(String name){

        if(name.length() > 70){
            throw new IllegalArgumentException("Too many characters in the name");
        }

        Pattern pattern = Pattern.compile("([A-Z]')?[A-Z][a-z]+");

        String[] names = name.split(" ");

        if(!Arrays.stream(names).allMatch(crtName -> pattern.matcher(crtName).matches())){
            throw new IllegalArgumentException("Name may contain special characters or may start with lower case letters");
        }
    }
}
