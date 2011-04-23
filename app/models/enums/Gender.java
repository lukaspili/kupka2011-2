package models.enums;

/**
 * User: derf4002
 * Date: 4/23/11
 * Time: 2:31 PM
 */
public enum Gender {

    MALE("male"),
    FEMALE("female");

    private String name;

    Gender(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }

    public static Gender findByName(String strGender) {
        for (Gender o : values()) {
            if(o.name.equals(strGender)) return o;
        }
        return null;
    }
}
