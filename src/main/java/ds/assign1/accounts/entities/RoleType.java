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
        }
        throw new RuntimeException("This roletype does not exist: " + role);
    }

    public static String toString(RoleType role){
        switch(role){
            case CLIENT:
                return "client";
            case ADMIN:
                return "admin";
        }

        throw new RuntimeException("RoleType not found: " + role);
    }
}
