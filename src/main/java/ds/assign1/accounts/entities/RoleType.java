package ds.assign1.accounts.entities;

public enum RoleType {
    ADMIN,
    CLIENT;

    public static RoleType getRole(String role){
        switch(role){
            case "client":
            case "Client":
            case "CLIENT":
                return CLIENT;
            case "admin":
            case "Admin":
            case "ADMIN":
                return ADMIN;
            default: return CLIENT;

        }
    }

    public static String toString(RoleType role){
        switch(role){
            case CLIENT:
                return "client";
            case ADMIN:
                return "admin";
            default: return "CLIENT";
        }

    }
}
