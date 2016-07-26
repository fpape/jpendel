package be.jpendel.domain.member;

public class Member {
    private String name;
    private String email;

    private Member(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public static Member withNameAndEmail(String name,String email){
        return new Member(name,email);
    }
}
